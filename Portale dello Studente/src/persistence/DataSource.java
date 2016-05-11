package persistence;

import java.sql.*;


public class DataSource {
	private String dbURI;
	private String userName = "postgres";
	private String password = "postgres";
	
	/**
	 * senza argomenti apre una connessione con il database di default 'portale'
	 */
	public DataSource() {
		 dbURI = "jdbc:postgresql://localhost:5432/portale";
	}
	/**
	 * 
	 * @param nomeDB nome del database con cui aprire la connessione, SENZA specificare il percorso completo
	 */
	public DataSource(String nomeDB) {
		dbURI = "jdbc:postgresql://localhost:5432/" + nomeDB;
	}
	
	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
		    Class.forName("org.postgresql.Driver");
		    connection = DriverManager.getConnection(dbURI,userName, password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
	/**
	 * cambia il nome del database dal valore di default a uno a scelta
	 * @param nomeDB il nome da assegnare al database SENZA il percorso completo
	 */
	public void setDbURI (String nomeDB) {
		dbURI = "jdbc:postgresql://localhost:5432/" + nomeDB;
	}
	
	/**
	 * restituisce il percorso completo del database corrente
	 * @return
	 */
	public String getDbURI() {
		return dbURI;
	}

}
