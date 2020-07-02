package Storage;

import Model.User.Admin;

public class Token {

    private final Long besNumber;
    private Admin loggedinAdmin;

    public Token(Long besNumber) {
        this.besNumber = besNumber;
    }

    public void setLoggedinAdmin(Admin loggedinAdmin) {
        this.loggedinAdmin = loggedinAdmin;
    }

    public Long getBesNumber() {
        return besNumber;
    }

    public Admin getLoggedinAdmin() {
        return loggedinAdmin;
    }
}
