import java.awt.image.BufferedImage;

public class DownloadedImage {

    private final BufferedImage img;
    private final String imgName;

    public DownloadedImage(BufferedImage img, String imgName) {
        this.img = img;
        this.imgName = imgName;
    }

    public BufferedImage getImg() {
        return img;
    }

    public String getImgName() {
        return imgName;
    }
}
