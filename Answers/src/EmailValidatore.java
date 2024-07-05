import java.util.regex.Pattern;

public class EmailValidatore {
    private static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean emailForm(String email) {
        return emailPattern.matcher(email).matches();
    }
}
