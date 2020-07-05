package Digitact.Backend.Model;

import Digitact.Backend.Model.User.Admin;

public class Token {
    private Long uniqueRandom;
    private String clientURL;
    private Admin loggedinAdmin;

    public Token(Long uniqueRandom, String clientURL) {
        this.uniqueRandom = uniqueRandom;
        this.clientURL = clientURL;
    }

    public Admin getLoggedinAdmin() {
        return loggedinAdmin;
    }

    public void setLoggedinAdmin(Admin loggedinAdmin) {
        this.loggedinAdmin = loggedinAdmin;
    }

    public Long getUniqueRandom() {
        return uniqueRandom;
    }

    public String getClientURL() {
        return clientURL;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Token)) return false;
        if (((Token) other).getClientURL().equals(this.clientURL)
                && ((Token) other)
                        .getLoggedinAdmin()
                        .getUserName()
                        .equals(this.loggedinAdmin.getUserName())
                && ((Token) other)
                        .getUniqueRandom()
                        .toString()
                        .equals(this.uniqueRandom.toString())) {
            return true;
        }
        return false;
    }
}
