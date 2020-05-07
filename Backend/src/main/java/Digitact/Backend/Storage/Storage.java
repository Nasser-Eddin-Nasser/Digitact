package Digitact.Backend.Storage;

import Digitact.Backend.Model.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is used (at the moment) as dummy DB
 */
@Repository
public class Storage {
    private static List<User> DB;

    public Storage() {
        if (DB == null)
            DB = new LinkedList<User>();
    }


    /**
     * Add new User to the DB
     * @param element
     */
    public static void addUserToDB(User element) {
        if(!isUserExist(element))
        DB.add(element);
    }

    private static boolean isUserExist(User element) {
        return DB.contains(element);
    }

    public static List<User> getDB() {
        return DB;
    }
}
