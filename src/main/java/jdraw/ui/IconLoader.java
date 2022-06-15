package jdraw.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IconLoader {
    private static final String ICONS_FOLDER_PATH = "/icons/";
    private static final String PLACEHOLDER_IMAGE_PATH = "placeholder.png";
    private final Image placeholderImage;

    private final Map<String, Image> loadedImages;

    public IconLoader() {
        loadedImages = new HashMap<>();
        placeholderImage = getImage(PLACEHOLDER_IMAGE_PATH);
        if(placeholderImage == null)
            throw new NullPointerException("Placeholder image not found");
    }

    public Image getImage(String image) {
        if(image == null || image.equals(""))
            return placeholderImage;

        if(loadedImages.containsKey(image))
            return loadedImages.get(image);

        URL imageURL = IconLoader.class.getResource(ICONS_FOLDER_PATH + image);
        if(imageURL == null)
            return placeholderImage;

        try {
            Image img = ImageIO.read(imageURL);
            loadedImages.put(image, img);
            return img;
        } catch (IOException e) {
            System.out.println("Not found");
            return placeholderImage;
        }
    }

    public Image getPlaceholderImage() {
        return placeholderImage;
    }
}
