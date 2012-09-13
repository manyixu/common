package org.solovyev.common.security;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.solovyev.common.HexUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * User: serso
 * Date: 8/20/12
 * Time: 7:44 PM
 */
public class CiphererImpl implements Cipherer {

    private static final String PROVIDER = "BC";
    private static final int IV_LENGTH = 16;

    private static final String RANDOM_ALGORITHM = "SHA1PRNG";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    @NotNull
    private final String randomAlgorithm;

    private final int ivLength;

    @NotNull
    private final String cipherAlgorithm;

    @Nullable
    private final String provider;

    public CiphererImpl(@NotNull String randomAlgorithm,
                        int ivLength,
                        @NotNull String cipherAlgorithm,
                        @Nullable String provider) {
        this.randomAlgorithm = randomAlgorithm;
        this.ivLength = ivLength;
        this.cipherAlgorithm = cipherAlgorithm;
        this.provider = provider;
    }

    @NotNull
    public static Cipherer newAndroidAesCipherer() {
        return new CiphererImpl(RANDOM_ALGORITHM, IV_LENGTH, CIPHER_ALGORITHM, PROVIDER);
    }

    @Override
    @NotNull
    public String encrypt(@NotNull SecretKey secret,
                          @NotNull String plainText) throws CiphererException {
        try {
            final byte[] iv = SecurityUtils.generateRandomBytes(randomAlgorithm, ivLength);
            final String ivHex = HexUtils.toHex(iv);
            return encrypt(secret, plainText, ivHex);
        } catch (Exception e) {
            throw new CiphererException("Unable to encrypt due to some errors!", e);
        }
    }

    @Override
    @NotNull
    public String encrypt(@NotNull SecretKey secret,
                          @NotNull String plainText,
                          @NotNull String ivHex) throws CiphererException {
        try {
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(HexUtils.toBytes(ivHex));

            final Cipher encryptionCipher;
            if (provider != null) {
                encryptionCipher = Cipher.getInstance(cipherAlgorithm, provider);
            } else {
                encryptionCipher = Cipher.getInstance(cipherAlgorithm);
            }
            encryptionCipher.init(Cipher.ENCRYPT_MODE, secret, ivParameterSpec);

            final byte[] encrypted = encryptionCipher.doFinal(plainText.getBytes("UTF-8"));
            final String encryptedHex = HexUtils.toHex(encrypted);

            return ivHex + encryptedHex;
        } catch (Exception e) {
            throw new CiphererException("Unable to encrypt due to some errors!", e);
        }
    }

    @NotNull
    @Override
    public String decrypt(@NotNull SecretKey secret, @NotNull String encryptedText) throws CiphererException {
        try {
            final String ivHex = getIvHexFromEncrypted(encryptedText);
            final String encryptedHex = encryptedText.substring(ivLength * 2);
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(HexUtils.toBytes(ivHex));

            final Cipher decryptionCipher;
            if (provider != null) {
                decryptionCipher = Cipher.getInstance(cipherAlgorithm, provider);
            } else {
                decryptionCipher = Cipher.getInstance(cipherAlgorithm);
            }
            decryptionCipher.init(Cipher.DECRYPT_MODE, secret, ivParameterSpec);

            byte[] decrypted = decryptionCipher.doFinal(HexUtils.toBytes(encryptedHex));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            throw new CiphererException("Unable to decrypt due to some errors!", e);
        }
    }

    @NotNull
    @Override
    public String getIvHexFromEncrypted(@NotNull String encryptedText) throws CiphererException {
        try {
            return encryptedText.substring(0, ivLength * 2);
        } catch (Exception e) {
            throw new CiphererException("Unable to extract initial vector!", e);
        }
    }
}
