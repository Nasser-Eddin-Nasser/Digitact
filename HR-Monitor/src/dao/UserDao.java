package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.User.Applicant;
import Model.User.User;
import Database.Connector;

public class UserDao {

	Connection con = null;

	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();

		String firstName = "";
		String secondName = "";

		Connector db = new Connector();
		try {
			con = db.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users;");
			while (rs.next()) {
				firstName = rs.getString(3);
				secondName = rs.getString(4);

				User user = new Applicant(firstName, secondName);

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
