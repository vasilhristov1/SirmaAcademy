import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
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
                            currentUser.increaseSum(roomToBook.getPricePerNight());
                            long diffDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
                            this.totalIncome += diffDays * roomToBook.getPricePerNight();
                            this.bookings.add(booking);
                            this.rooms = changeStatusToBooked(this.rooms, roomToBook);
                        } else {
                            System.out.println("The chosen room is not available for this period");
                        }
                    } else {
                        int ch2 = menu.displayEntranceMenu();
                        switch (ch2) {
                            case 1:
                                String[] credentials21 = menu.displayLoginMenu();
                                User checker = containsUser(users, credentials21[0], credentials21[1]);
                                if (containsUser(users, credentials21[0], credentials21[1]) != null) {
                                    currentUser = checker;
                                    currentUser.viewProfile();
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
                                    currentUser.viewProfile();
                                } else {
                                    System.out.println("A user with this name is already registered");
                                    currentUser = null;
                                }
                                continue;
                            case 3: // TO DO
                                int ch31 = menu.displayAdminMenu();
                                switch (ch31) {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                }
                                continue;
                            case 4:
                                continue;
                        }
                    }
                    break;
                case 3:
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
                            case 3: // TO DO
                                int ch31 = menu.displayAdminMenu();
                                switch (ch31) {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                }
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
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                return users.get(i);
            }
        }

        return null;
    }

    public static Room checkAvailability(List<Room> rooms, String roomNumber, Date start, Date end, List<Booking> bookingsList) {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (room.getStatus().equals("available") && room.getRoomNumber().equals(roomNumber) && !checkIfBooked(bookingsList, roomNumber, start, end)) {
                return room;
            }
        }

        return null;
    }

    public static boolean checkIfBooked(List<Booking> bookings, String roomNumber, Date start, Date end) {
        if (!bookings.isEmpty()) {
            for (int i = 0; i < bookings.size(); i++) {
                Booking booking = bookings.get(i);
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

    public static List<Room> changeStatusToBooked(List<Room> rooms, Room room) {
        List<Room> updatedRooms = new ArrayList<>();

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber().equals(room.getRoomNumber())) {
                rooms.get(i).setStatus("booked");
            }
        }

        updatedRooms = rooms;

        return updatedRooms;
    }
}
