package Digitact.Backend.Model.Image;

import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "images")
public class AppImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sqlId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User user;

    private String id;

    @OneToMany(mappedBy = "appImage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<Block> blocks;

    @Column(name = "type")
    private ImageType type;

    @JsonIgnore
    public AppImage(List<Block> blocks) {
        this.id = this.id = UUID.randomUUID().toString();
        this.blocks = blocks;
    }

    public void createId() {
        this.id = UUID.randomUUID().toString();
    }

    public AppImage(String id) {
        this.id = id;
    }

    public AppImage() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }
}