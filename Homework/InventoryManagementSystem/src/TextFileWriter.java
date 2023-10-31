import java.io.*;
import java.util.List;

public class TextFileWriter {
    public void saveInventoryToFile(List<InventoryItem> inventory, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (InventoryItem item : inventory) {
                writer.println(item.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
