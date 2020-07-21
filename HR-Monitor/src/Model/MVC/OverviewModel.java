package Model.MVC;

import static Main.App.DEVELOPMENT_ENVIRONMENT;

import Model.User.ApplicantUI;
import Storage.DBStorage;
import Storage.Dummy;
import java.util.List;
import java.util.stream.Collectors;

public class OverviewModel {

    public List<ApplicantUI> getDB() {
        if (DEVELOPMENT_ENVIRONMENT) {
             Dummy.DB.addAll(DBStorage.getStorage());
            return Dummy.DB;
        } else {
            return DBStorage.getStorage();
        }
    }

    public ApplicantUI getApplicantByID(long id) {
        if (DEVELOPMENT_ENVIRONMENT)
            return Dummy.DB
                    .stream()
                    .filter(x -> (x.getID() + "").equals(id + ""))
                    .collect(Collectors.toList())
                    .get(0);
        return DBStorage.getApplicantByID(id);
    }
}
