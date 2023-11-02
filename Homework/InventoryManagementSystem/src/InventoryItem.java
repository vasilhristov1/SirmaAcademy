import java.io.Serializable;

public abstract class InventoryItem extends AbstractItem implements Serializable, Comparable<InventoryItem> {
    public static int id = 1;
    private int itemID;
    private int quantity;

    public InventoryItem(int quantity) {
        setItemID(id++);
        setQuantity(quantity);
    }

    @Override
    public double calculateValue() {
        return (this.getPrice() * this.getQuantity());
    }

    @Override
    public String getDescription() {
        return null;
    }

    public int getItemID() {
        return this.itemID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(InventoryItem o) {
        if (this.getName().compareTo(o.getName()) == 0 &&
                this.getCategory().compareTo(o.getCategory()) == 0 &&
                this.getPrice() == o.getPrice() &&
                this.getDetails().compareTo(o.getDetails()) == 0
        ) {
            return 0;
        }

        return -1;
    }
}
