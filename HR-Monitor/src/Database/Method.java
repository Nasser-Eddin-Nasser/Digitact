package Database;

public enum Method {
    getApplicants("getApplicants"),
    getImageById("getImageById"),
    getAllEducationInfo("getAllEducationInfo");

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
