import java.util.List;

public class InputValidator {
    public boolean isValidUsername(String username){
        return username != null && username.length() >= 6;
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= 8 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }

    public boolean isValidRoomNumber(String roomNumber) {
        return roomNumber != null && roomNumber.matches("\\d{3,}");
    }

    public boolean isValidDate(String date) {
        return date != null && date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public boolean isValidType(List<RoomType> roomTypeList, String type) {
        for (int i = 0; i < roomTypeList.size(); i++) {
            if (roomTypeList.get(i).getType().equals(type)) {
                return true;
            }
        }

        return false;
    }

    public boolean isValidStatus(String status) {
        if (status.equals("available") || status.equals("booked")) {
            return true;
        }

        return false;
    }
}
