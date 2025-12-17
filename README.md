# 🔐 Text File Encryptor/Decryptor (Java + Maven)

A **console-based AES-256 file encryption tool** written in **Java** and built with **Maven**.

Encrypts any text file to binary `.encrypted` format and decrypts back to original text. Perfect for securing sensitive text files.

This project is ideal for:
- Learning **AES-256-GCM encryption** in Java
- Practicing **file I/O** and **cryptography**
- Building **cybersecurity tools**
- Creating **portable CLI utilities**

---

## ✨ Features

- **AES-256-GCM encryption** (strong, authenticated encryption)
- **Password-based key derivation** (SHA-256)
- **File encryption/decryption** with progress
- **Input validation** and error handling
- **Cross-platform** (Windows/Linux/macOS)
- **No external dependencies** (pure Java)

---

## 🛠️ Tech Stack

- **Java** (JDK 21+ recommended, tested with JDK 25)
- **Maven** (3.8+)
- **Java Cryptography Extension** (JCE, included in JDK)
- **JUnit 5** (tests included)

---

## 📋 Requirements

- Java JDK installed and available on your `PATH`
- Maven installed (`mvn -v` should work)

---

## 📁 Project Structure

```
text-file-encryptor/
├── pom.xml
├── README.md
├── .gitignore
├── src/
│ ├── main/
│ │ └── java/
│ │ └── com/security/
│ │ ├── EncryptorApp.java
│ │ └── AesEncryptor.java
│ └── test/
│ └── java/
│ └── com/security/
│ └── EncryptorAppTest.java
└── target/
```

---

## 📦 Building and Running

### 1️⃣ Build the project

```
mvn clean package
```

Generates: `target/text-file-encryptor-1.0-SNAPSHOT.jar`

### 2️⃣ Run the app

```
java -jar target/text-file-encryptor-1.0-SNAPSHOT.jar
```

### 3️⃣ Example workflow

1. Create test file
   echo "Secret data!" > secret.txt

2. Run app → option 1 (Encrypt)
   Enter: secret.txt
   Password: mysecretpass123
   Output: secret.txt.encrypted
3. Delete original
   del secret.txt

4. Run app → option 2 (Decrypt)
   Enter: secret.txt.encrypted
   Password: mysecretpass123
   Output: secret.txt.decrypted ✅

---

## 🔍 Menu Preview

```
🔒 AES-256 Text File Encryptor/Decryptor
1.🔐 Encrypt text file

2.🔓 Decrypt encrypted file

3.ℹ️ Info & Examples

4.Exit
```

---

## 📝 Notes

- **Password minimum**: 8 characters
- **File format**: Adds 12-byte IV header automatically
- **Output naming**: `input.txt` → `input.txt.encrypted` / `input.txt.decrypted`
- **⚠️ Security**: **SAVE YOUR PASSWORDS**. No recovery possible!

---

## 📄 License

MIT License

Copyright (c) 2025 Daniel Pierre Fachini de Toledo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

## 🚀 Future Improvements (Ideas)

- PBKDF2 key derivation (stronger than SHA-256)
- Progress bar for large files
- Batch encryption/decryption
- Encrypt directories recursively
- Key file support

---

## 👤 Author

**Daniel Pierre Fachini de Toledo**  
GitHub: https://github.com/akaPierre