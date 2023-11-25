import java.util.Scanner;

public class PalindromeCheck {

    static Scanner sc = new Scanner(System.in);

    static String formatInput(String input) {
        // Remove all whitespace and convert to lowercase
        String formattedInput = input.replaceAll("\\s+", "").toLowerCase();
        // remove all punctuation
        formattedInput = formattedInput.replaceAll("\\p{Punct}", "");
        return formattedInput;
    }

    static boolean palindromecheck(String input) {
        String formattedInput = formatInput(input);
        int length = formattedInput.length();
        for (int i = 0; i < length / 2; i++) {
            // compare first and last char, then second and second last char, etc.
            if (formattedInput.charAt(i) != formattedInput.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Enter a string to check if it is a palindrome (case, whitespace, and punctuation are ignored):");
            String input = sc.nextLine();
            sc.close();

            if (palindromecheck(input)) {
                System.out.println("The string is a palindrome.");
            } else {
                System.out.println("The string is not a palindrome.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}