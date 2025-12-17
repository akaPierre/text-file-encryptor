package com.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptorAppTest {

    @TempDir
    Path tempDir;

    @Test
    void testEncryptDecryptRoundtrip() throws Exception {
        // Create test file
        Path originalFile = tempDir.resolve("test.txt");
        String testContent = "Hello, encrypted world!\nLine 2 with more content.";
        Files.writeString(originalFile, testContent);

        Path encryptedFile = tempDir.resolve("test.encrypted");
        Path decryptedFile = tempDir.resolve("test.decrypted");

        AesEncryptor encryptor = new AesEncryptor();
        String password = "testpassword123";

        // Encrypt
        encryptor.encryptFile(originalFile, encryptedFile, password);

        // Verify encrypted file was created and is different size
        assertTrue(Files.exists(encryptedFile));
        assertTrue(Files.size(encryptedFile) > 0);

        // Decrypt
        encryptor.decryptFile(encryptedFile, decryptedFile, password);

        // Verify decrypted file matches original
        assertTrue(Files.exists(decryptedFile));
        String decryptedContent = Files.readString(decryptedFile);
        assertEquals(testContent, decryptedContent);
    }

    @Test
    void testWrongPasswordFails() throws Exception {
        Path originalFile = tempDir.resolve("test.txt");
        Files.writeString(originalFile, "Secret data");

        Path encryptedFile = tempDir.resolve("test.encrypted");
        Path decryptedFile = tempDir.resolve("test_wrongpass.decrypted");

        AesEncryptor encryptor = new AesEncryptor();
        String correctPassword = "correctpass";
        String wrongPassword = "wrongpass";

        // Encrypt with correct password
        encryptor.encryptFile(originalFile, encryptedFile, correctPassword);

        // Try decrypt with wrong password - should throw exception
        assertThrows(Exception.class, () -> {
            encryptor.decryptFile(encryptedFile, decryptedFile, wrongPassword);
        });
    }

    @Test
    void testEncryptNonExistentFile() {
        Path nonExistent = Paths.get("does-not-exist.txt");
        Path outputFile = tempDir.resolve("output.encrypted");

        AesEncryptor encryptor = new AesEncryptor();

        assertThrows(Exception.class, () -> {
            encryptor.encryptFile(nonExistent, outputFile, "password");
        });
    }
}