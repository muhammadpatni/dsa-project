public class StyledConsoleOutput {

    // ANSI escape codes
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String UNDERLINE = "\u001B[4m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    // Function to print styled text
    public static void printStyled(String text, boolean isBold, boolean isUnderline, String color) {
        StringBuilder style = new StringBuilder();

        // Apply bold if true
        if (isBold) {
            style.append(BOLD);
        }

        // Apply underline if true
        if (isUnderline) {
            style.append(UNDERLINE);
        }

        // Apply color
        switch (color.toLowerCase()) {
            case "black":
                style.append(BLACK);
                break;
            case "red":
                style.append(RED);
                break;
            case "green":
                style.append(GREEN);
                break;
            case "yellow":
                style.append(YELLOW);
                break;
            case "blue":
                style.append(BLUE);
                break;
            case "magenta":
                style.append(MAGENTA);
                break;
            case "cyan":
                style.append(CYAN);
                break;
            case "white":
                style.append(WHITE);
                break;
            default:
                break;
        }

        // Print the styled text
        System.out.println(style + text + RESET);
    }

}
