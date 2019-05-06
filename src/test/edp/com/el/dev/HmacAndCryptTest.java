package com.el.dev;

import com.el.edp.util.EdpIOUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import static com.el.edp.util.EdpIOUtil.classpathToPath;

/**
 * @author neo.pan
 * @since 2018/04/16
 */
@Slf4j
public class HmacAndCryptTest {

    private final static String HMAC_ALGO = "HmacMD5";
    private final static Charset HMAC_CSET = EdpIOUtil.EDP_CHARSET;
    private static final Base64.Encoder BASE64_ENC = Base64.getEncoder();

    @Test
    public void bytes_to_hex_string() {
        val bytes = "Hello World".getBytes(HMAC_CSET);
        val hexString = DatatypeConverter.printHexBinary(bytes);
        log.info("hexString={}", hexString);
    }

    @Test
    public void bytes_to_base64() {
        val bytes = "Hello World".getBytes(HMAC_CSET);
        val base64String = new String(BASE64_ENC.encode(bytes));
        log.info("base64String={}", base64String);
    }

    @Test
    public void hmac() throws NoSuchAlgorithmException, InvalidKeyException {
        val mac = Mac.getInstance(HMAC_ALGO);
        val key = new SecretKeySpec("secret".getBytes(HMAC_CSET), mac.getAlgorithm());
        mac.init(key);
        val bytesHashed = mac.doFinal("Hello World".getBytes(HMAC_CSET));
        val base64String = new String(BASE64_ENC.encode(bytesHashed));
        log.info("base64String={}", base64String);
    }

    private static final String CRYPT_ALGO = "AES";
    private static final int KEY_BITS = 128;

    private static void encrypt(String secret, InputStream inputStreamToEncrypt, OutputStream outputStream)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {

        byte[] keyBytes = DatatypeConverter.parseHexBinary(secret);
        log.info("keyBytes.size={}", keyBytes.length);

        Key key = new SecretKeySpec(keyBytes, CRYPT_ALGO);
        Cipher cipher = Cipher.getInstance(CRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        val cipherInputStream = new CipherInputStream(new BufferedInputStream(inputStreamToEncrypt), cipher);
        FileCopyUtils.copy(cipherInputStream, outputStream);
    }

    private static void decrypt(String secret, InputStream inputStreamToDecrypt, OutputStream outputStream)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {

        byte[] keyBytes = DatatypeConverter.parseHexBinary(secret);
        log.info("keyBytes.size={}", keyBytes.length);

        Key key = new SecretKeySpec(keyBytes, CRYPT_ALGO);
        Cipher cipher = Cipher.getInstance(CRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);

        val cipherInputStream = new CipherInputStream(new BufferedInputStream(inputStreamToDecrypt), cipher);
        FileCopyUtils.copy(cipherInputStream, outputStream);
    }

    private static String generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CRYPT_ALGO);
        keyGenerator.init(KEY_BITS);
        SecretKey key = keyGenerator.generateKey();
        String secret = DatatypeConverter.printHexBinary(key.getEncoded());
        log.info("secret={}", secret);
        return secret;
    }

    @Test
    public void encrypt_then_decrypt() throws Exception {

        String secret = generateSecretKey();

        val rawFile = classpathToPath("dev/Hello.txt");
        val encFile = rawFile.resolveSibling("Hello.enc");
        try (val rawFileStream = Files.newInputStream(rawFile);
             val encFileStream = Files.newOutputStream(encFile)
        ) {
            encrypt(secret, rawFileStream, encFileStream);
        }

        val decFile = rawFile.resolveSibling("World.txt");
        try (val encFileStream = Files.newInputStream(encFile);
             val decFileStream = Files.newOutputStream(decFile)
        ) {
            decrypt(secret, encFileStream, decFileStream);
        }
    }

    @Test
    public void uuid() {
        log.info(UUID.randomUUID().toString());
    }

}
