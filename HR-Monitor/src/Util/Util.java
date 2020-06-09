package Util;

import Model.User.ApplicantUI;
import Storage.Dummy;

public class Util {
    public static void fillDB() {
        new Dummy();
        int i = 0;
        while (i < 10000) {
            ApplicantUI user =
                    new ApplicantUI(
                            NameArray.FIRST_NAMES[(int) (Math.random() * (1000) + 0)],
                            NameArray.LAST_NAMES[(int) (Math.random() * (1000) + 0)]);
            Dummy.DB.add(user);
            i++;
        }
    }
}
