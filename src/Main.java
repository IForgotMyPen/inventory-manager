import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static final Scanner userInput = new Scanner(System.in);

    public static void main(String [] args) {

        loginChoices();

    }

    private static void loginChoices() {
        int userChoice;
        System.out.println("Choose:\n" +
                "1. Login to existing account\n" +
                "2. Create a new account");
        try {
            userChoice = userInput.nextInt();

            switch(userChoice) {
                case 1:
                    if (Login.loginToSystem()) {
                        System.out.println("Logging in as " + Login.username + "...");
                        Inventory.viewInventory();

                    } else {
                        System.out.println("Login failed. Username or password is incorrect.");
                    }
                    break;
                case 2:
                    Login.createNewLogin();
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("ERROR in loginChoices() method: userInput incorrect data type.");
        } catch (NoSuchElementException e) {
            System.out.println("ERROR in loginChoices() method: userInput empty.");
        }
    }

    private static void inventoryChoices() {

    }
}
