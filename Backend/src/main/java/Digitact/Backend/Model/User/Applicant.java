package Digitact.Backend.Model.User;

import Digitact.Backend.Model.Education;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class Applicant extends User {
    private static final long serialVersionUID = -2343243243242432341L;

//    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<Education> educations;

    protected Applicant() {
//        super();
    }


    /**
     * @param firstName
     * @param lastName
     */
    public Applicant(String firstName, String lastName) {
        super(firstName, lastName, UserRight.Applicant);
        educations = new HashSet<Education>();
    }

    public UserRight getUserRight() {
        return UserRight.Applicant;
    }

    public void setEducation(Education education) {
        educations.add(education);
    }

    public void setEducations(List<Education> education) {
        education.forEach(edd -> educations.add(edd));
    }

    public List<Education> getEducations() {
        return Lists.newArrayList(educations);
    }
}
