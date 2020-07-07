package Digitact.Backend.Model;

public class HRCommentHolder {

    long appID;
    String comment;

    HRCommentHolder(long appID, String comment) {
        this.appID = appID;
        this.comment = comment;
    }

    public long getAppID() {
        return appID;
    }

    public String getComment() {
        return comment;
    }
}
