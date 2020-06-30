package Model;

public class HrRating {

    private int rhetoric;

    private int motivation;

    private int selfAssurance;

    private int personalImpression;

    private String impression;

    public HrRating() {}

    public HrRating(
            int rhetoric,
            int motivation,
            int selfAssurance,
            int personalImpression,
            String impression) {
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
}
