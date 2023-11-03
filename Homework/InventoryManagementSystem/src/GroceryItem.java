// class to represent an item which have a category of a grocery item
public class GroceryItem extends InventoryItem {
    private String expirationDate; // String to store the expiration date of a product

    // constructor
    public GroceryItem(String name, double price, int quantity, String details, String expirationDate) {
        super(quantity); // calling the constructor of the superclass
        this.setName(name); // setting the name of the item
        this.setCategory("Grocery"); // setting the category of the item
        this.setPrice(price); // setting the price of the item
        setPerishable(isPerishable()); // set if the item is perishable
        setBreakable(isBreakable()); // set if the item is breakable
        this.setExpirationDate(expirationDate); // setting the expiration date of the item
        setDetails(details); // setting the details of the item
    }

    // method to get the expiration date
    public String getExpirationDate() {
        return expirationDate;
    }

    // method to set the expiration date
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    // toString method to return the string representation of a GroceryItem object
    @Override
    public String toString() {
        return getItemID() + "," + getCategory() + "," +
                getName() + "," + getPrice() + "," +
                getQuantity() + "," + getDetails() + "," + getExpirationDate();
    }
}
