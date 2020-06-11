package Digitact.Backend.Model.Image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sqlId;

    private String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "imageId")
    private AppImage appImage;

    @Column(name = "series")
    private int series;

    public Block() {}

    @JsonIgnore
    public Block(AppImage appImage, int series) {
        this.series = series;
        this.id = UUID.randomUUID().toString();
        this.appImage = appImage;
    }

    public void createId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public AppImage getAppImage() {
        return appImage;
    }

    public void setAppImage(AppImage appImage) {
        this.appImage = appImage;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
