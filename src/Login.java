import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Login {
    private static final File userLoginInfo = new File("user-data/user-login-info.txt");
    private static final ArrayList<User> userList = makeUserList();

    public static String username;
    public static String password;

    public static boolean loginToSystem() {
        try {
            System.out.print("Enter username: ");
            username = Main.userInput.next();
        } catch (NoSuchElementException e) {
            System.out.println("ERROR in loginToSystem() method: userInput is empty");
            return false;
        }
        try {
            System.out.print("Enter password: ");
            password = Main.userInput.next();
        } catch (NoSuchElementException e) {
            System.out.println("ERROR in loginToSystem() method: userInput is empty");
            return false;
        }
        return checkLogin();
    }

    private static boolean checkLogin() {

        boolean loginSuccess = false;
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                if (password.equals(user.getPassword())) {
                    loginSuccess = true;
                    break;
                }
            }
        }
        return loginSuccess;
    }

    private static ArrayList<User> makeUserList() {
        ArrayList<User> userList = new ArrayList<>();
        try (Scanner fileReader = new Scanner(userLoginInfo)) {
            while(fileReader.hasNext()) {
                String username = fileReader.next();
                String password = fileReader.nextLine().trim();

                User newUser = new User(username, password);

                userList.add(newUser);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("ERROR in makeUserList() method: fileReader failed.");
        }
        return userList;
    }

    public static void createNewLogin() {
        try (FileWriter fileOutput = new FileWriter(userLoginInfo, true)) {
            System.out.print("Enter new username: ");
            String username = Main.userInput.next();

            System.out.print("Enter new password: ");
            String password = Main.userInput.next();

            fileOutput.write("\n" + username + " " + password);
        } catch (java.io.IOException e) {
            System.out.println("ERROR in createNewLogin() method: fileOutput failed.");
        }
    }
}