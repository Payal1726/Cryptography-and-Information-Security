//import java.util.* ;
import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman{
    public static boolean isPrimitiveRoot(BigInteger g, BigInteger p) 
    {
        int prime = p.intValue(); 
        int alpha = g.intValue();

        boolean[] visited = new boolean[prime];
        int value = 1;

        for (int k = 1; k < prime; k++) 
        {
            value = (value * alpha) % prime;

            if (visited[value]) 
            {
                return false; 
            }
            visited[value] = true;
        }
        for (int i = 1; i < prime; i++) 
        {
            if (!visited[i]) 
            {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a prime number (q): ");
        BigInteger prime = sc.nextBigInteger();

        System.out.print("Enter a primitive root modulo q (alpha): ");
        BigInteger g = sc.nextBigInteger();

        if (!isPrimitiveRoot(g, prime)) 
        {
            System.out.println("\n" + g + " is not a valid primitive root modulo " + prime);
            sc.close();
            return; 
        } 
        else 
        {
            System.out.println("\n" + g + " is a valid primitive root modulo " + prime);
        }

        System.out.print("Enter value of Xa: ");
        BigInteger privateKeyAlice = sc.nextBigInteger();

        System.out.print("Enter value of Xb: ");
        BigInteger privateKeyBob = sc.nextBigInteger();

        BigInteger publicKeyAlice = g.modPow(privateKeyAlice, prime); 
        BigInteger publicKeyBob   = g.modPow(privateKeyBob, prime);   

        System.out.println("\nPublic Key Calculation");
        System.out.println("Xa's Public Key: "+ publicKeyAlice);
        System.out.println("Xb's Public Key: "+ publicKeyBob);

        BigInteger sharedSecretAlice = publicKeyBob.modPow(privateKeyAlice, prime); 
        BigInteger sharedSecretBob   = publicKeyAlice.modPow(privateKeyBob, prime); 

        System.out.println("\n Shared Secret Calculation");
        System.out.println("Shared Secret (Ya): "+ sharedSecretAlice);
        System.out.println("Shared Secret (Yb): "+ sharedSecretBob);

        if (sharedSecretAlice.equals(sharedSecretBob)) 
        {
            System.out.println("\n Both parties share the same secret key: " + sharedSecretAlice);
        } 
        else 
        {
            System.out.println("\n Secrets do not match.");
        }
        sc.close();
    }
}
