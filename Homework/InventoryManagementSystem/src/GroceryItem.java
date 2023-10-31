import com.fasterxml.jackson.annotation.JsonProperty;

public class GroceryItem extends InventoryItem {
    @JsonProperty("expirationDate")
    private String expirationDate;

    public GroceryItem(String name, double price, int quantity, String expirationDate) {
        super(quantity);
        this.setName(name);
        this.setCategory("Grocery");
        this.setPrice(price);
        setPerishable(isPerishable());
        setBreakable(isBreakable());
        this.setExpirationDate(expirationDate);
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return getItemID() + "," + getCategory() + "," +
                getName() + "," + getPrice() + "," +
                getQuantity() + "," + getExpirationDate();
    }
}
