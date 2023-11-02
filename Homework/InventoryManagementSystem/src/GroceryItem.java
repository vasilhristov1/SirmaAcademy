public class GroceryItem extends InventoryItem {
    private String expirationDate;

    public GroceryItem(String name, double price, int quantity, String details, String expirationDate) {
        super(quantity);
        this.setName(name);
        this.setCategory("Grocery");
        this.setPrice(price);
        setPerishable(isPerishable());
        setBreakable(isBreakable());
        this.setExpirationDate(expirationDate);
        setDetails(details);
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
                getQuantity() + "," + getDetails() + "," + getExpirationDate();
    }
}
