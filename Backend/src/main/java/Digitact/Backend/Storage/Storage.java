package Digitact.Backend.Storage;

import Digitact.Backend.Model.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
     *
     * @param user
     */
    public static void addUserToDB(User user) {
        if (!isUserExist(user))
            DB.add(user);
    }

    /**
     * Remove User to the DB
     *
     * @param user
     */
    public static void removeUserFromDB(User user) {
        //     if (isUserExist(user))
        DB = DB.stream().filter(x -> (!x.getLastName().equals(user.getLastName()) && !x.getFirstName().equals(user.getFirstName()))).collect(Collectors.toList());
    }


    public static List<User> getAllUsersByLastName(String lastName) {
        return DB.stream().filter(x -> x.getLastName().equals(lastName)).collect(Collectors.toList());
    }


    private static boolean isUserExist(User user) {
        return DB.stream().filter(x -> (x.getLastName().equals(user.getLastName()) && x.getFirstName().equals(user.getFirstName()))).collect(Collectors.toList()).size() > 0;
    }

    public static List<User> getDB() {
        return DB;
    }
}