package Storage;

import Model.Education;
import Model.WorkExperience;
import Model.User.ApplicantUI;
import java.util.LinkedList;
import java.util.List;

public class Dummy {
    public static List<ApplicantUI> DB;

    public static List<Education> EduDB;
    
    public static List<WorkExperience> WorkDB;

    public Dummy() {
        DB = new LinkedList<ApplicantUI>();
        EduDB = new LinkedList<Education>();
        WorkDB = new LinkedList<WorkExperience>();
    }
}
