package Model;

public class Education {

    private Long id;

    private String university;

    private String subject;

    private Degree degree;

    private double grade;

    private String graduation_date;

    private Education() {}

    public Education(
            String university,
            String subject,
            Degree degree,
            double grade,
            String graduation_date) {
        this.university = university;
        this.subject = subject;
        this.degree = degree;
        this.grade = grade;
        this.graduation_date = graduation_date;
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

    public Long getId() {
        return id;
    }
}
