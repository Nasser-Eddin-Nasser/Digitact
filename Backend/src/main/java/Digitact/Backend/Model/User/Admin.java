package Digitact.Backend.Model.User;

import Digitact.Backend.Model.DeviceIdentifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "users")
public class Admin extends User {
    private static final long serialVersionUID = -2343243243242432341L;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "passHint")
    private String passHint;

    @Column(name = "password")
    private String password; // todo

    @Column(name = "clientToken")
    private String clientToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<DeviceIdentifier> deviceIdentifiers;

    /**
     * @param firstName
     * @param lastName
     */
    public Admin(String firstName, String lastName) {
        super(firstName, lastName, UserRight.Admin);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHint() {
        return passHint;
    }

    public void setPassHint(String passHint) {
        this.passHint = passHint;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public void setDeviceIdentifier(DeviceIdentifier deviceIdentity) {
        deviceIdentifiers.add(deviceIdentity);
    }

    public List<DeviceIdentifier> getDeviceIdentifiers() {
        return new ArrayList<DeviceIdentifier>(deviceIdentifiers);
    }

    public void setDeviceIdentifiers(List<DeviceIdentifier> deviceIdentity) {
        if (deviceIdentity != null) {
            deviceIdentity.forEach(dev -> deviceIdentifiers.add(dev));
        }
    }

    protected Admin() {
        super();
    }

    public UserRight getUserRight() {
        return UserRight.Admin;
    }
}
