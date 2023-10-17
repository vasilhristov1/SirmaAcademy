import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
//    public static Room checkAvailability(List<Room> rooms, String roomNumber, Date start, Date end, List<Booking> bookingsList) {
//        for (int i = 0; i < rooms.size(); i++) {
//            Room room = rooms.get(i);
//            if (room.getStatus().equals("available") && room.getRoomNumber().equals(roomNumber) && !checkIfBooked(bookingsList, roomNumber, start, end)) {
//                return room;
//            }
//        }
//
//        return null;
//    }
//
//    public static boolean checkIfBooked(List<Booking> bookings, String roomNumber, Date start, Date end) {
//        if (bookings.isEmpty()) {
//            return false;
//        } else {
//            for (int i = 0; i < bookings.size(); i++) {
//                Booking booking = bookings.get(i);
//                if (booking.getRoomNumber().equals(roomNumber)) {
//                    Date bookingCheckIn = booking.getCheckIn();
//                    if (bookingCheckIn.compareTo(end) < 0 && bookingCheckIn.compareTo(start) > 0) {
//                        return false;
//                    }
//                }
//            }
//        }
//
//        return true;
//    }
//
//    public static List<Room> changeStatusToBooked(List<Room> rooms, Room room) {
//        List<Room> updatedRooms = new ArrayList<>();
//
//        for (int i = 0; i < rooms.size(); i++) {
//            if (rooms.get(i).getRoomNumber().equals(room.getRoomNumber())) {
//                rooms.get(i).setStatus("booked");
//            }
//        }
//
//        updatedRooms = rooms;
//
//        return updatedRooms;
//    }

    public static void main(String[] args) {
//        Gson gson = new Gson();
//        List<Room> rooms = new ArrayList<>();
//        List<Booking> bookings = new ArrayList<>();
//
//        try (FileReader reader = new FileReader("D:\\Study\\SirmaAcademy\\Homework\\HotelRoomBookingSystem\\src\\files\\rooms.json")) {
//            Type roomListType = new TypeToken<List<Room>>() {}.getType();
//            rooms = gson.fromJson(reader, roomListType);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        User u1 = new User("Vasko", "1235sfg");
//        Booking b1 = new Booking(rooms.get(0).getRoomNumber(), u1, new Date(2023, 3, 10), new Date(2023, 3, 15));
//        Booking b2 = new Booking(rooms.get(1).getRoomNumber(), u1, new Date(2023, 3, 10), new Date(2023, 3, 15));
//        Booking b3 = new Booking(rooms.get(2).getRoomNumber(), u1, new Date(2023, 3, 10), new Date(2023, 3, 15));
//
//        u1.addBooking(b1);
//        u1.addBooking(b2);
//        u1.addBooking(b3);
//
//        u1.viewProfile();
//
//        ////////////////////////////////////////////////////////
//
//        Menu menu = new Menu();
//        if (u1 != null) {
//            String[] bookingValues = menu.displayBookingMenu();
//            String roomNumber = bookingValues[0];
//            String[] checkIn = bookingValues[1].split("-");
//            String[] checkOut = bookingValues[2].split("-");
//            int yearIn = Integer.parseInt(checkIn[0]);
//            int monthIn = Integer.parseInt(checkIn[1]);
//            int dayIn = Integer.parseInt(checkIn[2]);
//            int yearOut = Integer.parseInt(checkOut[0]);
//            int monthOut = Integer.parseInt(checkOut[1]);
//            int dayOut = Integer.parseInt(checkOut[2]);
//            Date start = new Date(yearIn, monthIn - 1, dayIn);
//            Date end = new Date(yearOut, monthOut - 1, dayOut);
//
//            Room roomToBook = checkAvailability(rooms, roomNumber, start, end, bookings);
//
//            if (roomToBook != null) {
//                Booking booking = new Booking(roomToBook.getRoomNumber(), currentUser, start, end);
//                currentUser.addBooking(booking);
//                this.bookings.add(booking);
//                this.rooms = changeStatusToBooked(this.rooms, roomToBook);
//            } else {
//                System.out.println("The chosen room is not available for this period");
//            }
//        }

        ////////////////////////////////////////////////////////

        HotelManagementSystem hotelManagementSystem = new HotelManagementSystem();
    }
}
