import java.util.Date;
import java.util.Random;

public class Booking {
    private int bookingID;
    private String roomNumber;
    private User user;
    private Date checkIn;
    private Date checkOut;
    Random random = new Random();


    public Booking(String roomNumber, User user, Date checkIn, Date checkOut) {
        this.bookingID = random.nextInt(0, 100000);
        this.roomNumber = roomNumber;
        this.user = user;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public User getUser() {
        return user;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "ID: " + bookingID +
                ". \nRoom - " + roomNumber +
                ", \nUser - " + user.getUsername() +
                ", \nCheck In - " + checkIn +
                ", \nCheck Out - " + checkOut;
    }
}
