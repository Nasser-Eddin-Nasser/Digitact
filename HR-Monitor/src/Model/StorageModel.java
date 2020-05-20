package Model;

import Main.App;
import Model.User.User;
import Storage.Dummy;

import java.util.List;


public class StorageModel {

    public List<User> getDB() {
        if (App.DEVELOPMENT_ENVIRONMENT) {
            return Dummy.DB;
        }
        return null;
    }
}
