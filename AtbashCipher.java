import java.util.Scanner;

public class AtbashCipher {


    public static String atbashCipher(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isUpperCase(ch)) {
                result.append((char) ('A' + ('Z' - ch)));
            } else if (Character.isLowerCase(ch)) {
                result.append((char) ('a' + ('z' - ch)));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the text to encrypt/decrypt using Atbash Cipher:");
        String input = sc.nextLine();

        String output = atbashCipher(input);

        System.out.println("Result: " + output);

        sc.close();
    }
}
