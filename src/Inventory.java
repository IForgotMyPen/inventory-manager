import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private static final File userInventoryInfo = new File("user-data/user-inventory-info.txt");
    private static final ArrayList<Item> inventoryList = makeInventoryList();

    public static void addItem(String itemType, String itemName, int addQuantity) {
        boolean itemInInventory = false;
        Item existingItem;
        int existingItemIndex = 0;
        for (Item item : inventoryList) {
            if (item.getItemName().equals(itemName)) {
                itemInInventory = true;
                existingItemIndex = inventoryList.indexOf(item);
                break;
            }
        }
        if (itemInInventory) {
            existingItem = inventoryList.get(existingItemIndex);
            existingItem.setItemQuantity(existingItem.getItemQuantity() + addQuantity);
        } else {
            Item newItem = new Item(Login.username, itemType, itemName, addQuantity);
            inventoryList.add(newItem);
        }

        updateInventoryFile();
    }

    public static void removeItem(String itemType, String itemName, int removeQuantity) {
        boolean itemInInventory = false;
        Item existingItem;
        int existingItemIndex = 0;
        for (Item item : inventoryList) {
            if (item.getItemName().equals(itemName)) {
                itemInInventory = true;
                existingItemIndex = inventoryList.indexOf(item);
                break;
            }
        }
        if (itemInInventory) {
            existingItem = inventoryList.get(existingItemIndex);
            existingItem.setItemQuantity(existingItem.getItemQuantity() - removeQuantity);

            if (existingItem.getItemQuantity() <= 0) {
                inventoryList.remove(existingItemIndex);
            }
        }
        updateInventoryFile();
    }

    public static void viewInventory() {
        System.out.println("Inventory: ");
        for (Item item : inventoryList) {
            if (item.getItemUser().equals(Login.username)) {
                System.out.println("Item: " + item.getItemName() + " --- " +
                        "Type: " + item.getItemType() + " --- " +
                        "Quantity: " + item.getItemQuantity());
            }
        }
    }

    public static ArrayList<Item> makeInventoryList() {
        ArrayList<Item> inventoryList = new ArrayList<>();
        try (Scanner fileReader = new Scanner(userInventoryInfo)) {
            String newItemUser;
            String newItemType;
            String newItemName;
            int newItemQuantity;
            while (fileReader.hasNextLine()) {
                newItemUser = fileReader.next();
                newItemType = fileReader.next();
                newItemName = fileReader.next();
                newItemQuantity = fileReader.nextInt();

                Item newItem = new Item(newItemUser, newItemType, newItemName, newItemQuantity);

                inventoryList.add(newItem);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR in makeInventoryList() method: fileReader failed.");
        }
        return inventoryList;
    }

    private static void updateInventoryFile() {
        try (FileWriter fileWriter = new FileWriter(userInventoryInfo)) {
            int count = 0;
            for (Item item : inventoryList) {
                count++;
                fileWriter.write(Login.username + " " + item.getItemType() + " " + item.getItemName() + " " + item.getItemQuantity());
                if (count != inventoryList.size()) {
                    fileWriter.write("\n");
                }
            }
        } catch (IOException exception) {
            System.out.println("ERROR in updateInventoryFile() method: fileWriter failed.");
        }
    }
}
