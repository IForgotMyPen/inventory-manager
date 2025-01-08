import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static final Scanner userInput = new Scanner(System.in);

    public static void main(String [] args) {

        loginChoices();

    }

    private static void loginChoices() {
        boolean validInput = true;
        int userChoice;
        do {
            System.out.println("Choose:\n" +
                    "1. Login to existing account\n" +
                    "2. Create a new account\n" +
                    "3. End");
            try {
                userChoice = userInput.nextInt();
                switch (userChoice) {
                    case 1:
                        if (Login.loginToSystem()) {
                            System.out.println("Logging in as " + Login.username + "...");
                            inventoryChoices();
                        } else {
                            System.out.println("Login failed. Username or password is incorrect.");
                        }
                        break;
                    case 2:
                        Login.createNewLogin();
                        break;
                    case 3:
                        System.out.println("Ending...");
                        break;
                }

            } catch (InputMismatchException e) {
                validInput = false;
                userInput.next();
                System.out.println("ERROR in loginChoices() method: userInput incorrect data type.");
            } catch (NoSuchElementException e) {
                System.out.println("ERROR in loginChoices() method: userInput empty.");
                System.out.println("Ending...");
                break;
            }
        } while (!validInput);
    }

    private static void inventoryChoices() {
        System.out.println("Choose:\n" +
                "1. View inventory\n" +
                "2. Add item to inventory\n" +
                "3. Remove item from inventory");
        int userChoice = userInput.nextInt();

        String itemType;
        String itemName;
        int addQuantity;
        int removeQuantity;
        switch(userChoice) {
            case 1:
                Inventory.viewInventory();
                break;
            case 2:
                System.out.print("Item type: ");
                itemType = userInput.next();
                System.out.print("Item name: ");
                itemName = userInput.next();
                System.out.print("Item quantity: ");
                addQuantity = userInput.nextInt();
                Inventory.addItem(itemType, itemName, addQuantity);
                break;
            case 3:
                System.out.print("Item type: ");
                itemType = userInput.next();
                System.out.print("Item name: ");
                itemName = userInput.next();
                System.out.print("Item quantity: ");
                removeQuantity = userInput.nextInt();
                Inventory.removeItem(itemType, itemName, removeQuantity);
                break;
        }
    }
}
