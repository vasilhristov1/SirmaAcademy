import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManagementSystem {
    private static List<InventoryItem> inventory;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadInventoryData();

        boolean exit = false;

        while (!exit) {
            System.out.println("Inventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. List Items");
            System.out.println("3. Categorize Items");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    listItems();
                    break;
                case 3:
                    categorizeItems();
                    break;
                case 4:
                    placeOrder();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
        
        saveInventoryData();
        scanner.close();
    }

    private static void loadInventoryData() {
        TextFileReader textFileReader = new TextFileReader();
        inventory = textFileReader.loadInventoryFromFile("D:\\Study\\SirmaAcademy\\Homework\\InventoryManagementSystem\\src\\Files\\items.txt");
    }

    private static void saveInventoryData() {
        TextFileWriter textFileWriter = new TextFileWriter();
        textFileWriter.saveInventoryToFile(inventory, "D:\\Study\\SirmaAcademy\\Homework\\InventoryManagementSystem\\src\\Files\\items.txt");
    }

    private static void addItem() {
        System.out.println("Add a New Item");
        System.out.print("Enter the name of the item: ");
        String name = scanner.nextLine();

        System.out.print("Enter the category of the item: ");
        String category = scanner.nextLine();

        System.out.print("Enter the price of the item: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the quantity of the item: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        if ("Grocery".equalsIgnoreCase(category)) {
            System.out.print("Enter the expiration date (e.g., 2023-12-31): ");
            String expirationDate = scanner.nextLine();

            GroceryItem newItem = new GroceryItem(name, price, quantity, expirationDate);
            inventory.add(newItem);
        } else if ("Fragile".equalsIgnoreCase(category)) {
            System.out.print("Enter the weight (in kilograms) of the item: ");
            double weight = scanner.nextDouble();

            FragileItem newItem = new FragileItem(name, price, quantity, weight);
            inventory.add(newItem);
        } else if ("Electronic".equalsIgnoreCase(category)) {
            ElectronicsItem newItem = new ElectronicsItem(name, price, quantity);
            inventory.add(newItem);
        } else {
            System.out.println("Invalid category. Item not added.");
        }

        System.out.println("Item added to the inventory.");
    }

    private static void listItems() {

    }

    private static void categorizeItems() {

    }

    private static void placeOrder() {

    }

//    public static void main(String[] args) {
//        List<InventoryItem> items = new ArrayList<>();
//        items.add(new ElectronicsItem("Smartphone", 999.99, 2));
//        items.add(new ElectronicsItem("Laptop", 500.99, 5));
//        items.add(new FragileItem("Glass", 3.29, 6, 0.33));
//        items.add(new FragileItem("Vase", 100.0, 1, 5.63));
//        items.add(new GroceryItem("Tomato", 0.99, 5, "10-10-2024"));
//        items.add(new GroceryItem("Cucumber", 0.59, 3, "12-12-2024"));
//
//        TextFileWriter textFileWriter = new TextFileWriter();
//        textFileWriter.saveInventoryToFile(items, "D:\\Study\\SirmaAcademy\\Homework\\InventoryManagementSystem\\src\\Files\\items.txt");
//        TextFileReader textFileReader = new TextFileReader();
//
//        List<InventoryItem> items_test = textFileReader.loadInventoryFromFile("D:\\Study\\SirmaAcademy\\Homework\\InventoryManagementSystem\\src\\Files\\items.txt");
//
//        for (Serializable item : items_test) {
//            System.out.println(item);
//        }
//    }
}
