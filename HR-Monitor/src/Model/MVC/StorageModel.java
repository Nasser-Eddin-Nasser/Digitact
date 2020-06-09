package Model.MVC;

import Main.App;
import Model.User.ApplicantUI;
import Storage.DBStorage;
import Storage.Dummy;
import java.util.List;

public class StorageModel {

    public List<ApplicantUI> getDB() {
        if (App.DEVELOPMENT_ENVIRONMENT) {
            return Dummy.DB;
        } else {
            return DBStorage.getStorage();
        }
    }

    public ApplicantUI getApplicantByID(long id) {
        return DBStorage.getApplicantByID(id);
    }
}
