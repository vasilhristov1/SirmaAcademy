import java.util.List;

public class RoomType {
    private final String type;
    private final List<String> amenities;
    private final int maximumOccupancy;

    public RoomType(String type, int maximumOccupancy, List<String> amenities) {
        this.type = type;
        this.amenities = amenities;
        this.maximumOccupancy = maximumOccupancy;
    }

    public String getType() {
        return this.type;
    }

    public List<String> getAmenities() {
        return this.amenities;
    }

    public int getMaximumOccupancy() {
        return this.maximumOccupancy;
    }

    @Override
    public String toString() {
        return  "Type: " + this.getType() + " room\n" +
                "Amenities: " + getAmenities() +
                ", \nMaximumOccupancy: " + getMaximumOccupancy();
    }
}
