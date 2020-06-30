package Model;

public class Education {

    private Long id;

    private String university;

    private String subject;

    private Degree degree;

    private double grade;

    private String graduationYear;

    private Education() {}

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

    public Long getId() {
        return id;
    }
}
