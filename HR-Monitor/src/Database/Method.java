package Database;

public enum Method {
    getApplicants("getApplicants");

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
