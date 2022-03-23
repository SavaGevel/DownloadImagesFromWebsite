import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        getImagesFromWebSite("https://lenta.ru");

    }


    public static void getImagesFromWebSite(String URL) {

        String path = "/Users/savelijgevel/IdeaProjects/java_basics/13_FilesAndNetwork/homework_13.4/images";
        File imagesFolder = new File(path);

        try {

            if(!Files.exists(Path.of(path), LinkOption.NOFOLLOW_LINKS)) {
                imagesFolder.mkdir();
            }

            Document doc = Jsoup.connect(URL)
                    .userAgent("").
                    get();
            doc.body().getElementsByTag("img").stream()
                    .map(element -> element.attr("abs:src"))
                    .map(src -> {
                        try {
                            return new DownloadedImage(ImageIO.read(new URL(src)), src.substring(src.lastIndexOf("/")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .forEach(img -> {
                                try {
                                    ImageIO.write(img.getImg(), "png", new File(path + img.getImgName()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

            Arrays.stream(Objects.requireNonNull(imagesFolder.listFiles()))
                    .forEach(file -> System.out.println(file.getName()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
