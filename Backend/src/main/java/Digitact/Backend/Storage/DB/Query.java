package Digitact.Backend.Storage.DB;

public class Query {
  public static final String getAllUsersQuery = "SELECT firstname, lastname, user_role  FROM users";
  public static final String getApplicantQuery = "SELECT * FROM users WHERE dtype = 'Applicant'";
  public static final String getEducationsByUserID = "SELECT * FROM education WHERE user_id = ?1";
  public static final String getAllEducationsQuery = "SELECT * FROM education";
  public static final String getFullApplicantsInfoQuery =
      "SELECT * FROM users INNER JOIN education on users.id = education.user_id";
}
