package Digitact.Backend.Model.User;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.Industries;
import Digitact.Backend.Model.KeyCompetence;
import Digitact.Backend.Model.Positions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

// import com.google.common.collect.Lists;

@Entity
@Table(name = "users")
public class Applicant extends User {
    private static final long serialVersionUID = -2343243243242432341L;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<Education> educations;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Industries industries;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Positions positions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<KeyCompetence> keyCompetencies;

    protected Applicant() {
        super();
    }

    /**
     * @param firstName
     * @param lastName
     */
    public Applicant(String firstName, String lastName) {
        super(firstName, lastName, UserRight.Applicant);
        educations = new HashSet<Education>();
        keyCompetencies = new HashSet<KeyCompetence>();
    }

    public UserRight getUserRight() {
        return UserRight.Applicant;
    }

    public void setEducation(Education education) {
        educations.add(education);
    }

    public List<Education> getEducations() {
        return new ArrayList<Education>(educations);
    }

    public void setEducations(List<Education> education) {
        education.forEach(edd -> educations.add(edd));
    }

    public Industries getIndustries() {
        return industries;
    }

    public void setIndustries(Industries industries) {
        this.industries = industries;
    }

    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions positions) {
        this.positions = positions;
    }

    public void addKeyCompetence(KeyCompetence keyCompetence) { keyCompetencies.add(keyCompetence); }

    public List<KeyCompetence> getKeyCompetencies() { return new ArrayList<KeyCompetence>(keyCompetencies); }

    public void addKeyCompetencies(List<KeyCompetence> keyCompetencies) { keyCompetencies.forEach(comp -> this.keyCompetencies.add(comp)); }
}
