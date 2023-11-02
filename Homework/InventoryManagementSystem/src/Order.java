import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private List<InventoryItem> items;
    private Map<InventoryItem, Integer> itemQuantities;

    // Constructor
    public Order() {
        this.orderId = nextOrderId++;
        this.items = new ArrayList<>();
        this.itemQuantities = new HashMap<>();
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public Map<InventoryItem, Integer> getItemQuantities() {
        return itemQuantities;
    }

    // Add an item to the order
    public void addItem(InventoryItem item, int quantity) {
        if (item != null && quantity > 0) {
            items.add(item);
            itemQuantities.put(item, quantity);
        }
    }

    // Calculate the total cost of the order
    public double calculateTotalCost() {
        double totalCost = 0.0;

        for (InventoryItem item : items) {
            int quantity = itemQuantities.get(item);
            totalCost += item.getPrice() * quantity;
        }

        return totalCost;
    }

    // Remove an item from the order
    public void removeItem(InventoryItem item) {
        if (item != null && items.contains(item)) {
            items.remove(item);
            itemQuantities.remove(item);
        }
    }

    // Clear all items from the order
    public void clearItems() {
        items.clear();
        itemQuantities.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Items:\n");

        for (InventoryItem item : items) {
            sb.append(item.getName()).append(" (Quantity: ").append(itemQuantities.get(item)).append(")\n");
        }

        sb.append("Total Cost: $").append(calculateTotalCost()).append("\n");
        return sb.toString();
    }
}