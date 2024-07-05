import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    public static boolean passStrength(String password) {
        if (password.length() < 8) return false;
        boolean upper = false, lower = false, digit = false, special = false;
        for (char x : password.toCharArray()) {
            if (Character.isUpperCase(x)) upper = true;
            if (Character.isLowerCase(x)) lower = true;
            if (Character.isDigit(x)) digit = true;
            if ("@-_.".indexOf(x) != -1) special = true;
        }
        return upper && lower && digit && special;
    }
    public static String passwordHash(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte x : hash) {
                stringBuilder.append(String.format("%02x", x));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }
}
