package Model.Image;

public class AppImage {
    private long id;
    private ImageType type;
    private String content;

    protected AppImage() {}

    public AppImage(long ID, ImageType type) {
        this.id = ID;
        this.type = type;
    }
}
