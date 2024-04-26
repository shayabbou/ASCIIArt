/**
 * The image_char_matching package contains classes related to character
 * matching based on image brightness.
 * It includes algorithms for matching characters to image brightness values.
 */
package image_char_matching;

import java.util.*;
/**
 * This class is responsible for matching characters based on image brightness.
 * It provides methods to add, remove, and retrieve characters,
 * as well as to get a character based on image brightness.
 */
public class SubImgCharMatcher {
    private static HashMap<Character,Double> allAsciiBrightness = new HashMap<>();
    private TreeMap<Double, TreeSet<Character>> origCharMap;
    private TreeSet<Character> allChars;

     static {
        for(int i = 32; i < 127; i++) {
            allAsciiBrightness.put((char)i, calculateBrightness((char)i));
        }
    }
    /**
     * Constructs a SubImgCharMatcher object with the provided charset.
     * @param charset An array of characters representing the charset to
     *              initialize the matcher with.
     */
    public SubImgCharMatcher(char[] charset) {
        this.origCharMap = new TreeMap<>();
        this.allChars = new TreeSet<>();
        for (char i : charset) {
            addChar(i);
        }
    }

    /**
     * Retrieves a character based on the provided image brightness.
     * @param brightness A double value representing the brightness of the image.
     * @return A char representing the matched character.
     */
    public char getCharByImageBrightness(double brightness) {
        double min = origCharMap.firstKey();
        double max = origCharMap.lastKey();
        double reversBrightness = ((max - min) * brightness) + min;
        double floor = origCharMap.floorKey(reversBrightness);
        double ceiling = origCharMap.ceilingKey(reversBrightness);
        if ((reversBrightness - floor) < (ceiling - reversBrightness)) {
            return findMinChar(origCharMap.get(floor));
        } else {
            return findMinChar(origCharMap.get(ceiling));
        }

    }

    /**
     * Retrieves all characters stored in the matcher.
     * @return A TreeSet<Character> containing all characters.
     */
    public TreeSet<Character> getChar() {
        return allChars;
    }

    /**
     * Adds a character to the matcher.
     * @param c The character to add.
     */
    public void addChar(char c) {
        double brightness = allAsciiBrightness.get(c);
        allChars.add(c);
        if (origCharMap.containsKey(brightness)) {
            origCharMap.get(brightness).add(c);
        }
        else {
            TreeSet<Character> charArray = new TreeSet<>();
            charArray.add(c);
            this.origCharMap.put(brightness, charArray);
        }
    }

    /**
     * Removes a character from the matcher.
     * @param c The character to remove.
     */
    public void removeChar(char c) {
        double origBrightness = allAsciiBrightness.get(c);
        if (origCharMap.containsKey(origBrightness)) {
            TreeSet<Character> list = origCharMap.get(origBrightness);
            list.remove(c);
            origCharMap.put(origBrightness, list);
            if (list.isEmpty()) {
                origCharMap.remove(origBrightness);
            } else {
                origCharMap.put(origBrightness, list);
            }
        }
        allChars.remove(c);
    }

    /**
     * Calculates the brightness of a character.
     * @param c The character to calculate brightness for.
     * @return A double value representing the brightness of the character.
     */
    private static double calculateBrightness(char c) {
        //pass over the chars
        int t = 0;
        //for each char create double array of true and false that represent the char image
        boolean[][] boolCharArray = CharConverter.convertToBoolArray(c);
        //row
        for (boolean[] boolArr : boolCharArray) {
            //cols in row
            for (boolean bool : boolArr) {
                if (bool) {
                    t++;
                }
            }
        }
        return (double) t / (CharConverter.DEFAULT_PIXEL_RESOLUTION * CharConverter.DEFAULT_PIXEL_RESOLUTION);
    }
    private char findMinChar(TreeSet<Character> array) {
        int min = 256;
        for (Character c : array) {
            if ((int) c < min) {
                min = (int) c;
            }
        }
        return (char) min;
    }
}