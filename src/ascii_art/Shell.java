/**
 * The ascii_art package contains classes related to generating ASCII art from images.
 * It includes algorithms for converting images into ASCII representations.
 */
package ascii_art;

import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.EditImage;
import image.Image;
import image_char_matching.SubImgCharMatcher;
import java.io.IOException;

/**
 * The Shell class represents the command-line interface for the ASCII Art application.
 * It handles user input, image processing, and ASCII art generation based on user commands.
 */
public class Shell {
    private SubImgCharMatcher charMatcher;
    private int resolution;
    private AsciiOutput output;
    private int minRes;
    private int maxRes;
    private Image image;
    private static boolean changeInImage = false;
    private EditImage editImage;
    /**
     * Constructs a Shell object with default settings and initializes necessary components.
     */
    public Shell(){
        charMatcher = new SubImgCharMatcher(Constant.DEFAULT_CHARS_ARRAY);
        resolution = Constant.DEFAULT_RESOLUTION;
        output = new ConsoleAsciiOutput();
        try {
            image = new Image(Constant.DEFAULT_IMAGE);
            editImage = new EditImage(image, resolution);
        }
        catch (IOException io){
            System.out.println(Constant.IMAGE_ERROR);
        }
        initMinMaxRes();
    }

    /**
     * Runs the ASCII Art application, continuously accepting and processing user commands.
     */
    public void run() {
        System.out.print(Constant.ARROW);
        String input = KeyboardInput.readLine();
        while (!input.equals(Constant.EXIT_COMMAND)) {
            String[] inputArr = input.split(" ");
            switchCases(inputArr);
            System.out.print(Constant.ARROW);
            input = KeyboardInput.readLine();
        }
    }

