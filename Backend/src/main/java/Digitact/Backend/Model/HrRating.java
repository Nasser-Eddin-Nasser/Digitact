package Digitact.Backend.Model;

import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "HrRating")
public class HrRating {
	
	@JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rhetoric")
    private int rhetoric;

    @Column(name = "motivation")
    private int motivation;

    @Column(name = "selfAssurance")
    private int selfAssurance;

    @Column(name = "personalImpression")
    private int personalImpression;
    
    @Column(name = "impression")
    private String impression;

    public HrRating() {}

    public HrRating(User user) {
        super();
        this.user = user;
    }

    public HrRating(
            int rhetoric, int motivation, int selfAssurance, int personalImpression, String impression) {
        this.rhetoric = rhetoric;
        this.motivation = motivation;
        this.selfAssurance = selfAssurance;
        this.personalImpression = personalImpression;
        this.impression = impression;
    }

    public int getRhetoric() {
        return rhetoric;
    }

    public void setRhetoric(int rhetoric) {
        this.rhetoric = rhetoric;
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public int getSelfAssurance() {
        return selfAssurance;
    }

    public void setSelfAssurance(int selfAssurance) {
        this.selfAssurance = selfAssurance;
    }

    public int getPersonalImpression() {
        return personalImpression;
    }

    public void setPersonalImpression(int personalImpression) {
        this.personalImpression = personalImpression;
    }
    
    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
