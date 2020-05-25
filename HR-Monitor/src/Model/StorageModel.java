package Model;

import Main.App;
import Model.User.User;
import Storage.Dummy;
import dao.UserDao;

import java.util.List;

public class StorageModel {

	public List<User> getDB() {
		if (App.DEVELOPMENT_ENVIRONMENT) {
			return Dummy.DB;
		} else {
			UserDao dao = new UserDao();
			return dao.getUsers();
		}
		// return null;
	}
}
