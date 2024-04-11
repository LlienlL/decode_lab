package com.example.decode_lab;

import java.math.BigInteger;
import java.util.Random;

public class RSACipher {
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;

    public RSACipher(int length, int bitLength) {
        generateKeyPair(bitLength);
    }

    public int encrypt(int message) {
        BigInteger plaintext = BigInteger.valueOf(message);
        BigInteger ciphertext = plaintext.modPow(e, n);
        return ciphertext.intValue();
    }

    public int decrypt(int ciphertext) {
        BigInteger ciphertextBigInt = BigInteger.valueOf(ciphertext);
        BigInteger plaintext = ciphertextBigInt.modPow(d, n);
        return plaintext.intValue();
    }

    private void generateKeyPair(int bitLength) {
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);
        n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.valueOf(65537); // A common choice for the public exponent
        d = e.modInverse(phi);
    }
}