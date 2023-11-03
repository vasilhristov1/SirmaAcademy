import java.io.Serializable;

// class to represent an inventory item
public class InventoryItem extends AbstractItem implements Serializable, Comparable<InventoryItem> {
    public static int id = 1; // starting number for the id of the inventory items
    private int itemID; // integer number to store the id of an item
    private int quantity; // integer number to store the quantity of an item

    // constructor
    public InventoryItem(int quantity) {
        setItemID(id++); // setting the id of the item
        setQuantity(quantity); // setting the quantity of the item
    }

    // method to calculate the value of the inventory item
    @Override
    public double calculateValue() {
        return (this.getPrice() * this.getQuantity());
    }

    // method to get the description of the inventory item
    @Override
    public String getDescription() {
        return null;
    }

    // method to get the item ID
    public int getItemID() {
        return this.itemID;
    }

    // method to get the quantity of the item
    public int getQuantity() {
        return this.quantity;
    }

    // method to set the id of the item
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    // method to set the quantity of the item
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // compareTo method to compare two InventoryItem objects
    @Override
    public int compareTo(InventoryItem o) {
        return getCategory().compareTo(o.getCategory());
    }
}
