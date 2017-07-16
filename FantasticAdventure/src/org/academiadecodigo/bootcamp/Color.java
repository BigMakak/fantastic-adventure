package org.academiadecodigo.bootcamp;

/**
 * Created by codecadet on 16/07/17.
 */
public enum Color {

    // "\u001B[" Escape Code, (f) Foreground Color Code, (b) Background Color Code

    INTRO("\u001B[33;40m"),   // (f) yellow : (b) black
    BLACK("\u001B[30;40m"),   // (f) white  : (b) black
    RED("\u001B[37;41m"),     // (f) white  : (b) red
    GREEN("\u001B[30;42m"),   // (f) black  : (b) green
    YELLOW("\u001B[30;43m"),  // (f) black  : (b) yellow
    BLUE("\u001B[37;44m"),    // (f) white  : (b) blue
    MAGENTA("\u001B[37;45m"), // (f) white  : (b) magenta
    CYAN("\u001B[30;46m"),    // (f) black  : (b) cyan
    WHITE("\u001B[30;47m");   // (f) black  : (b) white


    private String colorMood;

    Color(String colormood) {

        this.colorMood = colormood;
    }

    public String getColorMood() {

        return colorMood;
    }

    public static String pickColor() {

        int result = RandomGenerator.randomGenerator(1, Color.values().length);
        return values()[result].getColorMood();
    }
}
