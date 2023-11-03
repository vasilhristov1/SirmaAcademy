import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private List<InventoryItem> items;
    private Map<InventoryItem, Integer> itemQuantities;
    private Payment payment;

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

    public void processPayment(double amount) {
        this.payment = new Payment(amount);
    }

    public Payment getPayment() {
        return payment;
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
        StringBuilder orderSummary = new StringBuilder("Order Summary:\n");
        for (Map.Entry<InventoryItem, Integer> entry : itemQuantities.entrySet()) {
            InventoryItem item = entry.getKey();
            int quantity = entry.getValue();
            orderSummary.append(item.getName()).append(" x").append(quantity).append(" - $").append(item.getPrice() * quantity).append("\n");
        }
        orderSummary.append("Total Cost: $").append(calculateTotalCost());
        if (payment != null) {
            orderSummary.append("\n").append(payment);
        }
        return orderSummary.toString();
    }
}