package com.borikov.bullfinch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class PasswordEncryptor {
    private static final String ENCRYPTION_ALGORITHM = "SHA-1";
    private static final int SIGNUM_DEFAULT = 1;
    private static final int HEXADECIMAL_RADIX = 16;
    private static final Logger LOGGER = LogManager.getLogger();

    private PasswordEncryptor() {
    }

    public static Optional<String> encrypt(String password) {
        Optional<String> encryptedPassword = Optional.empty();
        try {
            MessageDigest messageDigest =
                    MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] passwordEncodedBytes = messageDigest.digest();
            BigInteger passwordBigInt = new BigInteger(SIGNUM_DEFAULT,
                    passwordEncodedBytes);
            encryptedPassword =
                    Optional.of(passwordBigInt.toString(HEXADECIMAL_RADIX));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.ERROR, "Error while encrypting password");
        }
        return encryptedPassword;
    }
}
