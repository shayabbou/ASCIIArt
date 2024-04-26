/**
 * The ascii_art package contains classes related to generating ASCII art from images.
 * It includes algorithms for converting images into ASCII representations.
 */
package ascii_art;

import java.util.Scanner;
/**
 * The KeyboardInput class provides a way to read user input from the keyboard.
 * It implements the Singleton design pattern to ensure that only one instance of the Scanner object is created.
 */
class KeyboardInput
{
    private static KeyboardInput keyboardInputObject = null;
    private Scanner scanner;
    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the Scanner object to read input from the standard input
     * stream (keyboard).
     */
    private KeyboardInput()
    {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Returns the singleton instance of the KeyboardInput class.
     * If the instance doesn't exist, it creates a new one.
     * @return The singleton instance of the KeyboardInput class.
     */
    public static KeyboardInput getObject()
    {
        if(KeyboardInput.keyboardInputObject == null)
        {
            KeyboardInput.keyboardInputObject = new KeyboardInput();
        }
        return KeyboardInput.keyboardInputObject;
    }

    /**
     * Reads a line of text from the user's input.
     * @return A String containing the user's input, trimmed to remove
     * leading and trailing whitespace.
     */
    public static String readLine()
    {
        return KeyboardInput.getObject().scanner.nextLine().trim();
    }
}