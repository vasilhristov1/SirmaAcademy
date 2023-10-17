import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scan;

    public Menu() {
        this.scan = new Scanner(System.in);
    }

    public int displayMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. View Rooms");
        System.out.println("2. Book a Room");
        System.out.println("3. Cancel Booking");
        System.out.println("4. View Profile");
        System.out.println("5. Log Out");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        return this.getIntInput();
    }

    public int displayViewRoomsMenu() {
        System.out.println("View Rooms");
        System.out.println("1. View All Rooms");
        System.out.println("2. View Available Rooms");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter your choice: ");
        return this.getIntInput();
    }

    public String[] displayBookingMenu() {
        InputValidator inputValidator = new InputValidator();
        String[] tokens = new String[3];

        System.out.println("Which room would you like to book?");
        String roomNumber = scan.nextLine();

        System.out.println("Enter your check in date: ");
        String checkInDate = scan.nextLine();
        while (!inputValidator.isValidDate(checkInDate)) {
            System.out.println("Please, enter a valid date: ");
            checkInDate = scan.nextLine();
        }

        System.out.println("Enter your check out date: ");
        String checkOutDate = scan.nextLine();
        while (!inputValidator.isValidDate(checkOutDate)) {
            System.out.println("Please, enter a valid date: ");
            checkOutDate = scan.nextLine();
        }

        tokens[0] = roomNumber;
        tokens[1] = checkInDate;
        tokens[2] = checkOutDate;

        return tokens;
    }

    public int displayEntranceMenu() {
        System.out.println("Login(Login as Admin) or Signup");
        System.out.println("1. Login \t 2. Signup \t 3. Admin \t 4. Back to Main Menu");
        return this.getIntInput();
    }

    public String[] displayLoginMenu() {
        System.out.println("Login");
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();

        String[] credentials = new String[2];
        credentials[0] = username;
        credentials[1] = password;

        return credentials;
    }

    public String[] displayRegistrationMenu() {
        InputValidator inputValidator = new InputValidator();
        String[] credentials = new String[2];
        System.out.println("Registration");

        System.out.print("Enter username: ");
        String username = scan.nextLine();
        while (!inputValidator.isValidUsername(username)) {
            System.out.println("Invalid username format. Enter new username: ");
            username = scan.nextLine();
        }

        System.out.print("Enter password: ");
        String password = scan.nextLine();
        while (!inputValidator.isValidPassword(password)) {
            System.out.println("Invalid password format. Enter new password: ");
            password = scan.nextLine();
        }

        System.out.print("Confirm password: ");
        do {
            if (scan.nextLine().equals(password)) {
                break;
            } else {
                System.out.println("The entered password is not the same. Try again!");
            }
        } while (true);

        credentials[0] = username;
        credentials[1] = password;

        return credentials;
    }

    public int displayAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("1. View All Bookings");
        System.out.println("2. View Income Report");
        System.out.println("3. Modify Room Details");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        return this.getIntInput();
    }

    public void displayRoomTypes(List<RoomType> roomTypes) {
        System.out.println("Room Types:");
        for (RoomType roomType : roomTypes) {
            System.out.println(roomType);
        }
    }

    public void displayAvailableRooms(List<Room> rooms) {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.getStatus().equals("available")) {
                System.out.println("Room Number: " + room.getRoomNumber() +
                        ", Type: " + room.getType() +
                        ", Price per Night: $" + room.getPricePerNight() +
                        ", Cancellation Fee: $" + room.getCancellationFee() +
                        ", Status: " + room.getStatus());
            }
        }
    }

    public void displayAllRooms(List<Room> rooms) {
        System.out.println("All Rooms:");
        for (Room room : rooms) {
            System.out.println("Room Number: " + room.getRoomNumber() +
                    ", Type: " + room.getType() +
                    ", Price per Night: $" + room.getPricePerNight() +
                    ", Cancellation Fee: $" + room.getCancellationFee() +
                    ", Status: " + room.getStatus());
        }
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    }

    public int getIntInput() {
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            return getIntInput();
        }
    }

    public void close() {
        scan.close();
    }
}
