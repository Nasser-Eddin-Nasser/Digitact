package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {


	private Connection connection = null;

	public Connection getConnection() {
		try {
			closeConnection();
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(Configuration.url, Configuration.user, Configuration.password);
			System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
					+ connection.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("exit");
		}

		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null && connection.isClosed() == false)
				connection.close();
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
