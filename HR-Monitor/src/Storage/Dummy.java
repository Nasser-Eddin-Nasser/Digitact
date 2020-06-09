package Storage;

import Model.Education;
import Model.User.ApplicantUI;
import java.util.LinkedList;
import java.util.List;

public class Dummy {
    public static List<ApplicantUI> DB;

    public static List<Education> EduDB ;

    public Dummy() {
        DB = new LinkedList<ApplicantUI>();
        EduDB = new LinkedList<Education>();
    }
}
