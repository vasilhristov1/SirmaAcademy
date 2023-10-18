import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelManagementSystem {
    public List<RoomType> roomTypes;
    public List<Room> rooms;
    public List<User> users;
    public List<Booking> bookings;
    public Menu menu;
    public User currentUser;
    public double totalIncome;
    public double totalCancellationFees;

    public HotelManagementSystem() {
        initialize();

        do {
            int choice = menu.displayMainMenu();
            switch (choice) {
                case 1:
                    int ch1 = menu.displayViewRoomsMenu();
                    switch (ch1) {
                        case 1:
                            menu.displayAllRooms(this.rooms);
                            break;
                        case 2:
                            menu.displayAvailableRooms(this.rooms);
                            break;
                        case 3:
                            continue;
                    }
                    break;
                case 2:
                    if (currentUser != null) {
                        String[] bookingValues = menu.displayBookingMenu();
                        String roomNumber = bookingValues[0];
                        String[] checkIn = bookingValues[1].split("-");
                        String[] checkOut = bookingValues[2].split("-");
                        int yearIn = Integer.parseInt(checkIn[0]);
                        int monthIn = Integer.parseInt(checkIn[1]);
                        int dayIn = Integer.parseInt(checkIn[2]);
                        int yearOut = Integer.parseInt(checkOut[0]);
                        int monthOut = Integer.parseInt(checkOut[1]);
                        int dayOut = Integer.parseInt(checkOut[2]);
                        Date start = new Date(yearIn, monthIn - 1, dayIn);
                        Date end = new Date(yearOut, monthOut - 1, dayOut);

                        Room roomToBook = checkAvailability(rooms, roomNumber, start, end, bookings);

                        if (roomToBook != null) {
                            Booking booking = new Booking(roomToBook.getRoomNumber(), currentUser, start, end);
                            currentUser.addBooking(booking);
                            long diffDays = daysDifference(start, end);
                            currentUser.increaseSum(diffDays * roomToBook.getPricePerNight());
                            this.totalIncome += diffDays * roomToBook.getPricePerNight();
                            this.bookings.add(booking);
                            this.rooms = changeStatusTo(this.rooms, roomToBook, "booked");
                            writeModifiedRooms(this.rooms);
                        } else {
                            System.out.println("The chosen room is not available!");
                        }
                    } else {
                        int ch2 = menu.displayEntranceMenu();
                        switch (ch2) {
                            case 1:
                                String[] credentials21 = menu.displayLoginMenu();
                                User checker = containsUser(users, credentials21[0], credentials21[1]);
                                if (containsUser(users, credentials21[0], credentials21[1]) != null) {
                                    currentUser = checker;
                                } else {
                                    System.out.println("A user with this name is not registered");
                                    currentUser = null;
                                }
                                continue;
                            case 2:
                                String[] credentials22 = menu.displayRegistrationMenu();
                                if (containsUser(users, credentials22[0], credentials22[1]) == null) {
                                    currentUser = new User(credentials22[0], credentials22[1]);
                                    users.add(currentUser);
                                } else {
                                    System.out.println("A user with this name is already registered");
                                    currentUser = null;
                                }
                                continue;
                            case 3:
                                int ch31 = menu.displayAdminMenu();
                                adminOperations(ch31, this.menu, this.bookings, this.users, this.rooms, this.totalIncome, this.totalCancellationFees);
                                continue;
                            case 4:
                                continue;
                        }
                    }
                    break;
                case 3:
                    if (currentUser != null) {
                        int ch3 = menu.displayCancelBookingMenu();
                        if (ch3 != 0) {
                            if (!bookings.isEmpty()) {
                                boolean bookingExists = false;
                                User bookingUser = null;
                                String numberOfCancelledRoom = null;
                                Date start = new Date();
                                Date end = new Date();
                                for (int i = 0; i < bookings.size(); i++) {
                                    if (bookings.get(i).getBookingID() == ch3) {
                                        bookingExists = true;
                                        start = bookings.get(i).getCheckIn();
                                        end = bookings.get(i).getCheckOut();
                                        bookingUser = bookings.get(i).getUser();
                                        numberOfCancelledRoom = bookings.get(i).getRoomNumber();
                                        bookings.remove(i);
                                        break;
                                    }
                                }

                                if (bookingExists && bookingUser != null) {
                                    double userCancellationFee = 0.0;
                                    long diffDays = daysDifference(start, end);
                                    double roomPricePerNight = 0.0;
                                    for (int i = 0; i < rooms.size(); i++) {
                                        if (rooms.get(i).getRoomNumber().equals(numberOfCancelledRoom)) {
                                            userCancellationFee = rooms.get(i).getCancellationFee();
                                            roomPricePerNight = rooms.get(i).getPricePerNight();
                                            this.rooms = changeStatusTo(rooms, rooms.get(i), "available");
                                            writeModifiedRooms(this.rooms);
                                            break;
                                        }
                                    }
                                    for (User user : users) {
                                        if (user.getUsername().equals(bookingUser.getUsername())) {
                                            for (int j = 0; j < user.getBookings().size(); j++) {
                                                if (user.getBookings().get(j).getBookingID() == ch3) {
                                                    user.getBookings().remove(j);
                                                    user.increaseFees(userCancellationFee);
                                                    user.decreaseSum(diffDays * roomPricePerNight);
                                                    this.totalIncome -= diffDays * roomPricePerNight;
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                    }

                                    this.totalCancellationFees += userCancellationFee;
                                }
                            }
                        }
                    } else {
                        int ch3 = menu.displayEntranceMenu();
                        switch (ch3) {
                            case 1:
                                String[] credentials31 = menu.displayLoginMenu();
                                User checker = containsUser(users, credentials31[0], credentials31[1]);
                                if (containsUser(users, credentials31[0], credentials31[1]) != null) {
                                    currentUser = checker;
                                } else {
                                    System.out.println("A user with this name is not registered");
                                    currentUser = null;
                                }
                                continue;
                            case 2:
                                String[] credentials32 = menu.displayRegistrationMenu();
                                if (containsUser(users, credentials32[0], credentials32[1]) == null) {
                                    currentUser = new User(credentials32[0], credentials32[1]);
                                    users.add(currentUser);
                                } else {
                                    System.out.println("A user with this name is already registered");
                                    currentUser = null;
                                }
                                continue;
                            case 3:
                                int ch31 = menu.displayAdminMenu();
                                adminOperations(ch31, this.menu, this.bookings, this.users, this.rooms, this.totalIncome, this.totalCancellationFees);
                                continue;
                            case 4:
                                continue;
                        }
                    }
                    break;
                case 4:
                    if (currentUser == null) {
                        int ch4 = menu.displayEntranceMenu();
                        switch (ch4) {
                            case 1:
                                String[] credentials1 = menu.displayLoginMenu();

                                User checker = containsUser(users, credentials1[0], credentials1[1]);
                                if (checker != null) {
                                    currentUser = checker;
                                    currentUser.viewProfile();
                                } else {
                                    System.out.println("A user with this name is not registered");
                                    currentUser = null;
                                }
                                continue;
                            case 2:
                                String[] credentials2 = menu.displayRegistrationMenu();
                                if (containsUser(users, credentials2[0], credentials2[1]) == null) {
                                    currentUser = new User(credentials2[0], credentials2[1]);
                                    users.add(currentUser);
                                    currentUser.viewProfile();
                                } else {
                                    System.out.println("A user with this name is already registered");
                                    currentUser = null;
                                }
                                continue;
                            case 3:
                                int ch31 = menu.displayAdminMenu();
                                adminOperations(ch31, this.menu, this.bookings, this.users, this.rooms, this.totalIncome, this.totalCancellationFees);
                                continue;
                            case 4:
                                continue;
                        }
                    } else {
                        currentUser.viewProfile();
                    }
                    break;
                case 5:
                    if (currentUser != null) {
                        currentUser = null;
                    } else {
                        System.out.println("You are not logged in the system");
                    }
                    break;
                case 6:
                    menu.close();
                    return;
            }
        } while (true);
    }

    public void initialize() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("D:\\Study\\SirmaAcademy\\Homework\\HotelRoomBookingSystem\\src\\files\\roomTypes.json")) {
            Type roomTypeListType = new TypeToken<List<RoomType>>() {}.getType();
            this.roomTypes = gson.fromJson(reader, roomTypeListType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader("D:\\Study\\SirmaAcademy\\Homework\\HotelRoomBookingSystem\\src\\files\\rooms.json")) {
            Type roomListType = new TypeToken<List<Room>>() {}.getType();
            this.rooms = gson.fromJson(reader, roomListType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.menu = new Menu();
        this.currentUser = null;
    }

    public static User containsUser(List<User> users, String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public static Room checkAvailability(List<Room> rooms, String roomNumber, Date start, Date end, List<Booking> bookingsList) {
        for (Room room : rooms) {
            if (room.getStatus().equals("available") && room.getRoomNumber().equals(roomNumber) && !checkIfBooked(bookingsList, roomNumber, start, end)) {
                return room;
            }
        }

        return null;
    }

    public static boolean checkIfBooked(List<Booking> bookings, String roomNumber, Date start, Date end) {
        if (!bookings.isEmpty()) {
            for (Booking booking : bookings) {
                if (booking.getRoomNumber().equals(roomNumber)) {
                    Date bookingCheckIn = booking.getCheckIn();
                    if (bookingCheckIn.compareTo(end) < 0 && bookingCheckIn.compareTo(start) > 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static List<Room> changeStatusTo(List<Room> rooms, Room room, String status) {
        List<Room> updatedRooms;

        for (Room value : rooms) {
            if (value.getRoomNumber().equals(room.getRoomNumber())) {
                value.setStatus(status);
            }
        }

        updatedRooms = rooms;

        return updatedRooms;
    }

    public static long daysDifference(Date start, Date end) {
        return ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static void writeModifiedRooms(List<Room> rooms_) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = "D:\\Study\\SirmaAcademy\\Homework\\HotelRoomBookingSystem\\src\\files\\rooms_.json";

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(rooms_, writer);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void adminOperations(int choice, Menu men, List<Booking> books, List<User> usrs, List<Room> rms, double ti, double tcf) {
        switch (choice) {
            case 1:
                if (books.isEmpty()) {
                    System.out.println("There are no bookings registered yet.");
                } else {
                    for (Booking book : books) {
                        System.out.println(book);
                    }
                }
                break;
            case 2:
                System.out.println("Income Report");
                System.out.printf("Total income: %.2f%n", ti);
                System.out.printf("Total cancellation fees: %.2f%n", tcf);
                System.out.printf("Total: %.2f%n", (ti + tcf));
                break;
            case 3:
                String[] roomDetails = men.displayAddRoomMenu();
                Room newRoom = new Room(roomDetails[0], roomDetails[1], Double.parseDouble(roomDetails[2]), Double.parseDouble(roomDetails[3]), roomDetails[4]);
                String yesOrNo = men.getUserInput("Do you want to add this room? (Y/N)");

                if (yesOrNo.equals("Y")) {
                    rms.add(newRoom);
                    writeModifiedRooms(rms);
                }

                break;
            case 4:
                String roomRemove = men.displayRemoveRoomMenu();
                String yn = men.getUserInput("Do you want to remove this room? (Y/N)");

                if (yn.equals("Y")) {
                    boolean isRoomRemoved = false;
                    for (int i = 0; i < rms.size(); i++) {
                        if (rms.get(i).getRoomNumber().equals(roomRemove) && rms.get(i).getStatus().equals("available")) {
                            rms.remove(i);
                            writeModifiedRooms(rms);
                            isRoomRemoved = true;
                            break;
                        }
                    }
                    if (isRoomRemoved) {
                        System.out.println("The room is removed successfully!");
                    } else {
                        System.out.println("The room cannot be removed!");
                    }
                }
                break;
            case 5:
                String modifyValue = men.displayModifyRoomMenu();
                boolean roomExists = false;
                for (Room rm : rms) {
                    if (rm.getRoomNumber().equals(modifyValue) && rm.getStatus().equals("available")) {
                        roomExists = true;
                        do {
                            boolean isExit = false;
                            String input = men.getUserInput("Enter your choice: ");
                            switch (Integer.parseInt(input)) {
                                case 1 -> {
                                    String newRoomNum = men.getUserInput("Enter the new number of the room: ");
                                    rm.setRoomNumber(newRoomNum);
                                    writeModifiedRooms(rms);
                                }
                                case 2 -> {
                                    String newRoomType = men.getUserInput("Enter the new type of the room: ");
                                    rm.setType(newRoomType);
                                    writeModifiedRooms(rms);
                                }
                                case 3 -> {
                                    String newRoomPrice = men.getUserInput("Enter the new price of the room: ");
                                    rm.setPricePerNight(Double.parseDouble(newRoomPrice));
                                    writeModifiedRooms(rms);
                                }
                                case 4 -> {
                                    String newRoomFee = men.getUserInput("Enter the new cancellation fee of the room: ");
                                    rm.setCancellationFee(Double.parseDouble(newRoomFee));
                                    writeModifiedRooms(rms);
                                }
                                case 5 -> {
                                    String newRoomStatus = men.getUserInput("Enter the new status of the room: ");
                                    rm.setStatus(newRoomStatus);
                                    writeModifiedRooms(rms);
                                }
                                case 6 -> isExit = true;
                            }

                            if (isExit) {
                                break;
                            }
                        } while (true);
                        break;
                    }
                }
                if (!roomExists) {
                    System.out.println("There is not such a room or the room is not available!");
                }
                break;
            case 6:
                break;
        }
    }
}