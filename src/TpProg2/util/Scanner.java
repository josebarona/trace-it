package TpProg2.util;

public class Scanner {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);

    private Scanner() { }

    /** Displays the given message and waits for user to enter some text.
     * @param message to be displayed.
     * @return text entered by the user.
     */
    public static String getString(String message) {
        System.out.print(message);
        final String result = scanner.nextLine().trim();
        if(result.isEmpty()) {
            System.out.println("Please enter a text.");
            return getString(message);
        }
        return result;
    }

    /** Displays the given message and waits for user to enter a character.
     * @param message to be displayed.
     * @return char entered by the user.
     */
    public static char getChar(String message) {
        return getString(message).charAt(0);
    }

    /** Displays the given message and waits for user to enter an int.
     * @param message to be displayed.
     * @return integer entered by the user.
     */
    public static int getInt(String message) {
        System.out.print(message);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer.");
            return getInt(message);
        }
    }

    /** Displays the given message and waits for user to enter a long.
     * @param message to be displayed.
     * @return long entered by the user.
     */
    public static long getLong(String message) {
        System.out.print(message);
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a long.");
            return getLong(message);
        }
    }

    /** Displays the given message and waits for user to enter a float.
     * @param message to be displayed.
     * @return float entered by the user.
     */
    public static float getFloat(String message) {
        System.out.print(message);
        try {
            return Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a float.");
            return getFloat(message);
        }
    }

    /** Displays the given message and waits for user to enter a double.
     * @param message to be displayed.
     * @return double entered by the user.
     */
    public static double getDouble(String message) {
        System.out.print(message);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a double.");
            return getDouble(message);
        }
    }

    //Particulares

    public static int getDate(String message) {
        System.out.print(message);
        try {
            int date = Integer.parseInt(scanner.nextLine());
            if (Integer.toString(date).length() != 2){
                System.out.println("Please enter two digits for the date.");
                return getDate(message);
            }else{return date;}
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer.");
            return getInt(message);
        }
    }


}
