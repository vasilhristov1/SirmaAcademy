// class that represents the items which have a category of Electronic items
public class ElectronicsItem extends InventoryItem {
    // constructor for the ElectronicsItem class
    public ElectronicsItem(String name, double price, int quantity, String details) {
        super(quantity); // constructor of the superclass
        setName(name); // setting the name of the item
        setCategory("Electronic"); // setting the category of the item
        setPerishable(isPerishable()); // set if the item is perishable
        setBreakable(isBreakable()); // set if the item is breakable
        setPrice(price); // setting the price of the item
        setDetails(details); // setting the details of the item
    }

    // method to get the description of an item
    @Override
    public String getDescription() {
        return "This is an electronic item.";
    }

    // toString method which returns the String representation of the ElectronicsItem class
    @Override
    public String toString() {
        return getItemID() + "," + getCategory() + "," +
                getName() + "," + getPrice() + "," +
                getQuantity() + "," + getDetails();
    }
}
