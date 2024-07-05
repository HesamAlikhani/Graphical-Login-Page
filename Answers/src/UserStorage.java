import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserStorage {
    File storageFile = new File("userStorage.txt");
    private List<User> users;
    public UserStorage() {
        users = new ArrayList<>();
        loadUsers();
    }
    private void loadUsers() {
        try {
            Scanner reader = new Scanner(storageFile);
            while (reader.hasNextLine()) {System.out.println(reader.nextLine());}
        } catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }
    public boolean userReg(String username, String password, String email) {
        if (usernameCheck(username)) return false;
        User user = new User(username, password, email);
        users.add(user);
        saveUser(user);
        return true;
    }
    private boolean usernameCheck(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return true;
        }
        return false;
    }
    public boolean loginCheck(String username, String password) {
        for (User user : users) {
            if (usernameCheck(username) && user.getPassword().equals(PasswordUtils.passwordHash(password))) return true;
        }
        return false;
    }
    private void saveUser(User user) {
        try {
            FileWriter writer = new FileWriter("userStorage.txt", true);
            writer.write(String.format("%s, %s, %s", user.getUsername(), user.getPassword(), user.getEmail()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
