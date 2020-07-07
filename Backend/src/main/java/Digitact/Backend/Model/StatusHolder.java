package Digitact.Backend.Model;

public class StatusHolder {

    long appID;
    Status status;

    StatusHolder(long appID, Status status) {
        this.appID = appID;
        this.status = status;
    }

    public long getAppID() {
        return appID;
    }

    public Status getStatus() {
        return status;
    }
}
