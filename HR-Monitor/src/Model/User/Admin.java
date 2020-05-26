package Model.User;

public class Admin extends User {
    private static final long serialVersionUID = -2343243243242432341L;

    protected Admin() {
        super();
    }

    /**
     * @param firstName
     * @param lastName
     */
    public Admin(String firstName, String lastName) {
        super(firstName, lastName, UserRight.Admin);
    }

    public UserRight getUserRight() {
        return UserRight.Admin;
    }
}
