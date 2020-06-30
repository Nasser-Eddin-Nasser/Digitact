package Model;

public class WorkExperience {
    
    private String jobTitle;

    private String company;

    private EmploymentType employmentType;

    private String startDate;

    private String endDate;
    
    private String description;

    public WorkExperience() {}

    public WorkExperience(
            String jobTitle, String company, EmploymentType employmentType, String startDate, String endDate, String description) {
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

}
