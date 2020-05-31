package Util;

import Model.User.Applicant;
import Model.User.User;
import Storage.Dummy;

public class Util {
    public static void fillDB() {
        new Dummy();
        int i = 0;
        while (i < 10000) {
            User user =
                    new Applicant(
                            NameArray.FIRST_NAMES[(int) (Math.random() * (1000) + 0)],
                            NameArray.LAST_NAMES[(int) (Math.random() * (1000) + 0)]);
            Dummy.DB.add(user);
            i++;
        }
    }
}
