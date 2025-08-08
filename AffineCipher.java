import java.util.Scanner;

public class AffineCipher{
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1;
    }

    static String encrypt(String plaintext, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (char ch : plaintext.toUpperCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                int x = ch - 'A';
                char enc = (char) (((a * x + b) % 26) + 'A');
                result.append(enc);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    static String decrypt(String ciphertext, int a, int b) {
        int a_inv = modInverse(a, 26);
        if (a_inv == -1) {
            return "Invalid key. No modular inverse for a = " + a;
        }

        StringBuilder result = new StringBuilder();
        for (char ch : ciphertext.toUpperCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                int y = ch - 'A';
                char dec = (char) (((a_inv * (y - b + 26)) % 26) + 'A');
                result.append(dec);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    static boolean isCoprime(int a, int m) {
        for (int i = 2; i <= Math.min(a, m); i++) {
            if (a % i == 0 && m % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter key 'a' must be coprime with 26: ");
        int a = sc.nextInt();

        if (!isCoprime(a, 26)) {
            System.out.println("Error: 'a' must be coprime with 26.");
            return;
        }

        System.out.print("Enter key 'b': ");
        int b = sc.nextInt();

        String encrypted = encrypt(plaintext, a, b);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, a, b);
        System.out.println("Decrypted: " + decrypted);
        sc.close();
    }
}