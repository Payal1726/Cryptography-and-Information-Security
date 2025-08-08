import java.util.*;

public class HillCipher {
    
    public static int charToNum(char c) {
        return c - 'A';
    }

    public static char numToChar(int num) {
        return (char) (num + 'A');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        plaintext = plaintext.replace('J', 'I'); 

        if (plaintext.length() % 2 != 0) {
            plaintext += "X";
        }

        int[][] keyMatrix = new int[2][2];
        System.out.println("Enter 4 numbers for 2x2 key matrix:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = sc.nextInt();
            }
        }

        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] block = new int[2];
            block[0] = charToNum(plaintext.charAt(i));
            block[1] = charToNum(plaintext.charAt(i + 1));

            int[] cipherBlock = new int[2];
            cipherBlock[0] = (keyMatrix[0][0] * block[0] + keyMatrix[0][1] * block[1]) % 26;
            cipherBlock[1] = (keyMatrix[1][0] * block[0] + keyMatrix[1][1] * block[1]) % 26;

            ciphertext.append(numToChar(cipherBlock[0]));
            ciphertext.append(numToChar(cipherBlock[1]));
        }

        System.out.println("Ciphertext: " + ciphertext.toString());

        sc.close();
    }
}