import java.util.*;

public class ceaseorcipher {

    static String encrypt(String n, int k) {
        String cp = "";
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);

            if (Character.isUpperCase(c)) {
                c = (char) (((c - 'A' + k) % 26) + 'A');
            } else if (Character.isLowerCase(c)) {
                c = (char) (((c - 'a' + k) % 26) + 'a');
            }
            cp += c;
        }
        return cp;
    }

    static String decrypt(String n, int k) {
        String pt = "";
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);

            if (Character.isUpperCase(c)) {
                c = (char) (((c - 'A' - k + 26) % 26) + 'A');
            } else if (Character.isLowerCase(c)) {
                c = (char) (((c - 'a' - k + 26) % 26) + 'a');
            }
            pt += c;
        }
        return pt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Plaintext:");
        String n = sc.nextLine();

        System.out.println("Enter the key:");
        int k = sc.nextInt();

        String e = encrypt(n, k);
        String d = decrypt(e, k);

        System.out.println("Encrypted message: " + e);
        System.out.println("Decrypted message: " + d);

        sc.close();
    }
}
