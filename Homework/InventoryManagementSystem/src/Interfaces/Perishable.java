package Interfaces;

// Interface Perishable to indicate the items which are perishable
public interface Perishable {
    boolean isPerishable(); // method to check if the item is perishable
    void handleExpiration(); // method to handle the expiration of an item
}
