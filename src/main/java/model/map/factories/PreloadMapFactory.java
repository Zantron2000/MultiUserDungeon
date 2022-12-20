package model.map.factories;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import model.character.PlayerCharacter;
import model.map.Coordinates;
import model.map.Map;
import model.map.PreloadMap;
import model.map.room.Room;
import model.map.room.factories.RoomFactory;

public class PreloadMapFactory {
    private String fileName;
    private RoomFactory roomFactory;

    public PreloadMapFactory(String fileName) {
        this.fileName = fileName;
        this.roomFactory = new RoomFactory();
    }

    public Map generate(PlayerCharacter player) {
        try {
            Room currentRoom = null;
            HashMap<Coordinates, Room> layout = new HashMap<>();
            File file = new File(this.fileName);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while(scanner.hasNext()) {
                String[] data = scanner.nextLine().split(",");
                
                int roomX = Integer.parseInt(data[0]);
                int roomY = Integer.parseInt(data[1]);
                String roomLayout = data[2];

                Room room = this.roomFactory.generatePremadeRoom(roomX, roomY, roomLayout, player);
                layout.put(new Coordinates(roomX, roomY), room);

                if(roomLayout.contains("P")) {
                    currentRoom = room;
                }
            }

            scanner.close();
            return new PreloadMap(layout, currentRoom);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
