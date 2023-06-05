package controller.gameGenerators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import controller.gameController.Game;
import model.character.characters.PlayerCharacter;
import model.map.Coordinates;
import model.map.Map;
import model.map.room.Room;

public class MapGenerator {
    public static Map generateMap(String filePath, PlayerCharacter player, Game game) {
        EnemyGenerator.loadEnemies("data/characters/enemies.csv");
        ItemGenerator.loadItems("data/items/armor.csv", "data/items/bags.csv", "data/items/buffs.csv", "data/items/weapons.csv", "data/items/food.csv");
        Map map = new Map();

        RoomGenerator.setMap(map);
        RoomGenerator.setGame(game);
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
                    floorPlan += reader.readLine() + "\n";
                }

                Room room = RoomGenerator.generateRoom(rows, cols, floorPlan, player);

                if(floorPlan.contains("P")) {
                    currentRoom = room;
                }

                layout.put(coords, room);
                numRooms--;
            }

            map.finishSetup(currentRoom, layout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
