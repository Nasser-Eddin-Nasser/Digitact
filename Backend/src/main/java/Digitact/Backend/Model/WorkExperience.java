package Digitact.Backend.Model;

import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "WorkExperience")
public class WorkExperience {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "company")
    private String company;

    @Column(name = "employmentType")
    private EmploymentType employmentType;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "description")
    private String description;

    public WorkExperience() {}

    public WorkExperience(User user) {
        super();
        this.user = user;
    }

    public WorkExperience(
            String jobTitle,
            String company,
            EmploymentType employmentType,
            String startDate,
            String endDate,
            String description) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.employmentType = employmentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
