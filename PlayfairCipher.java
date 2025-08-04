import java.util.*;

public class PlayfairCipher {
    static char[][] matrix = new char[5][5];
    static String key;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter key: ");
        key = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');

        generateMatrix();

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');

        String formattedText = formatPlainText(plaintext);
        String cipher = encrypt(formattedText);
        System.out.println("Encrypted Text: " + cipher);
    }

    static void generateMatrix() {
        Set<Character> used = new LinkedHashSet<>();

        for (char c : key.toCharArray()) {
            used.add(c);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J') used.add(c);
        }

        Iterator<Character> it = used.iterator();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = it.next();
            }
        }

        
        System.out.println("Playfair Matrix:");
        for (char[] row : matrix) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
    }

    static String formatPlainText(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            sb.append(a);
            if (i + 1 < text.length()) {
                char b = text.charAt(i + 1);
                if (a == b) {
                    sb.append('X');
                } else {
                    sb.append(b);
                    i++;
                }
            }
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    
    static int[] findPos(char ch) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == ch)
                    return new int[]{i, j};
        return null;
    }

    
    static String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPos(a);
            int[] posB = findPos(b);

            if (posA[0] == posB[0]) {
                
                result.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                result.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) {
            
                result.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                result.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else {
                
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }
}
