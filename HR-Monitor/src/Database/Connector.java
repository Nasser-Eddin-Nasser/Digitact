package Database;

import Model.StorageModel;
import Model.User.Applicant;
import Model.User.User;
import Storage.Dummy;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

public class Connector {

	private final String url = "jdbc:postgresql://localhost:5432/postgres";
	private final String user = "postgres";
	private final String password = "postgres";
	private Connection connection = null;

	public Connection getConnection() {
		try {
			closeConnection();
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
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
