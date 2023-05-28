package controller.gameGenerators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import model.character.characters.PlayerCharacter;
import model.map.Coordinates;
import model.map.Map;
import model.map.room.Room;

public class MapGenerator {
    public static Map generateMap(String filePath, PlayerCharacter player) {
        Map map = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            HashMap<Coordinates, Room> layout = new HashMap<>();
            Room currentRoom = null;
            String line = reader.readLine();
            int numRooms;
            
            line = reader.readLine();
            numRooms = Integer.parseInt(line);


            while (numRooms > 0) {
                String[] stringCoords = reader.readLine().split(" ");
                String[] stringDim = reader.readLine().split(" ");

                Coordinates coords = new Coordinates(Integer.parseInt(stringCoords[0]), Integer.parseInt(stringCoords[1]));
                int rows = Integer.parseInt(stringDim[0]);
                int cols = Integer.parseInt(stringDim[1]);

                String floorPlan = "";
                for(int r = 0; r < rows; r++) {
                    floorPlan += reader.readLine();
                }

                Room room = RoomGenerator.generateRoom(rows, cols, floorPlan, player);

                if(floorPlan.contains("P")) {
                    currentRoom = room;
                }

                layout.put(coords, room);
            }

            map = new Map(currentRoom, layout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
