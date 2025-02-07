package org.llamawow.service.impl;

import org.llamawow.model.ParamsEncrypt;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionService {

    public static byte[] computeVerifier(ParamsEncrypt params, byte[] salt, String username, String password) throws Exception {
        validateInputLength(username, password, params.identityMaxLength, params.passwordMaxLength);

        BigInteger x = getX(params, salt, username, password);
        BigInteger verifier = modPow(params.g, x, params.N);

        return toLittleEndian(verifier.toByteArray());
    }

    private static void validateInputLength(String username, String password, int identityMaxLength, int passwordMaxLength) {
        if (username.length() > identityMaxLength) {
            throw new IllegalArgumentException("La identidad debe tener un máximo de " + identityMaxLength + " caracteres");
        }
        if (password.length() > passwordMaxLength) {
            throw new IllegalArgumentException("La contraseña debe tener un máximo de " + passwordMaxLength + " caracteres");
        }
    }

    private static BigInteger getX(ParamsEncrypt params, byte[] salt, String identity, String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(params.hash);
        byte[] identityPasswordHash = digest.digest((identity.toUpperCase() + ":" + password.toUpperCase()).getBytes(StandardCharsets.UTF_8));

        digest.reset();
        digest.update(salt);
        byte[] hashX = digest.digest(identityPasswordHash);

        return new BigInteger(1, toLittleEndian(hashX));
    }

    private static byte[] toLittleEndian(byte[] bytes) {
        byte[] littleEndianBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            littleEndianBytes[i] = bytes[bytes.length - 1 - i];
        }
        return littleEndianBytes;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger modulus) {
        return base.modPow(exponent, modulus);
    }
}