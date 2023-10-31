import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class InventoryItem extends AbstractItem implements Serializable {
    public static int id = 1;
    @JsonProperty("id")
    private int itemID;
    @JsonProperty("quantity")
    private int quantity;

    public InventoryItem(int quantity) {
        setItemID(id++);
        setQuantity(quantity);
    }

    @Override
    public double calculateValue() {
        return (this.getPrice() * this.getQuantity());
    }

    @JsonIgnore
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
}
