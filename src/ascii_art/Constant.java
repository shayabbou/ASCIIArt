/**
 * The ascii_art package contains classes related to generating ASCII art from images.
 * It includes algorithms for converting images into ASCII representations.
 */
package ascii_art;

/**
 * The Constant class provides a set of constant values used throughout the application.
 * These constants include error messages, default settings, command names, and other static values.
 */
public class Constant {
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private Constant(){};

    /**
     * The file extension for PNG images.
     */
    final static String IMAGE_PNG = ".png";

    /**
     * The file extension for JPEG images.
     */
    final static String IMAGE_JPEG = ".jpeg";

    /**
     * Command string for increasing resolution.
     */
    final static String UP = "up";

    /**
     * Command string for decreasing resolution.
     */
    final static String DOWN = "down";

    /**
     * Command string for applying operation to all items.
     */
    final static String ALL = "all";

    /**
     * Character representing space.
     */
    final static char SPACE_CHAR = ' ';

    /**
     * Character representing tilde (~).
     */
    final static char TILDA = '~';

    /**
     * String representation of space character.
     */
    final static String SPACE_WORD = "space";

    /**
     * Hyphen character.
     */
    final static String HYPHEN = "-";

    /**
     * Length of input for certain commands - add and remove
     */
    final static int INPUT_LEN = 3;

    /**
     * Syntax for printing resolution.
     */
    final static String RESOLUTION_PRINT_SYNTAX = "Resolution set to %d.";

    /**
     * Arrow symbol used in command line interface.
     */
    final static String ARROW = ">>> ";

    /**
     * Error message for incorrect command.
     */
    final static String COMMAND_ERROR = "Did not execute due to incorrect command.";

    /**
     * Error message for incorrect add command format.
     */
    final static String ADD_FORMAT_ERROR = "Did not add due to incorrect format.";

    /**
     * Error message for incorrect remove command format.
     */
    final static String REMOVE_FORMAT_ERROR = "Did not remove due to incorrect format.";

    /**
     * Error message for incorrect resolution format.
     */
    final static String RESOLUTION_FORMAT_ERROR = "Did not change resolution due to incorrect format.";

    /**
     * Error message for resolution exceeding boundaries.
     */
    final static String RESOLUTION_BOUNDARIES_ERROR = "Did not change resolution due to" +
            " exceeding boundaries.";

    /**
     * Error message for problem with image file.
     */
    final static String IMAGE_ERROR = "Did not execute due to problem with image file.";

    /**
     * Error message for incorrect output method format.
     */
    final static String OUTPUT_ERROR = "Did not change output method due to incorrect format.";

    /**
     * Error message for empty charset.
     */
    final static String EMPTY_CHARS = "Did not execute. Charset is empty.";

    /**
     * Default image filename.
     */
    final static String DEFAULT_IMAGE = "cat.jpeg";

    /**
     * Default resolution value.
     */
    final static int DEFAULT_RESOLUTION = 128;

    /**
     * Default charset array.
     */
    final static char[] DEFAULT_CHARS_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * Output format for console.
     */
    final static String CONSOLE_OUTPUT = "console";

    /**
     * Output format for HTML.
     */
    final static String HTML_OUTPUT = "html";

    /**
     * Default HTML font.
     */
    final static String HTML_FONT = "Courier New";

    /**
     * HTML output file name.
     */
    final static String HTML_OUTPUT_FILE = "out.html";

    /**
     * Command name for displaying characters.
     */
    final static String CHARS_COMMAND = "chars";

    /**
     * Command name for adding characters.
     */
    final static String ADD_COMMAND = "add";

    /**
     * Command name for removing characters.
     */
    final static String REMOVE_COMMAND = "remove";

    /**
     * Command name for changing resolution.
     */
    final static String RES_COMMAND = "res";

    /**
     * Command name for exiting the program.
     */
    final static String EXIT_COMMAND = "exit";

    /**
     * Command name for setting image.
     */
    final static String IMAGE_COMMAND = "image";

    /**
     * Command name for changing output method.
     */
    final static String OUTPUT_COMMAND = "output";

    /**
     * Command name for generating ASCII art.
     */
    final static String ASCIIART_COMMAND = "asciiArt";
}
