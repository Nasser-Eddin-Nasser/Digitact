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

    public void connect(){

        ArrayList<User> users = new ArrayList<>();

        String firstName = "";
        String secondName = "";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users"))
        {
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1)+" : "+resultSet.getString(2)+" : "+resultSet.getString(3)+" : "+
                        resultSet.getString(4) );
                firstName = resultSet.getString(3);
                secondName = resultSet.getString(4);


                //map(firstName,secondName);
                User user = new Applicant(firstName, secondName);
                StorageModel storagemodel = new StorageModel();
                //users.add(user);
                //Storage.Dummy.DB.add(user);
                //Dummy.DB.add(user);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        //map(firstName,secondName);
    }
    
    public void map(String firstName, String secondName){
        System.out.println("1");
        User user = new Applicant(firstName, secondName);

        Dummy.DB.add(user);
        System.out.println("2");
    }
}
