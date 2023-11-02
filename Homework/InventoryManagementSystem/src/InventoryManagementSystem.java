import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InventoryManagementSystem {
    private static List<InventoryItem> inventory;

    public static void main(String[] args) {
        // System.out.println(expirationDateChecker("30-04-2022"));
        // InventoryItem i1 = new ElectronicsItem("Smartphone", 999.99, 1, "");
        // InventoryItem i2 = new ElectronicsItem("Smartphone", 999.99, 2, "");

        // System.out.println(i1.compareTo(i2));

//        inventory = new ArrayList<>();
//
//        inventory.add(new ElectronicsItem("Smartphone", 999.99, 2, "Apple iPhone 15 Pro"));
//        inventory.add(new ElectronicsItem("MacBook", 1999.99, 1, "MacBook Pro"));
//        inventory.add(new GroceryItem("Tomato", 0.99, 5, "Red tomatoes", "10-10-2023"));
//        inventory.add(new GroceryItem("Cucumber", 1.99, 2, "Cucumber for tarator", "11-10-2023"));
//        inventory.add(new FragileItem("Vase", 89.99, 1, "Chinese vase", 5.0));
//
//        saveInventoryData();

        loadInventoryData();

        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.println("Inventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item by ID");
            System.out.println("3. List Items");
            System.out.println("4. Categorize Items");
            System.out.println("5. Place Order");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    listItems();
                    break;
                case 4:
                    categorizeItems();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }

        saveInventoryData();
        scanner.close();
    }

    // method to read all the items in a text file
    private static void loadInventoryData() {
        TextFileReader textFileReader = new TextFileReader();
        inventory = textFileReader.loadInventoryFromFile("D:\\Study\\SirmaAcademy\\Homework\\InventoryManagementSystem\\src\\Files\\items.txt");
    }

    // method to write all the items in a text file
    private static void saveInventoryData() {
        TextFileWriter textFileWriter = new TextFileWriter();
        textFileWriter.saveInventoryToFile(inventory, "D:\\Study\\SirmaAcademy\\Homework\\InventoryManagementSystem\\src\\Files\\items.txt");
    }

    // method to check if the category is correct
    public static boolean categoryCheck(String category) {
        if (category.equals("Electronic") || category.equals("Grocery") || category.equals("Fragile")) {
            return true;
        }

        return false;
    }

    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean expirationDateChecker(String date) {
        Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Matcher matcher = pattern.matcher(date);

        if (matcher.find()) {
            String[] parts = date.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (day > 31 || day < 1) {
                        return false;
                    } else {
                        return true;
                    }
                case 2:
                    if (isLeapYear(year)) {
                        if (day > 29 || day < 1) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        if (day > 28 || day < 1) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day > 30 || day < 1) {
                        return false;
                    } else {
                        return true;
                    }
                default:
                    return false;
            }
        }

        return false;
    }

    public static double doubleInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
            return doubleInput();
        }
    }

    public static int integerInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
            return integerInput();
        }
    }

    private static void addItem() {
        Scanner scanner = new Scanner(System.in);
        boolean isNew = false;

        System.out.println("Add a New Item");
        System.out.print("Enter the name of the item: ");
        String name = scanner.nextLine();

        System.out.print("Enter the category of the item: ");
        String category = scanner.nextLine();
        while (!categoryCheck(category)) {
            System.out.println("Invalid category. Please enter category again: ");
            category = scanner.nextLine();
        }

        System.out.print("Enter the price of the item: ");
        double price = doubleInput();

        System.out.print("Enter the quantity of the item: ");
        int quantity = integerInput();

        System.out.print("Enter the details of the item: ");
        String details = scanner.nextLine();

        if ("Grocery".equalsIgnoreCase(category)) {
            System.out.print("Enter the expiration date (e.g., 31-12-2023): ");
            String expirationDate = scanner.nextLine();
            while (!expirationDateChecker(expirationDate)) {
                System.out.println("Invalid date format. Please, enter an expiration date: ");
                expirationDate = scanner.nextLine();
            }

            GroceryItem newItem = new GroceryItem(name, price, quantity, details, expirationDate);

            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i) == newItem) {
                    inventory.get(i).setQuantity(inventory.get(i).getQuantity() + newItem.getQuantity());
                    isNew = false;
                    break;
                } else {
                    isNew = true;
                }
            }

            if (isNew) {
                inventory.add(newItem);
            }
        } else if ("Fragile".equalsIgnoreCase(category)) {
            System.out.print("Enter the weight (in kilograms) of the item: ");
            double weight = scanner.nextDouble();

            FragileItem newItem = new FragileItem(name, price, quantity, details, weight);

            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i) == newItem) {
                    inventory.get(i).setQuantity(inventory.get(i).getQuantity() + newItem.getQuantity());
                    isNew = false;
                    break;
                } else {
                    isNew = true;
                }
            }

            if (isNew) {
                inventory.add(newItem);
            }
        } else if ("Electronic".equalsIgnoreCase(category)) {
            ElectronicsItem newItem = new ElectronicsItem(name, price, quantity, details);

            for (InventoryItem inventoryItem : inventory) {
                if (inventoryItem == newItem) {
                    inventoryItem.setQuantity(inventoryItem.getQuantity() + newItem.getQuantity());
                    isNew = false;
                    break;
                } else {
                    isNew = true;
                }
            }

            if (isNew) {
                inventory.add(newItem);
            }
        }

        System.out.println("Item added to the inventory.");

    }

    public static void removeItem() {
        boolean isFound = false;
        System.out.println("Remove an Item");
        System.out.print("Enter the ID of the item: ");
        int id = integerInput();

        for (int i = 0; i < inventory.size(); i++) {
            if (id == inventory.get(i).getItemID()) {
                isFound = true;
                inventory.remove(i);
                System.out.printf("Item with ID - %d is removed successfully!%n", id);
                break;
            }
        }

        if (!isFound) {
            System.out.printf("An item with ID - %d is not found", id);
        }
    }

    private static void listItems() {
        for (InventoryItem item : inventory) {
            System.out.println(item);
        }
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
