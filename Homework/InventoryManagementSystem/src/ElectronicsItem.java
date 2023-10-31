public class ElectronicsItem extends InventoryItem {
    public ElectronicsItem(String name, double price, int quantity) {
        super(quantity);
        setName(name);
        setCategory("Electronic");
        setPerishable(isPerishable());
        setBreakable(isBreakable());
        setPrice(price);
    }

    @Override
    public String getDescription() {
        return "This is an electronic item.";
    }

    @Override
    public String toString() {
        return getItemID() + "," + getCategory() + "," +
                getName() + "," + getPrice() + "," +
                getQuantity();
    }
}
