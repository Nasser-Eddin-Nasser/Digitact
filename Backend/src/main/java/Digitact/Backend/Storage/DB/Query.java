package Digitact.Backend.Storage.DB;

import Digitact.Backend.Model.UserRight;

public class Query {
    public static final String getApplicantQuery = "SELECT * FROM users WHERE dtype = 'Applicant'";
}
