package Util;

import Model.*;
import Model.User.ApplicantUI;
import Storage.Dummy;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static void fillDB() {
        new Dummy();
        int i = 0;
        while (i < 10000) {
            ApplicantUI user =
                    new ApplicantUI(
                            NameArray.FIRST_NAMES[(int) (Math.random() * (1000) + 0)],
                            NameArray.LAST_NAMES[(int) (Math.random() * (1000) + 0)]);
            List<Education> edu = new ArrayList<Education>(10);
            List<WorkExperience> work = new ArrayList<WorkExperience>(10);
            List<KeyCompetence> kc = new ArrayList<KeyCompetence>(10);
            for (int j = 0; j < 10; j++) {
                edu.add(
                        new Education(
                                "Dummy Uni " + j,
                                "Dummy Info" + j,
                                j > 5 ? Degree.Bachelor : Degree.Master,
                                (Math.random() * (5.0) + 0),
                                "199" + j));
                work.add(
                        new WorkExperience(
                                "Dummy jobTilte " + j,
                                "Dummy Company " + j,
                                j > 5 ? EmploymentType.Apprenticeship : EmploymentType.Internship,
                                "199" + j,
                                "202" + j,
                                "description"));
                kc.add(
                        new KeyCompetence(
                                KeyCompetenciesCategory.ProgrammingLanguagesAndFrameworks,
                                "java",
                                j));
                kc.add(
                        new KeyCompetence(
                                KeyCompetenciesCategory.ProgrammingLanguagesAndFrameworks,
                                "c#",
                                j));
            }
            user.setKeyCompetencies(kc);
            user.setEducation(edu);
            user.setWorkExperience(work);
            user.setStatus(i < 100 ? Status.Denied : i < 500 ? Status.Open : Status.Send2HR);
            Dummy.DB.add(user);
            i++;
        }
    }
}
