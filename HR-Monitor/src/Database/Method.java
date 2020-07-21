package Database;

public enum Method {
    createApplicant("createApplicant"),
    getApplicants("getApplicants"),
    getImageById("getImageById"),
    getAdminByUserName("getAdminByUserName"),
    createAdminAccount("createAdminAccount"),
    getAdminUserNames("getAdminUserNames"),
    changeStatus("changeStatus"),
    postHRComment("postHRComment"),
    gutenMorgen("gutenMorgen"), // it used to ping the BES
    putToken("putToken");



    private final String name;

    private Method(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
