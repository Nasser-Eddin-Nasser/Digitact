package Digitact.Backend.Model;

import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "Industries")
public class Industries {
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "automotive")
    private boolean automotive = false;

    @Column(name = "finance")
    private boolean finance = false;

    @Column(name = "commerce")
    private boolean commerce = false;

    @Column(name = "pharma_Helthcare")
    private boolean pharma_Helthcare = false;

    @Column(name = "public_Sector")
    private boolean public_Sector = false;

    public Industries() {}

    public Industries(User user) {
        super();
        this.user = user;
    }

    public Industries(
            boolean automotive,
            boolean finance,
            boolean commerce,
            boolean pharma_Helthcare,
            boolean public_Sector) {
        this.automotive = automotive;
        this.finance = finance;
        this.commerce = commerce;
        this.pharma_Helthcare = pharma_Helthcare;
        this.public_Sector = public_Sector;
    }

    public boolean isAutomotive() {
        return automotive;
    }

    public void setAutomotive(boolean automotive) {
        this.automotive = automotive;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public boolean isCommerce() {
        return commerce;
    }

    public void setCommerce(boolean commerce) {
        this.commerce = commerce;
    }

    public boolean isPharma_Helthcare() {
        return pharma_Helthcare;
    }

    public void setPharma_Helthcare(boolean pharma_Helthcare) {
        this.pharma_Helthcare = pharma_Helthcare;
    }

    public boolean isPublic_Sector() {
        return public_Sector;
    }

    public void setPublic_Sector(boolean public_Sector) {
        this.public_Sector = public_Sector;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
