import java.util.ArrayList;
import java.util.List;

public class RoomType {
    private String type;
    private List<String> amenities;
    private int maximumOccupancy;

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

    public void setType(String type) {
        this.type = type;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public void setMaximumOccupancy(int maximumOccupancy) {
        this.maximumOccupancy = maximumOccupancy;
    }

    @Override
    public String toString() {
        return  type + " room - " +
                "Amenities: " + amenities +
                ", MaximumOccupancy=" + maximumOccupancy;
    }
}