    /**
     * The main method to start the ASCII Art application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.run();
    }
    private void initMinMaxRes(){
        maxRes = editImage.getPaddImage().getWidth();
        minRes = Math.max(maxRes/editImage.getPaddImage().getHeight(),1);
    }
    private void switchCases(String[] inputArr) {
        switch (inputArr[0]) {
            case (Constant.CHARS_COMMAND):
                charsInput(inputArr);
                break;
            case (Constant.ADD_COMMAND):
                catchAddException(inputArr);
                break;
            case (Constant.REMOVE_COMMAND):
                catchRemoveException(inputArr);
                break;
            case (Constant.RES_COMMAND):
                catchResException(inputArr);
                break;
            case (Constant.IMAGE_COMMAND):
                catchImageException(inputArr);
                break;
            case (Constant.OUTPUT_COMMAND):
                catchOutputException(inputArr);
                break;
            case (Constant.ASCIIART_COMMAND):
                catchAsciiArtException();
                break;
            default:
                System.out.println(Constant.COMMAND_ERROR);
                break;
        }
    }
    private void charsInput(String [] inputArr){
        if (inputArr.length > 1){
            System.out.println(Constant.COMMAND_ERROR);
        } else {
            for (Character c : charMatcher.getChar()) {
                System.out.print(String.format("%c ", c));
            }
            System.out.println();
        }
    }

    private void catchAsciiArtException(){
        try {
            runAlgo();
        }
        catch (IllegalArgumentException e){
            System.out.println(Constant.EMPTY_CHARS);
        }
        catch (IOException io){
            System.out.println(Constant.IMAGE_ERROR);
        }
    }
    private void runAlgo() throws IllegalArgumentException , IOException{
        if(charMatcher.getChar().isEmpty()){
            throw new IllegalArgumentException();
        }
        if (changeInImage) {
            editImage = new EditImage(image, resolution);
            changeInImage = false;
        }
        AsciiArtAlgorithm asciiArtAlgorithm = new AsciiArtAlgorithm(editImage,resolution,charMatcher);
        char[][] algoResult = asciiArtAlgorithm.run();
        output.out(algoResult);
    }
    private void catchOutputException(String [] inputArr){
        try{
            outputInputValidation(inputArr);
        }
        catch (IllegalArgumentException e){
            System.out.println(Constant.OUTPUT_ERROR);
        }
    }
    private void outputInputValidation(String [] inputArr) throws IllegalArgumentException {
        if (inputArr.length == 1) {
            throw new IllegalArgumentException();
        }
        String rest = inputArr[1];
        if (rest.equals(Constant.HTML_OUTPUT)) {
            if (!(output instanceof HtmlAsciiOutput)) {
                output = new HtmlAsciiOutput(Constant.HTML_OUTPUT_FILE, Constant.HTML_FONT);
            }
        } else if (rest.equals(Constant.CONSOLE_OUTPUT)) {
            if (!(output instanceof ConsoleAsciiOutput)) {
                output = new ConsoleAsciiOutput();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    private void catchImageException(String [] inputArr){
        try {
            imageInputValidation(inputArr);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(Constant.IMAGE_ERROR);
        }
    }
    private void imageInputValidation(String [] inputArr) throws IOException, IllegalArgumentException{
        if (inputArr.length == 1) {
            throw new IllegalArgumentException();
        }
        String rest = inputArr[1];
        if(rest.contains(Constant.IMAGE_PNG)|rest.contains(Constant.IMAGE_JPEG)){
            image = new Image(rest);
            editImage = new EditImage(image, resolution);
            changeInImage = false;
            initMinMaxRes();
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    private void catchResException(String[] inputArr){
        try {
            resInputValidation(inputArr);
        } catch (IllegalArgumentException e) {
            System.out.println(Constant.RESOLUTION_FORMAT_ERROR);
        }
    }
    private void resInputValidation(String[] inputArr) throws IllegalArgumentException{
        if (inputArr.length == 1) {
            throw new IllegalArgumentException();
        }
        String res = inputArr[1];
        if (res.equals(Constant.UP)){
            if(resolutionBoundaries(resolution*2)){
                resolution *= 2;
                System.out.println(String.format(Constant.RESOLUTION_PRINT_SYNTAX,resolution));
            }
        }
        else if (res.equals(Constant.DOWN)){
            if(resolutionBoundaries(resolution/2)){
                resolution /= 2;
                System.out.println(String.format(Constant.RESOLUTION_PRINT_SYNTAX,resolution));
            }
        }
        else{
            throw new IllegalArgumentException();
        }
        changeInImage = true;
    }
    private boolean resolutionBoundaries(int resolution) {
        if (resolution>=minRes && resolution<=maxRes){
            return true;
        }
        System.out.println(Constant.RESOLUTION_BOUNDARIES_ERROR);
        return false;
    }
    private void catchRemoveException(String [] inputArr){
        try {
            removeInputValidation(inputArr);
        } catch (IllegalArgumentException e) {
            System.out.println(Constant.REMOVE_FORMAT_ERROR);
        }
    }
    private void removeInputValidation(String[] inputArr) throws IllegalArgumentException{
        if (inputArr.length==1) {
            throw new IllegalArgumentException();
        }
        String rest = inputArr[1];
        if(rest.length() == 1) {
            charMatcher.removeChar(rest.charAt(0));
        } else if(rest.equals(Constant.ALL)) {
            removeSequence(Constant.SPACE_CHAR,Constant.TILDA);
        } else if(rest.equals(Constant.SPACE_WORD)) {
            charMatcher.removeChar(Constant.SPACE_CHAR);
        } else if (rest.contains(Constant.HYPHEN) && rest.length() == Constant.INPUT_LEN) {
            if(rest.charAt(0)<rest.charAt(2)) {
                removeSequence(rest.charAt(0),rest.charAt(2));
            } else {
                removeSequence(rest.charAt(2),rest.charAt(0));
            }
        } else{
            throw new IllegalArgumentException();
        }
    }
    private void removeSequence(char start,char end){
        for(char i=start;i<=end;i++){
            charMatcher.removeChar(i);
        }

    }
    private void catchAddException(String[] inputArr){
        try{
            addInputValidation(inputArr);
        }
        catch (IllegalArgumentException e){
            System.out.println(Constant.ADD_FORMAT_ERROR);
        }
    }
    private void addInputValidation(String[] inputArr) throws IllegalArgumentException{
        if (inputArr.length == 1) {
            throw new IllegalArgumentException();
        }
        String rest = inputArr[1];
        if(rest.length() == 1) {
            charMatcher.addChar(rest.charAt(0));
        } else if(rest.equals(Constant.ALL)) {
            addSequence(Constant.SPACE_CHAR,Constant.TILDA);
        } else if(rest.equals(Constant.SPACE_WORD)) {
            charMatcher.addChar(Constant.SPACE_CHAR);
        } else if (rest.contains(Constant.HYPHEN) && rest.length() == Constant.INPUT_LEN) {
            if(rest.charAt(0)<rest.charAt(2)) {
                addSequence(rest.charAt(0),rest.charAt(2));
            } else {
                addSequence(rest.charAt(2),rest.charAt(0));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    private void addSequence(char start,char end){
        for(char i=start;i<=end;i++){
            charMatcher.addChar(i);
        }
    }

}
