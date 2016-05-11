package persistence;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * PER UTILIZZARE QUESTA CLASSE, LA LIBRERIA 
 * c3p0-0.9.1.1.jar 
 * DEVE ESSERE NEL BUILD PATH
 * 
 * Project -> Properties -> "Java Build Path" -> "Add JARs"
 * 
 * */


public class AdvancedDataSource {

	private static Logger logger = Logger.getLogger("persistence.AdvancedDataSource");
	private ComboPooledDataSource cpds;
	private static AdvancedDataSource instance;
	
	String driver;
	String dbURI;
	String username;
	String password;

	private AdvancedDataSource(){
		InputStream inputStream = null;
		Properties properties = new Properties();
		
		try {
			// il file di properties jdbc.properties nel quale scriviamo i parametri per la connessione
			// e' memorizzato nella directory META-INF che si trova alla radice dei sorgenti 
			// (quindi abbiamo src/META-INF/)

			inputStream = this.getClass().getClassLoader().getResourceAsStream("build/jdbc.properties");	
			
			//inputStream = new FileInputStream("C:/Users/giorg/workspace/ASW/build/jdbc.properties");
			
			properties.load(inputStream);
		} catch (IOException e) {
			logger.severe("Error loading the Data Source property file: "+ e.getMessage());
		}			
		driver = properties.getProperty("driver");
		dbURI = properties.getProperty("dbURI");
		username = properties.getProperty("username");
		password = properties.getProperty("password");

		
		this.cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(driver);
		} catch (PropertyVetoException e) {
			logger.severe("Error Loading Class Driver " + e.getMessage());
		} 
		this.cpds.setJdbcUrl(dbURI);
		this.cpds.setUser(username);                                  
		this.cpds.setPassword(password);                                  

		// altre impostazioni (si potrebbero omettere e usare i valori di default)
		this.cpds.setMinPoolSize(5);                                     
		this.cpds.setAcquireIncrement(5);
		this.cpds.setMaxPoolSize(20);		
	}
	/**
	 * 
	 * @return un nuovo oggetto AdvancedDataSource se non e' stato gia' creato, altrimenti
	 * l'oggetto gia' esistente
	 */
	public static AdvancedDataSource getInstance(){
		if (instance==null) 
			instance = new AdvancedDataSource();
		return instance;
	}
	
	public Connection getConnection() throws PersistenceException  {
		Connection connection = null;
		try {
			connection = this.cpds.getConnection();
		} catch (SQLException e) {
			logger.severe("Connessione non creata "+ e.getMessage());
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}