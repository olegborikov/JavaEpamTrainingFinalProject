package com.borikov.bullfinch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class PasswordEncryption {
    private static final String ENCRYPTION_ALGORITHM = "SHA-1";
    private static final Logger LOGGER = LogManager.getLogger();

    private PasswordEncryption() {
    }

    public static Optional<String> encrypt(String password) {
        Optional<String> encryptedPassword = Optional.empty();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] passwordEncodedBytes = messageDigest.digest();
            BigInteger passwordBigInt = new BigInteger(1, passwordEncodedBytes);
            encryptedPassword = Optional.of(passwordBigInt.toString(16));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.ERROR, "Error while encrypt password");
        }
        return encryptedPassword;
    }
}
