import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private static final String PATH = "database.txt";

    public static void signUp() {
        Scanner scan = new Scanner(System.in);
        User user = new User();
        try {
            System.out.println("Insert Name and Surname");
            user.setName(scan.next());
            user.setSurname(scan.next());
            scan.nextLine();
            System.out.println("Insert username /at least 10 symbols/.");
            user.setUsername(scan.nextLine());
            System.out.println("Insert email address.");
            user.setUsername(scan.nextLine());
            System.out.println("Insert password /at least 8 symbols, 2 upper case letters and 3 numbers/.");
            user.setPassword(scan.nextLine());
            FileService.write(PATH, user);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Inputs are discarded");
        }
        scan.close();
    }

    public static List<User> readUser() {
        List<User> users;
        try {
            List<String> strings = FileService.read(PATH);
            users = new ArrayList<>();
            for (String s : strings) {
                users.add(new User(s));
            }
            return users;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Could not read Users.");
        }
        return new ArrayList<>();
    }

    public static boolean login() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert username.");
        String username = scan.nextLine();
        if(readUser().contains(username)){
            System.out.println("Insert password");
            String password = scan.nextLine();
//            boolean isCorrect = true;
//            while (isCorrect){
//                switch ()
//            }
        }

        return true;
    }
}
