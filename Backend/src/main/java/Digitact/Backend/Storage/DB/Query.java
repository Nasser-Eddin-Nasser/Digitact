package Digitact.Backend.Storage.DB;

public class Query {
    public static final String getAllUsersQuery =
            "SELECT firstname, lastname, user_role  FROM users";
    public static final String getApplicantQuery = "SELECT * FROM users WHERE dtype = 'Applicant'";
    public static final String getAdminQuery = "SELECT * FROM users WHERE dtype = 'Admin'";
    public static final String getEducationsByUserID = "SELECT * FROM education WHERE user_id = ?1";
    public static final String getAllEducationsQuery = "SELECT * FROM education";
    public static final String getFullApplicantsInfoQuery =
            "SELECT * FROM users INNER JOIN education on users.id = education.user_id";
    public static final String getImageByID = "SELECT * FROM images WHERE id = ?1";
    public static final String getAdminByUserName = "SELECT * FROM users WHERE user_name = ?1";
    public static final String getWorkExperiencesByUserID =
            "SELECT * FROM WorkExperience WHERE user_id = ?1";
    public static final String getAllWorkExperiencesQuery = "SELECT * FROM WorkExperience";
}
