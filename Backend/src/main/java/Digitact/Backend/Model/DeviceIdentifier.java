package Digitact.Backend.Model;

import Digitact.Backend.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "DeviceIdentifier")
public class DeviceIdentifier {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "deviceIdentity")
    private String deviceIdentity;

    public DeviceIdentifier(User user) {
        super();
        this.user = user;
    }

    public DeviceIdentifier(String deviceIdentity) {
        this.deviceIdentity = deviceIdentity;
    }

    public DeviceIdentifier() {}

    public String getDeviceIdentity() {
        return deviceIdentity;
    }

    public void setDeviceIdentity(String deviceIdentity) {
        this.deviceIdentity = deviceIdentity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
