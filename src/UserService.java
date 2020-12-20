import java.io.IOException;
import java.util.*;

public class UserService {
    private static final String PATH = "database.txt";

    public static void signUp() {
        Scanner scan = new Scanner(System.in);
        User user = new User();
        try {
            System.out.println("Insert Name and Surname");
            user.setFullName(scan.nextLine());
            System.out.println("Insert username /at least 10 symbols/.");
            user.setUsername(scan.nextLine());
            System.out.println("Insert email address.");
            user.setEmail(scan.nextLine());
            System.out.println("Insert password /at least 8 symbols, 2 upper case letters and 3 numbers/.");
            user.setPassword(scan.nextLine());
            FileService.write(PATH, user);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Inputs are discarded");
        }
    }

    public static HashMap<String, User> readUsers() {
        HashMap<String, User> users = new HashMap<>();
        try {
            List<String> strings = FileService.read(PATH);
            for (String s : strings) {
                User user = new User(s);
                users.put(user.getUsername(), user);
            }
            return users;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Could not read Users.");
        }
        return new HashMap<>();
    }

    public static boolean login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert username.");
        String username = scan.nextLine();
        HashMap<String, User> users = readUsers();
        User currentUser = users.get(username);
        if (currentUser != null) {
            System.out.println("Insert password");
            String password = scan.nextLine();
            if (currentUser.checkPassword(password)) {
                System.out.println("Successfully logged in.");
                return true;
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("Incorrect login!");
        }
        return false;
    }
}
