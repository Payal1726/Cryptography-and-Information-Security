import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Scanner;

public class RSA_algorithm {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a message to encrypt: ");
        String plaintext = sc.nextLine();

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] ciphertext = encryptCipher.doFinal(plaintext.getBytes());

        System.out.println("\nPublic Key   : " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key  : " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        System.out.println("Ciphertext   : " + Base64.getEncoder().encodeToString(ciphertext));

        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = decryptCipher.doFinal(ciphertext);

        System.out.println("Decrypted    : " + new String(decrypted));

        sc.close();
    }
}
