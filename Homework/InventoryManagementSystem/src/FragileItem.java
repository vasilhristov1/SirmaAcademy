// class to represent the items which have a category of Fragile items
public class FragileItem extends InventoryItem {
    private double weight; // double variable to store the weight of the fragile item

    // constructor for the fragile item
    public FragileItem(String name, double price, int quantity, String details, double weight) {
        super(quantity); // calling the constructor of the superclass
        setName(name); // setting the name of the item
        setCategory("Fragile"); // setting the category of the item
        setPrice(price); // setting the price of the item
        setWeight(weight); // setting the weight of the item
        setPerishable(isPerishable()); // set if the item is perishable
        setBreakable(isBreakable()); // set if the item is breakable
        setDetails(details); // setting the details of the item
    }

    // method to get the description of the item
    @Override
    public String getDescription() {
        return "This is a fragile item.";
    }

    // method to set the weight of the item
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // method to get the weight of the item
    public double getWeight() {
        return this.weight;
    }

    // method to calculate the value of a fragile item
    @Override
    public double calculateValue() {
        double result = 0.0;

        if (this.weight <= 3.0) {
            result = (getPrice() * getQuantity()) + 4.43;
        } else if (this.weight <= 6.0) {
            result = (getPrice() * getQuantity()) + 6.16;
        } else if (this.weight <= 10.0) {
            result = (getPrice() * getQuantity()) + 7.58;
        } else if (this.weight <= 20.0) {
            result = (getPrice() * getQuantity()) + 13.55;
        } else {
            result = (getPrice() * getQuantity()) + 13.55 + (Math.floor(getWeight() - 20.0) * 0.58);
        }

        return result;
    }

    // toString method to return the string representation of the FragileItem object
    @Override
    public String toString() {
        return getItemID() + "," + getCategory() + "," +
                getName() + "," + getPrice() + "," +
                getQuantity() + "," + getDetails() + "," + getWeight();
    }
}
