package com.borikov.final_project.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    private PasswordEncryption() {
    }

    public static String encrypt(String password) throws NoSuchAlgorithmException {// TODO: 04.09.2020 what to do with exception
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] passwordEncodedBytes = messageDigest.digest();
        BigInteger passwordBigInt = new BigInteger(1, passwordEncodedBytes);
        String encryptedPassword = passwordBigInt.toString(16);
        return encryptedPassword;
    }
}
