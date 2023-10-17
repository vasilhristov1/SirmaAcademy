public class Room {
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private String status;

    public Room() {
        this.roomNumber = null;
        this.type = null;
        this.pricePerNight = 0.0;
        this.cancellationFee = 0.0;
        this.status = null;
    }

    public Room(String roomNumber, String type, double pricePerNight, double cancellationFee, String status) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.status = status;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public String getType() {
        return this.type;
    }

    public double getPricePerNight() {
        return this.pricePerNight;
    }

    public double getCancellationFee() {
        return this.cancellationFee;
    }

    public String getStatus() {
        return this.status;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber +
                " Type - " + type +
                ", Price Per Night - " + pricePerNight +
                ", Cancellation Fee - " + cancellationFee +
                ", Status - " + status;
    }
}
