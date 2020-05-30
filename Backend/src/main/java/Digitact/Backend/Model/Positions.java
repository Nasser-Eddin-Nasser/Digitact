package Digitact.Backend.Model;

import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Positions")
public class Positions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User user;

    @Column(name = "consultant_Business_Consultant")
    private boolean consultant_Business_Consultant = false;
    @Column(name = "iT_Consultant_Informationsmanagement")
    private boolean iT_Consultant_Informationsmanagement = false;
    @Column(name = "iT_Consultant_Java_JEE")
    private boolean iT_Consultant_Java_JEE = false;
    @Column(name = "iT_Consultant_Data_Science")
    private boolean iT_Consultant_Data_Science = false;
    @Column(name = "iT_Consultant_Artificial_Intelligence")
    private boolean iT_Consultant_Artificial_Intelligence = false;
    @Column(name = "consultant_SAP")
    private boolean Consultant_SAP = false;
    @Column(name = "internship_Working_Student")
    private boolean internship_Working_Student = false;

    public Positions() {
    }

    public Positions(boolean consultant_Business_Consultant, boolean IT_Consultant_Informationsmanagement, boolean IT_Consultant_Java_JEE, boolean IT_Consultant_Data_Science, boolean IT_Consultant_Artificial_Intelligence, boolean consultant_SAP, boolean internship_Working_Student) {
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

    public void setUser(User user) {
        this.user = user;
    }
}
