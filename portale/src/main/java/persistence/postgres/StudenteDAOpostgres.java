package persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import model.Studente;
import persistence.DataSource;
import persistence.PersistenceException;
import persistence.StudenteRepository;

public class StudenteDAOpostgres implements StudenteRepository {

	DataSource dataSource;
	Logger logger = Logger.getLogger("persistence.postgres.StudenteDAOpostgres.log");

	public StudenteDAOpostgres() {
		dataSource = new DataSource();
	}

	@Override
	public boolean insert(Studente studente) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "INSERT INTO studente(nome, cognome, matricola) values (?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, studente.getNome());
			statement.setString(2, studente.getCognome());
			statement.setInt(3, studente.getMatricola());
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
	public boolean update(Studente studente, String nome, String cognome) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "UPDATE studente SET nome = ?, cognome = ?"
					+ "where matricola = '" + studente.getMatricola() + "'";
			statement = connection.prepareStatement(query);
			statement.setString(1, nome);
			statement.setString(2, cognome);
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
	public boolean delete(Studente studente) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		int righeModificate = 0;

		try {
			connection = dataSource.getConnection();
			String query = "DELETE FROM studente WHERE matricola=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, studente.getMatricola());
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
	public Studente findByMatricola(int matricola) throws PersistenceException {
		Studente studente = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT * FROM studente WHERE matricola = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, matricola);
			result = statement.executeQuery();
			if (result.next()) {
				studente = new StudenteProxy(); 
				studente.setNome(result.getString("nome"));
				studente.setCognome(result.getString("cognome"));
				studente.setMatricola(result.getInt("matricola"));
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
		return studente;
	}

	@Override
	public List<Studente> findByNome(String nome) throws PersistenceException {
		Studente studente = null;
		List<Studente> studenti = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT cognome, matricola, nome FROM studente WHERE nome LIKE ?";
			statement = connection.prepareStatement(query);
			String nomelike = new String("%"+nome+"%");
			statement.setString(1, nomelike);
			result = statement.executeQuery();

			studenti = new LinkedList<Studente>();

			while (result.next()) {
				studente = new StudenteProxy(); // proxy per Laxy Load
				studente.setNome(result.getString("nome"));
				studente.setCognome(result.getString("cognome"));
				studente.setMatricola(result.getInt("matricola"));

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
	public List<Studente> findByCognome(String cognome) throws PersistenceException {
		Studente studente = null;
		List<Studente> studenti = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT nome, matricola, cognome FROM studente WHERE cognome LIKE ?";
			statement = connection.prepareStatement(query);
			String cognomelike = new String("%"+cognome+"%");
			statement.setString(1, cognomelike);
			result = statement.executeQuery();

			studenti = new LinkedList<Studente>();

			while (result.next()) {
				studente = new StudenteProxy(); // proxy per Laxy Load
				studente.setCognome(result.getString("cognome"));
				studente.setNome(result.getString("nome"));
				studente.setMatricola(result.getInt("matricola"));

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
