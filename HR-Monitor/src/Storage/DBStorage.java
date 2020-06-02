package Storage;

import Database.Connector;
import Model.User.Applicant;
import Model.User.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBStorage {
    private static List<User> users;
    private static Connection connection = null;

    public static List<User> getStorage() {
        updateStorage();
        return users;
    }

    private static void updateStorage() {
        users = getUsers();
    }

    private static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        Connector db = new Connector();
        try {
            connection = db.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from users;");
            while (rs.next()) {
                User user = new Applicant(rs.getString(3), rs.getString(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
