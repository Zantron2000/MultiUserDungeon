package controller.gameGenerators;

import java.util.ArrayList;

import controller.gameController.TurnObserver;
import model.character.characters.NonPlayerCharacter;
import model.character.characters.PlayerCharacter;
import model.map.Coordinates;
import model.map.TimeObserver;
import model.map.room.Room;
import model.map.room.tile.Occupier;
import model.map.room.tile.Terrain;
import model.map.room.tile.Tile;
import model.map.room.tile.Occupiers.Chest;
import model.map.room.tile.Occupiers.Obstacle;
import model.map.room.tile.Occupiers.Trap;

public class RoomGenerator {
    public static Room generateRoom(int rows, int cols, String floorPlan, PlayerCharacter player) {
        ArrayList<TurnObserver> turnObservers = new ArrayList<>();
        ArrayList<TimeObserver> timeObservers = new ArrayList<>();
        Tile[][] layout = new Tile[rows][cols];

        String[] data = floorPlan.split("\n");

        for(int r = 0; r < rows; r++) {
            String[] strip = data[r].split("|");

            for(int c = 0; c < cols; c++) {
                Tile tile = RoomGenerator.generateTile(strip[c], r, c, player, turnObservers, timeObservers);

                layout[r][c] = tile;
            }
        }

        return new Room(layout, turnObservers, timeObservers);
    }

    private static Tile generateTile(String occupier, int row, int col, PlayerCharacter player, ArrayList<TurnObserver> turnObservers, ArrayList<TimeObserver> timeObservers) {
        Coordinates coords = new Coordinates(row, col);
        Tile tile;

        if(occupier.equals("B")) {
            tile = new Tile(coords);
        } else if(occupier.equals("P")) {
            tile = new Tile(coords, player);
            player.moveOnto(tile);
        } else if(occupier.equals("C")) {
            Occupier chest = new Chest(ItemGenerator.generateChestItems(), 100);

            tile = new Tile(coords, chest);
        } else if(occupier.equals("E")) {
            NonPlayerCharacter enemy = EnemyGenerator.generateNPC();
            turnObservers.add(enemy);
            timeObservers.add(enemy);

            tile = new Tile(coords, enemy);
            enemy.moveOnto(tile);
        } else if(occupier.equals("T")) {
            Terrain trap = new Trap(5);

            tile = new Tile(coords, trap);
        } else if(occupier.equals("O")) {
            Occupier obstacle = new Obstacle("A giant rock");

            tile = new Tile(coords, obstacle);
        } else {
            System.out.println("Error: " + occupier);

            tile = null;
        }

        return tile;
    }
}
