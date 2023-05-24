package edu.pe.usil.model;

import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Data
public class Employee {
    private int employee_id;
    private String name;
    private String lastname;
    private String username;
    private String password;

    public String getUsername() {
        return name+"."+lastname;
    } // custom username
    public void setUsername(String username) {
        throw new UnsupportedOperationException("Cannot set username directly. It is derived from name and lastname.");
    } // custom username

    //PASSWORD ENCRYPT
    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = encryptPassword(password);
    }
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    //PASSWORD VALIDATION
    public boolean verifyPassword(String password) throws NoSuchAlgorithmException {
        String encryptedPassword = encryptPassword(password);
        return this.password.equals(encryptedPassword);
    }
}

