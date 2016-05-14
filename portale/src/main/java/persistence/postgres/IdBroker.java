package persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import persistence.PersistenceException;

public class IdBroker {
	private static Logger logger = Logger.getLogger("persistence.postgres.IdBroker");

	// valide per postgresql (queste stringhe potrebbero essere scritte nel file di configurazione)
	private static final String query = "SELECT NEXTVAL('sequenza_id') AS id";

	public static Long getId(Connection connection) throws PersistenceException {
		long id = -1;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		}
		return new Long(id);
	}
}

