package Digitact.Backend.Model.Image;

public class ImageString {
    private String content;

    private ImageType type;

    public ImageString(String content, ImageType type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }
}
