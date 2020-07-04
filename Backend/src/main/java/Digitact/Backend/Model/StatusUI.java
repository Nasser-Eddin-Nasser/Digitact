package Digitact.Backend.Model;

public class StatusUI {

    long appID;
    Status status;

    StatusUI(long appID, Status status){
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
