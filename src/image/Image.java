/**
 * The image package contains classes related to image processing and manipulation.
 * It includes classes for loading, editing, and analyzing images.
 */
package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A package-private class of the package image.
 * @author Dan Nirel
 */
public class Image {
    private final Color[][] pixelArray;
    private final int width;
    private final int height;

    /**
     * Description: Constructs an Image object from a file.
     * @param filename A String representing the path to the image file.
     * @throws IOException
     */
    public Image(String filename) throws IOException {
        BufferedImage im = ImageIO.read(new File(filename));
        width = im.getWidth();
        height = im.getHeight();
        pixelArray = new Color[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixelArray[i][j] = new Color(im.getRGB(j, i));
            }
        }
    }

    /**
     * Constructs an Image object with the provided pixel array, width, and height.
     * @param pixelArray A 2D array of Color representing the pixels of the image.
     * @param width An int representing the width of the image.
     * @param height An int representing the height of the image.
     */
    public Image(Color[][] pixelArray, int width, int height) {
        this.pixelArray = pixelArray;
        this.width = width;
        this.height = height;
    }

    /**
     * Return the image width.
     * @return The image width.
     */
    public int getWidth() {
        return width;
    }
    /**
     * Return the image height.
     * @return The image height.
     */
    public int getHeight() {
        return height;
    }
    /**
     * Return the pixel (x,y) color.
     * @return The pixel (x,y) color.
     */
    public Color getPixel(int x, int y) {
        return pixelArray[x][y];
    }

    /**
     * Saves the image in to the filename provided
     * @param fileName The name of the file to save the image to.
     */
    public void saveImage(String fileName) {
        // Initialize BufferedImage, assuming Color[][] is already properly populated.
        BufferedImage bufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
                BufferedImage.TYPE_INT_RGB);
        // Set each pixel of the BufferedImage to the color from the Color[][].
        for (int x = 0; x < pixelArray.length; x++) {
            for (int y = 0; y < pixelArray[x].length; y++) {
                bufferedImage.setRGB(y, x, pixelArray[x][y].getRGB());
            }
        }
        File outputfile = new File(fileName + ".jpeg");
        try {
            ImageIO.write(bufferedImage, "jpeg", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
