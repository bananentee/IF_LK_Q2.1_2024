package server.cryptography;

import java.math.BigInteger;

public class RSA {

    public static void main(String[] args) {
        RSA rsa = new RSA(157, 211); // maximal dreistellige(!) Primzahlen
        rsa.createKeys();
        long encodedMessage = rsa.encode(66, rsa.getPublicKey());
        System.out.println(encodedMessage);
        System.out.println(rsa.decode(encodedMessage, rsa.getPrivateKey()));
    }

    private final long p;
    private final long q;

    private long[] privateKey;
    private long[] publicKey;


    RSA(long p, long q) {
        this.p = p;
        this.q = q;
    }

    public void createKeys() {
        long n = p * q;
        long phiN = (p - 1) * (q - 1);
        long e = findCoprime(n, phiN);
        long d = findModInverse(e, phiN);
        privateKey = new long[]{n, d};
        publicKey = new long[]{n, e};
    }

    public long encode(long message, long[] keys) {
        return modExponentiation(message, keys[1], keys[0]);
    }

    public long decode(long encodedM, long[] keys) {
        return modExponentiation(encodedM, keys[1], keys[0]);
    }

    // Funktion, um den größten gemeinsamen Teiler (ggT) zu berechnen @gpt
    private long ggT(long x, long y) {
        while (y != 0) {
            long temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }

    // Funktion, um eine Zahl zu finden, die teilerfremd zu beiden `a` und `b` ist @gpt
    private long findCoprime(long a, long b) {
        for (long e = 2; ; e++) { // Starte bei 2, da 1 nach Definition zu allem teilerfremd ist
            if (ggT(e, a) == 1 && ggT(e, b) == 1) {
                return e;
            }
        }
    }


    // Erweiterter euklidischer Algorithmus zur Berechnung des modularen Inversen @gpt
    private long findModInverse(long e, long phiN) {
        long t = 0, newT = 1;
        long r = phiN, newR = e;

        while (newR != 0) {
            long quotient = r / newR;

            // Tauschen t und newT
            long temp = newT;
            newT = t - quotient * newT;
            t = temp;

            // Tauschen r und newR
            temp = newR;
            newR = r - quotient * newR;
            r = temp;
        }

        // Prüfen, ob der ggT von e und n 1 ist
        if (r > 1) {
            return -1;  // Kein inverses Element existiert
        }

        // Stellen Sie sicher, dass t positiv ist
        if (t < 0) {
            t = t + phiN;
        }

        return t;
    }

    // Methode zur effizienten Berechnung von großen Exponenten, da Math.pow ein Limit hat @gpt
    private long modExponentiation(long base, long exponent, long modulus) {
        if (modulus == 1) return 0;
        long result = 1;
        base = base % modulus;
        while (exponent > 0) {
            if ((exponent % 2) == 1) { // Wenn der Exponent ungerade ist, multipliziere das Ergebnis mit der Basis
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1; // Exponent durch 2 teilen
            base = (base * base) % modulus; // Basis quadrieren
        }
        return result;
    }

    public long[] getPrivateKey() {
        return privateKey;
    }

    public long[] getPublicKey() {
        return publicKey;
    }

}
