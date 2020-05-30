package Digitact.Backend.Model;

import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "university")
    public String university;
    @Column(name = "subject")
    public String subject;
    @Column(name = "degree")
    public Degree degree; // Master/Bachelor/PhD
    @Column(name = "grade")
    public double grade;
    @Column(name = "date")
    public String graduation_date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User user;

    public Education() {
    }

    public Education(User user) {
        super();
        this.user = user;
    }

    public Education(String university, String subject, Degree degree, double grade, String graduation_Date) {
        this.university = university;
        this.subject = subject;
        this.degree = degree;
        this.grade = grade;
        this.graduation_date = graduation_Date;
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

    public String getGraduation_date() {
        return graduation_date;
    }

    public void setGraduation_date(String graduation_date) {
        this.graduation_date = graduation_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
