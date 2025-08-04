import java.util.Scanner;

public class VigenereCipher {

    
    public static String encrypt(String plaintext, String keyword) {
        StringBuilder ciphertext = new StringBuilder();
        keyword = keyword.toUpperCase();

        int keyIndex = 0;
        int keyLen = keyword.length();

        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);

            if (Character.isLetter(p)) {
                boolean isUpper = Character.isUpperCase(p);
                char k = keyword.charAt(keyIndex % keyLen);

                int pIndex = Character.toUpperCase(p) - 'A';
                int kIndex = k - 'A';

                int cIndex = (pIndex + kIndex) % 26;
                char c = (char) (cIndex + (isUpper ? 'A' : 'a'));

                ciphertext.append(c);
                keyIndex++; 
            } else {
                ciphertext.append(p); 
            }
        }

        return ciphertext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        String encrypted = encrypt(plaintext, keyword);
        System.out.println("Ciphertext: " + encrypted);

        sc.close();
    }
}