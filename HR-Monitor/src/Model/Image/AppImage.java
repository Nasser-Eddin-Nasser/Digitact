package Model.Image;

public class AppImage {


    private String id;
    private ImageType type;
    private String content;
    private String path;

    protected AppImage() {}

    public AppImage(String id, ImageType type) {
        this.id = id;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type.toString();
    }

    public String getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
