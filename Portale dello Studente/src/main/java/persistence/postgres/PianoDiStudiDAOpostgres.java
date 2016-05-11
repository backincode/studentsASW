package persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import model.Esame;
import model.Studente;
import persistence.DataSource;
import persistence.PersistenceException;
import persistence.PianoDiStudiRepository;

public class PianoDiStudiDAOpostgres implements PianoDiStudiRepository {
	
	DataSource dataSource;
	Logger logger = Logger.getLogger("persistence.postgres.PianoDiStudiDAOpostgres.log");

	public PianoDiStudiDAOpostgres() {
		dataSource = new DataSource();
	}
	
	@Override
	public boolean insert(int studente, Long esame) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "INSERT INTO pianodistudi(id_studente, id_esame) values (?,?)";
			statement = connection.prepareStatement(query);
			statement.setLong(1, studente);
			statement.setLong(2, esame);
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
	public boolean deleteByStudente(int studente) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "DELETE FROM pianodistudi WHERE id_studente=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, studente);
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
	public boolean update(Studente studente, Esame esamevecchio, Esame esamenuovo) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "UPDATE pianodistudi SET id_esame = ?"
					+ "WHERE id_studente = '" + studente.getMatricola() + "' AND id_esame= '"+esamevecchio.getId()+"'";
			statement = connection.prepareStatement(query);
			statement.setLong(1, esamenuovo.getId());
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
	public List<Long> findByStudente(int studente) throws PersistenceException {
		Long esame = null;
		List<Long> esami = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT id_esame FROM pianodistudi WHERE id_studente = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, studente);
			result = statement.executeQuery();

			esami = new LinkedList<Long>();

			while (result.next()) {
				esame = new Long(result.getLong("id_esame"));
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
	public List<Integer> findByAll() throws PersistenceException {
		Integer studente = null;
		List<Integer> studenti = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT DISTINCT id_studente FROM pianodistudi";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();

			studenti = new LinkedList<Integer>();

			while (result.next()) {
				studente = new Integer(result.getInt("id_studente"));
				studenti.add(studente);
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
		return studenti;
	}

	@Override
	public boolean deleteEsameByStudente(Studente studente, Esame esame) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "DELETE FROM pianodistudi WHERE id_studente=? AND id_esame=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, studente.getMatricola());
			statement.setLong(2, esame.getId());
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
	public List<Integer> findByEsame(Esame esame) throws PersistenceException {
		Integer studente = null;
		List<Integer> studenti = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT DISTINCT id_studente FROM pianodistudi WHERE id_esame=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, esame.getId());
			result = statement.executeQuery();

			studenti = new LinkedList<Integer>();

			while (result.next()) {
				studente = new Integer(result.getInt("id_studente"));
				studenti.add(studente);
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
		return studenti;
	}

}
