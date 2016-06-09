package persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import model.Esame;
import persistence.DataSource;
import persistence.EsameRepository;
import persistence.PersistenceException;

public class EsameDAOpostgres implements EsameRepository {
	
	DataSource dataSource;
	Logger logger = Logger.getLogger("persistence.postgres.EsameDAOpostgres.log");

	public EsameDAOpostgres() {
		dataSource = new DataSource();
	}
	
	@Override
	public boolean insert(Esame esame) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			long id = IdBroker.getId(connection);
			esame.setId(id);
			String query = "INSERT INTO esame(nome, cfu, descrizione, id) values (?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, esame.getNome());
			statement.setInt(2, esame.getCfu());
			statement.setString(3, esame.getDescrizione());
			statement.setLong(4, id);
			righeModificate = statement.executeUpdate();
			return (righeModificate > 0);
		}
		catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			}
			catch (SQLException e) {
				logger.severe("Errore SQL: " + e.getMessage());
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public boolean update(Esame esame, String nome, String descrizione, int cfu) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "UPDATE esame SET nome = ?, descrizione = ?, cfu = ? "
					+ "where id = '" + esame.getId() + "'";
			statement = connection.prepareStatement(query);
			statement.setString(1, nome);
			statement.setString(2, descrizione);
			statement.setInt(3, cfu);
			righeModificate = statement.executeUpdate();
			return (righeModificate > 0);
		} catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public boolean delete(Esame esame) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "DELETE FROM esame WHERE id=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, esame.getId());
			righeModificate = statement.executeUpdate();
			return (righeModificate > 0);
		}
		catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			}
			catch (SQLException e) {
				logger.severe("Errore SQL: " + e.getMessage());
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public Esame findById(long id) throws PersistenceException {
		Esame esame = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT * FROM esame WHERE id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			result = statement.executeQuery();
			if (result.next()) {
				esame = new Esame(); 
				esame.setId(result.getLong("id"));
				esame.setNome(result.getString("nome"));
				esame.setDescrizione(result.getString("descrizione"));
				esame.setCfu(result.getInt("cfu"));
			}
		}
		catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			}
			catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return esame;	
	}

	@Override
	public List<Esame> findByNome(String nome) throws PersistenceException {
		Esame esame = null;
		List<Esame> esami = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT id, nome, descrizione, cfu FROM esame WHERE nome LIKE ?";
			statement = connection.prepareStatement(query);
			String nomelike = new String("%"+nome+"%");
			statement.setString(1, nomelike);
			result = statement.executeQuery();

			esami = new LinkedList<Esame>();

			while (result.next()) {
				esame = new Esame();
				esame.setId(result.getLong("id"));
				esame.setNome(result.getString("nome"));
				esame.setDescrizione(result.getString("descrizione"));
				esame.setCfu(result.getInt("cfu"));

				esami.add(esame);
			}
		} catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return esami;
	}

	
	@Override
	public List<Esame> findAll() throws PersistenceException {
		Esame esame = null;
		List<Esame> esami = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT id, nome, descrizione, cfu FROM esame";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();

			esami = new LinkedList<Esame>();

			while (result.next()) {
				esame = new Esame();
				esame.setId(result.getLong("id"));
				esame.setNome(result.getString("nome"));
				esame.setDescrizione(result.getString("descrizione"));
				esame.setCfu(result.getInt("cfu"));

				esami.add(esame);
			}
		} catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return esami;
	}

}