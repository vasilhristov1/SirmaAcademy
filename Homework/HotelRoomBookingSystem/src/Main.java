import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HotelManagementSystem hotelManagementSystem = new HotelManagementSystem();

//        Gson gson = new Gson();
//        List<RoomType> roomTypes = new ArrayList<>();
//
//        try (FileReader reader = new FileReader("D:\\Study\\SirmaAcademy\\Homework\\HotelRoomBookingSystem\\src\\files\\roomTypes.json")) {
//            Type roomTypeListType = new TypeToken<List<RoomType>>() {}.getType();
//            roomTypes = gson.fromJson(reader, roomTypeListType);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (RoomType roomType : roomTypes) {
//            System.out.println(roomType.getType());
//        }
    }
}
