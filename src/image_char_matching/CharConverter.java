/**
 * The image_char_matching package contains classes related to character matching
 * based on image brightness.
 * It includes algorithms for matching characters to image brightness values.
 */
package image_char_matching;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Inspired by, and partly copied from
 * https://github.com/korhner/asciimg/blob/95c7764a6abe0e893fae56b3b6b580e09e1de209/src
 * /main/java/io/korhner/asciimg/image/AsciiImgCache.java
 * described in the blog:
 * https://dzone.com/articles/ascii-art-generator-java
 * Adaptations made by Dan Nirel and again by Rachel Behar.
 * The class converts characters to a binary "image" (2D array of booleans).
 */
public class CharConverter {
    private static final double X_OFFSET_FACTOR = 0.2;
    private static final double Y_OFFSET_FACTOR = 0.75;
    private static final String FONT_NAME = "Courier New";
    /**
     * Default pixel resolution.
     */
    public static final int DEFAULT_PIXEL_RESOLUTION = 16;
    /**
     * CharConverter constructor.
     */
    public CharConverter(){};

    /**
     * Converts a given character into a square black and white image represented by a
     * 2D array of booleans.The character is rendered according to how it looks in
     * the specified font.
     * The dimension of the image in pixels is determined by the default pixel resolution.
     * @param c The character to render into a boolean matrix.
     * @return A 2D boolean array representing the black and white image of the character.
     */
    public static boolean[][] convertToBoolArray(char c) {
        BufferedImage img = getBufferedImage(c, FONT_NAME, DEFAULT_PIXEL_RESOLUTION);
        boolean[][] matrix = new boolean[DEFAULT_PIXEL_RESOLUTION][DEFAULT_PIXEL_RESOLUTION];
        for(int y = 0 ; y < DEFAULT_PIXEL_RESOLUTION ; y++) {
            for(int x = 0 ; x < DEFAULT_PIXEL_RESOLUTION ; x++) {
                matrix[y][x] = img.getRGB(x, y) == 0; //is the color black
            }
        }
        return matrix;
    }

    private static BufferedImage getBufferedImage(char c, String fontName, int pixelsPerRow) {
        String charStr = Character.toString(c);
        Font font = new Font(fontName, Font.PLAIN, pixelsPerRow);
        BufferedImage img = new BufferedImage(pixelsPerRow, pixelsPerRow, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setFont(font);
        int xOffset = (int)Math.round(pixelsPerRow *X_OFFSET_FACTOR);
        int yOffset = (int)Math.round(pixelsPerRow *Y_OFFSET_FACTOR);
        g.drawString(charStr, xOffset, yOffset);
        return img;
    }
}
