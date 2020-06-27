package Model;

public class KeyCompetence {

    private Long id;

    private KeyCompetenciesCategory category;

    private String name;

    private int rating;

    public KeyCompetence() {}

    public KeyCompetenciesCategory getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public int getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return this.name + "-" + this.rating;
    }
}
