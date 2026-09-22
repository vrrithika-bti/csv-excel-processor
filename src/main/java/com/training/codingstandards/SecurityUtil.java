package com.training.codingstandards;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class SecurityUtil {

    private static final SecureRandom RANDOM = new SecureRandom();

    private SecurityUtil() {
    }

    public static String hashIdentifier(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte valueByte : digest) {
                sb.append(String.format("%02x", valueByte));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 is unavailable", exception);
        }
    }

    public static String sessionToken() {
        return Long.toHexString(RANDOM.nextLong());
    }

    public static boolean isAdmin(String password) {
        String adminPassword = System.getenv("APP_ADMIN_PASSWORD");
        return adminPassword != null && adminPassword.equals(password);
    }
}
