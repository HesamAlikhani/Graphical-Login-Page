import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernames = new JTextField();
    private JPasswordField passwords = new JPasswordField();
    private JTextField emails = new JTextField();
    private JButton loginB = new JButton("Log In");
    private JButton registerB = new JButton("Register");
    private JLabel message = new JLabel();
    private UserStorage storage;
    public LoginGUI() {
        storage = new UserStorage();
        createUI();
    }
    private void createUI() {
        setTitle("");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));
        add(new JLabel("Username:"));
        add(usernames);
        add(new JLabel("Password:"));
        add(passwords);
        add(new JLabel("Email (for registration only)"));
        add(emails);
        add(loginB);
        add(registerB);
        add(message);
        loginB.addActionListener(new lbListener());
        registerB.addActionListener(new rbListener());
    }
    private class lbListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String userName = usernames.getText();
            String password = new String(passwords.getPassword());
            if (storage.loginCheck(userName, password)) {
                message.setText("Logged in successfully");
            } else {
                message.setText("Invalid entry!");
            }
        }
    }
    private class rbListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae){
            String userName = usernames.getText();
            String password = new String(passwords.getPassword());
            String email = emails.getText();
            if (!EmailValidatore.emailForm(email)) {
                message.setText("Invalid email format!");
            }
            if (!PasswordUtils.passStrength(password)) {
                message.setText("Invalid password format!");
            }
            if (storage.userReg(userName, password, email)) {
                message.setText("Registered successfully!");
            } else {
                message.setText("Invalid entry!");
            }
        }
    }
}
