package Model;

public class Positions {
    private Long id;

    private boolean consultant_Business_Consultant = false;

    private boolean iT_Consultant_Informationsmanagement = false;

    private boolean iT_Consultant_Java_JEE = false;

    private boolean iT_Consultant_Data_Science = false;

    private boolean iT_Consultant_Artificial_Intelligence = false;

    private boolean Consultant_SAP = false;

    private boolean internship_Working_Student = false;

    public Positions() {}

    public Positions(
            boolean consultant_Business_Consultant,
            boolean IT_Consultant_Informationsmanagement,
            boolean IT_Consultant_Java_JEE,
            boolean IT_Consultant_Data_Science,
            boolean IT_Consultant_Artificial_Intelligence,
            boolean consultant_SAP,
            boolean internship_Working_Student) {
        consultant_Business_Consultant = consultant_Business_Consultant;
        this.iT_Consultant_Informationsmanagement = IT_Consultant_Informationsmanagement;
        this.iT_Consultant_Java_JEE = IT_Consultant_Java_JEE;
        this.iT_Consultant_Data_Science = IT_Consultant_Data_Science;
        this.iT_Consultant_Artificial_Intelligence = IT_Consultant_Artificial_Intelligence;
        this.Consultant_SAP = consultant_SAP;
        this.internship_Working_Student = internship_Working_Student;
    }

    public boolean isConsultant_Business_Consultant() {
        return consultant_Business_Consultant;
    }

    public boolean isiT_Consultant_Informationsmanagement() {
        return iT_Consultant_Informationsmanagement;
    }

    public boolean isiT_Consultant_Java_JEE() {
        return iT_Consultant_Java_JEE;
    }

    public boolean isiT_Consultant_Data_Science() {
        return iT_Consultant_Data_Science;
    }

    public boolean isiT_Consultant_Artificial_Intelligence() {
        return iT_Consultant_Artificial_Intelligence;
    }

    public boolean isConsultant_SAP() {
        return Consultant_SAP;
    }

    public boolean isInternship_Working_Student() {
        return internship_Working_Student;
    }

    public String toString() {
        String res = "";
        if (consultant_Business_Consultant) res += "consultant_Business_Consultant, ";

        if (Consultant_SAP) res += "Consultant_SAP, ";

        if (internship_Working_Student) res += "internship_Working_Student, ";

        if (iT_Consultant_Artificial_Intelligence) res += "iT_Consultant_Artificial_Intelligence, ";

        if (iT_Consultant_Informationsmanagement) res += "iT_Consultant_Informationsmanagement, ";

        return res.substring(0, res.length() - 2);
    }
}
