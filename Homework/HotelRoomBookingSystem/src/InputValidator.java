public class InputValidator {
    public boolean isValidUsername(String username){
        return username != null && username.length() >= 6;
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= 8 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }

    public boolean isValidRoomNumber(String roomNumber) {
        return roomNumber != null && roomNumber.matches("\\d{3}");
    }

    public boolean isValidDate(String date) {
        return date != null && date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
