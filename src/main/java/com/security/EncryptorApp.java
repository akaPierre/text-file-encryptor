package com.security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class EncryptorApp {
    private final AesEncryptor encryptor;
    private final Scanner scanner;

    public EncryptorApp() {
        this.encryptor = new AesEncryptor();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new EncryptorApp().run();
    }

    public void run() {
        System.out.println("[AES-256] Text File Encryptor/Decryptor");
        System.out.println("=======================================");
        System.out.println();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> encryptFile();
                case 2 -> decryptFile();
                case 3 -> showInfo();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("❌ Invalid option. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("1) [ENC] Encrypt text file");
        System.out.println("2) [DEC] Decrypt encrypted file");
        System.out.println("3) [INFO] Info & Examples");
        System.out.println("0) Exit");
        System.out.println("----------------------------------------");
    }

    private void encryptFile() {
        System.out.println("=== Encrypt Text File ===");
        Path inputFile = readFilePath("Enter input text file path: ");
        if (!Files.exists(inputFile)) {
            System.out.println("❌ File not found: " + inputFile);
            return;
        }

        if (!Files.isRegularFile(inputFile)) {
            System.out.println("❌ Not a regular file: " + inputFile);
            return;
        }

        Path outputFile = inputFile.resolveSibling(inputFile.getFileName() + ".encrypted");
        String password = readPassword();

        try {
            long startTime = System.currentTimeMillis();
            encryptor.encryptFile(inputFile, outputFile, password);
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("[OK] Encrypted successfully!");

            System.out.println("[FILE] Input:  " + inputFile);
            System.out.println("[FILE] Output: " + outputFile);
            System.out.println("[TIME] Time:   " + duration + "ms");
        } catch (Exception e) {
            System.err.println("❌ Encryption failed: " + e.getMessage());
        }
    }

    private void decryptFile() {
        System.out.println("=== Decrypt File ===");
        Path inputFile = readFilePath("Enter .encrypted file path: ");
        if (!Files.exists(inputFile)) {
            System.out.println("❌ File not found: " + inputFile);
            return;
        }

        Path outputFile = inputFile.resolveSibling(inputFile.getFileName().toString().replace(".encrypted", ".decrypted"));
        String password = readPassword();

        try {
            long startTime = System.currentTimeMillis();
            encryptor.decryptFile(inputFile, outputFile, password);
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("✅ Decrypted successfully!");
            System.out.println("📁 Input:  " + inputFile);
            System.out.println("📁 Output: " + outputFile);
            System.out.println("⏱️  Time:   " + duration + "ms");
        } catch (Exception e) {
            System.err.println("❌ Decryption failed: " + e.getMessage());
        }
    }

    private void showInfo() {
        System.out.println("=== Information ===");
        System.out.println("🔒 Uses AES-256-GCM (strong encryption)");
        System.out.println("📝 Works with any text files (UTF-8)");
        System.out.println("");
        System.out.println("💡 Example usage:");
        System.out.println("  1. Create test.txt with some content");
        System.out.println("  2. Encrypt → test.txt.encrypted");
        System.out.println("  3. Delete test.txt");
        System.out.println("  4. Decrypt → test.txt.decrypted");
        System.out.println("");
        System.out.println("⚠️  SAVE YOUR PASSWORD! Files are unrecoverable without it.");
    }

    private Path readFilePath(String prompt) {
        while (true) {
            System.out.print(prompt);
            String path = scanner.nextLine().trim();
            Path filePath = Paths.get(path);
            if (Files.exists(filePath)) {
                return filePath;
            }
            System.out.println("File not found. Try again or use full path.");
        }
    }

    private String readPassword() {
        while (true) {
            System.out.print("Enter password (min 8 chars): ");
            String password = scanner.nextLine().trim();
            if (password.length() >= 8) {
                return password;
            }
            System.out.println("❌ Password must be at least 8 characters.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }
}