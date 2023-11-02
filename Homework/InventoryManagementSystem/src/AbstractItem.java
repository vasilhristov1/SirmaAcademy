import Interfaces.*;

import java.io.Serializable;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable, Serializable {
    private String name;
    private String category;
    private boolean perishable;
    private boolean breakable;
    private double price;
    private String details;

    @Override
    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public boolean isBreakable() {
        if (this.getCategory().equals("Electronic") || this.getCategory().equals("Fragile")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isPerishable() {
        if (this.getCategory().equals("Grocery")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void handleBreakage() {
        if (this.breakable) {
            System.out.println("If your item is broken, please contact us to help you. We are sorry for our mistake!");
        } else {
            System.out.println("This item is not breakable so it cannot be repaired.");
        }
    }

    @Override
    public void handleExpiration() {
        if (this.perishable) {
            System.out.println("If your item is out of date, please contact us to help you. We are sorry for our mistake!");
        } else {
            System.out.println("This item is not perishable so we cannot do nothing to help you.");
        }
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
