package Model;

import Main.App;
import Model.User.User;
import Storage.Dummy;

import java.util.List;

import static java.lang.Boolean.TRUE;


public class StorageModel {

    public List<User> getDB() {
        if (TRUE) {
            return Dummy.DB;
        }
        return null;
    }
}
