import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {
    public List<InventoryItem> loadInventoryFromFile(String fileName) {
        List<InventoryItem> inventory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int itemID = Integer.parseInt(parts[0]);
                    String category = parts[1];
                    String name = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int quantity = Integer.parseInt(parts[4]);

                    if ("Electronic".equals(category)) {
                        // Handle ElectronicsItem
                        ElectronicsItem electronicItem = new ElectronicsItem(name, price, quantity);
                        electronicItem.setItemID(itemID);
                        inventory.add(electronicItem);
                    } else if ("Grocery".equals(category)) {
                        // Handle GroceryItem
                        if (parts.length >= 6) {
                            String expirationDate = parts[5];
                            GroceryItem groceryItem = new GroceryItem(name, price, quantity, expirationDate);
                            groceryItem.setItemID(itemID);
                            inventory.add(groceryItem);
                        }
                    } else if ("Fragile".equals(category)) {
                        // Handle FragileItem
                        if (parts.length >= 6) {
                            double weight = Double.parseDouble(parts[5]);
                            FragileItem fragileItem = new FragileItem(name, price, quantity, weight);
                            fragileItem.setItemID(itemID);
                            inventory.add(fragileItem);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inventory;
    }

}
