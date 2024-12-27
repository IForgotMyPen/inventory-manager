public class Item {
    public String itemUser;
    public String itemType;
    public String itemName;
    public int itemQuantity;

    public Item (String itemUser, String itemType, String itemName, int itemQuantity) {
        this.itemUser = itemUser;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public void setItemUser(String itemUser) {
        this.itemUser = itemUser;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemUser() {
        return itemUser;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }
}
