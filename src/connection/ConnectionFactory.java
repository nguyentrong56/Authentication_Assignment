package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	private final String URL = "jdbc:postgresql://localhost:5432/authentication";
	private final String USER = "postgres";
	private final String PWD = "123";
	
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver"); 
		
		Connection connection = DriverManager.getConnection(URL, USER, PWD);
		
		return connection;
	}
}
 