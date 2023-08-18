package io.collective.basic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private final String previousHash;
    private final long timestamp;
    private final int nonce;
    private final String hash;
    public Block(String previousHash, long timestamp, int nonce) {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.hash = calculateHash();
    }
    public String getPreviousHash() {
        return this.previousHash;
    }
    public long getTimestamp() {
        return this.timestamp;
    }
    public int getNonce() {
        return this.nonce;
    }
    public String getHash() {
       return this.hash;
    }

    public String calculatedHash( ) {
        return calculateHash();
    }

    /// Supporting functions that you'll need.
    public String calculateHash() {

        try {
            String toBeEncrypted = this.previousHash + this.timestamp + this.nonce;
            MessageDigest encryptAlgo = MessageDigest.getInstance("SHA-256");
            byte[] encryptedBytes = encryptAlgo.digest(toBeEncrypted.getBytes());
            StringBuilder encrypted = new StringBuilder();
            for (byte b : encryptedBytes) {
                encrypted.append(String.format("%02x", b));
            }
            return encrypted.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Err");
            return null;
        }

    }
}