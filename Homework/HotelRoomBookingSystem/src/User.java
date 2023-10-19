import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Booking> bookings;
    private double fees;
    private double sum;

    public User(String name, String password) {
        this.username = name;
        this.password = password;
        this.bookings = new ArrayList<>();
        this.fees = 0.0;
        this.sum = 0.0;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }

    public double getFees() {
        return fees;
    }

    public double getSum() {
        return sum;
    }

    public void increaseSum(double sum) {
        this.sum += sum;
    }

    public void increaseFees(double fee) {
        this.fees += fee;
    }

    public void decreaseSum(double sum) {
        this.sum -= sum;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public void viewProfile() {
        System.out.printf("Username: %s%n", this.getUsername());
        System.out.printf("Fees: %.2f%n", this.getFees());
        System.out.printf("Sum: %.2f%n", this.getSum());
        System.out.println("Bookings: ");
        int i = 1;
        for (Booking booking : bookings) {
            System.out.println(i + ". " + booking);
            i++;
        }
    }

    @Override
    public String toString() {
        return "Username: " + username +
                ", \nPassword: " + password +
                ", \nBookings: " + bookings +
                ", \nFees: " + fees +
                ", \nSum: " + sum;
    }
}
