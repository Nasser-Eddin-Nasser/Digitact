package Digitact.Backend.Model;

import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "Education")
public class Education {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "university")
    private String university;

    @Column(name = "subject")
    private String subject;

    @Column(name = "degree")
    private Degree degree;

    @Column(name = "grade")
    private double grade;

    @Column(name = "year")
    private String graduationYear;

    public Education() {}

    public Education(User user) {
        super();
        this.user = user;
    }

    public Education(
            String university, String subject, Degree degree, double grade, String graduationYear) {
        this.university = university;
        this.subject = subject;
        this.degree = degree;
        this.grade = grade;
        this.graduationYear = graduationYear;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
