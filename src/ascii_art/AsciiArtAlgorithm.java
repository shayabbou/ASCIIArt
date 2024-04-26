/**
 * The ascii_art package contains classes related to generating ASCII art from images.
 * It includes algorithms for converting images into ASCII representations.
 */
package ascii_art;

import image.EditImage;
import image.Image;
import image_char_matching.SubImgCharMatcher;
import java.util.*;
/**
 * This class generates ASCII art from images.
 */
public class AsciiArtAlgorithm {
    private SubImgCharMatcher charMatcher;
    private EditImage editImage;
    private int resolution;

    /**
     * Constructs an AsciiArtAlgorithm object with the provided image, edited image,
     * resolution, and character matcher.
     * @param editImage The edited EditImage object.
     * @param resolution An int representing the resolution of the ASCII art.
     * @param charMatcher The SubImgCharMatcher object for character matching.
     */
    public AsciiArtAlgorithm(EditImage editImage, int resolution, SubImgCharMatcher charMatcher){
        this.charMatcher = charMatcher;
        this.resolution = resolution;
        this.editImage = editImage;
    }

    /**
     * Generates ASCII art from the image.
     * @return A 2D array of char representing the ASCII art.
     */
    public char [][] run(){
        LinkedHashMap<Image, Double> slicedImages = editImage.getSubImages();
        int imageHeight = editImage.getPaddImage().getHeight();
        int imageWidth = editImage.getPaddImage().getWidth();
        int rows = imageHeight/(imageWidth/resolution);
        char[][] asciiImage = new char[rows][resolution];
        int row = 0;
        int col = 0;
        for (Map.Entry<Image,Double> slice : slicedImages.entrySet()){
            if(col < resolution){
                asciiImage[row][col] = charMatcher.getCharByImageBrightness(slice.getValue());
                col++;
            } else {
                col = 0;
                row++;
                asciiImage[row][col] = charMatcher.getCharByImageBrightness(slice.getValue());
                col++;
            }
        }
        return asciiImage;
    }
}
