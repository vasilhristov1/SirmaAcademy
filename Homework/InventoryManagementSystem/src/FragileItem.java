import com.fasterxml.jackson.annotation.JsonProperty;

public class FragileItem extends InventoryItem {
    @JsonProperty("weight")
    private double weight;

    public FragileItem(String name, double price, int quantity, double weight) {
        super(quantity);
        setName(name);
        setCategory("Fragile");
        setPrice(price);
        setWeight(weight);
        setPerishable(isPerishable());
        setBreakable(isBreakable());
    }

    @Override
    public String getDescription() {
        return "This is a fragile item.";
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

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

    @Override
    public String toString() {
        return getItemID() + "," + getCategory() + "," +
                getName() + "," + getPrice() + "," +
                getQuantity() + "," + getWeight();
    }
}
