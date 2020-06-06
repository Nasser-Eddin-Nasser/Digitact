package Digitact.Backend.Model;

import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "KeyCompetence")
public class KeyCompetence {
  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userId")
  public User user;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "category")
  private KeyCompetenciesCategory category;

  @Column(name = "name")
  private String name;

  @Column(name = "rating")
  private int rating;

  public KeyCompetence() {}

  public KeyCompetence(User user) {
    super();
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public KeyCompetenciesCategory getCategory() {
    return this.category;
  }

  public String getName() {
    return this.name;
  }

  public int getRating() {
    return this.rating;
  }
}

