import Interfaces.*;

import java.io.Serializable;

// abstract class to represent an item of the system
public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable, Serializable {
    private String name; // this string contains the name of the item
    private String category; // this string contains the category of the item
    private boolean perishable; // this boolean marks if the item is perishable
    private boolean breakable; // this boolean marks if the item is breakable
    private double price; // this double stores the price of the item
    private String details; // this string contains the details of the item

    // method to get the details of an item
    @Override
    public String getDetails() {
        return this.details;
    }

    // method to set the details of an item
    public void setDetails(String details) {
        this.details = details;
    }

    // method to set if an item is breakable or not
    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    // method to set if an item is perishable or not
    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    // method to get the name of an item
    public String getName() {
        return this.name;
    }

    // method to set the name of an item
    public void setName(String name) {
        this.name = name;
    }

    // method to set the category of an item
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    // method to get the category of an item
    @Override
    public String getCategory() {
        return this.category;
    }

    // method to get if an item is breakable or not
    @Override
    public boolean isBreakable() {
        if (this.getCategory().equals("Electronic") || this.getCategory().equals("Fragile")) {
            return true;
        } else {
            return false;
        }
    }

    // method to get if an item is perishable or not
    @Override
    public boolean isPerishable() {
        if (this.getCategory().equals("Grocery")) {
            return true;
        } else {
            return false;
        }
    }

    // method to handle the breakage of an item
    @Override
    public void handleBreakage() {
        if (this.breakable) {
            System.out.println("If your item is broken, please contact us to help you. We are sorry for our mistake!");
        } else {
            System.out.println("This item is not breakable so it cannot be repaired.");
        }
    }

    // method to handle the expiration of an item
    @Override
    public void handleExpiration() {
        if (this.perishable) {
            System.out.println("If your item is out of date, please contact us to help you. We are sorry for our mistake!");
        } else {
            System.out.println("This item is not perishable so we cannot do nothing to help you.");
        }
    }

    // method to set the price of an item
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    // method to get the price of an item
    @Override
    public double getPrice() {
        return this.price;
    }
}
