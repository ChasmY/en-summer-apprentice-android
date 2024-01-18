package com.example.ticketingapp.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class PasswordEncoderService {

    public static String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // Convert bytes to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verifyPassword(String inputPassword, String hashedPassword) {
        // Hash the input password and compare it with the stored hashed password
         return encodePassword(inputPassword).equals(hashedPassword);

//        return Objects.equals(encodePassword(inputPassword), hashedPassword);

    }

}
