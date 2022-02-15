import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.security.*;

interface PasswordsInterface {
    void input(String fileName) throws IOException, NoSuchAlgorithmException;
    boolean verify(String login, String pass) throws NoSuchAlgorithmException;
    void register(String login, String pass) throws IOException;
}
public class Passwords implements PasswordsInterface {
    private final Map<String, byte[]> passwords;
    private String fileName;
    public Passwords() {
        passwords = new TreeMap<>();
    }
    @Override
    public void input(String fileName) throws IOException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter("[\\s\r\n\t]+");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String login;
        String pass;
        while(sc.hasNext()) {
            md.reset();
            login = sc.next();
            pass = sc.next();
            md.update(pass.getBytes(StandardCharsets.UTF_8));
            passwords.put(login, md.digest());
        }
        sc.close();
        this.fileName = fileName;
    }
    @Override
    public boolean verify(String login, String pass) throws NoSuchAlgorithmException {
        if (passwords.containsKey(login)) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(pass.getBytes(StandardCharsets.UTF_8));
            return Arrays.equals(passwords.get(login), md.digest());
        }
        return false;
    }
    @Override
    public void register (String login, String pass) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(raf.length());
        raf.writeByte('\n');
        raf.writeBytes(login + " " + pass);
        raf.close();
    }
}