package model.map.factories;

import java.util.HashMap;

import model.character.PlayerCharacter;
import model.map.Coordinates;
import model.map.Direction;
import model.map.EndlessMap;
import model.map.Map;
import model.map.room.Room;
import model.map.room.factories.RandomRoomFactory;
import model.map.room.factories.SafeRoomFactory;

public class EndlessMapFactory {
    private RandomRoomFactory randomFactory;
    private SafeRoomFactory safeFactory;

    public EndlessMapFactory() {
        randomFactory = new RandomRoomFactory();
        safeFactory = new SafeRoomFactory();
    }

    public Map generate(PlayerCharacter player) {
        Coordinates start = new Coordinates(0, 0);
        Room currentRoom = safeFactory.generateRandomRoom(0, 0, player);
        HashMap<Coordinates, Room> layout = new HashMap<>();
        HashMap<PlayerCharacter, Room> safeRooms = new HashMap<>();

        layout.put(start, currentRoom);
        safeRooms.put(player, currentRoom);
        layout = this.generateRooms(layout, start);

        return new EndlessMap(layout, currentRoom, safeRooms);
    }

    private HashMap<Coordinates, Room> generateRooms(HashMap<Coordinates, Room> layout, Coordinates coords) {
        Coordinates north = coords.nextCoordinates(Direction.NORTH);
        Coordinates east = coords.nextCoordinates(Direction.EAST);
        Coordinates south = coords.nextCoordinates(Direction.SOUTH);
        Coordinates west = coords.nextCoordinates(Direction.WEST);

        layout.put(north, this.randomFactory.generateRandomRoom(north));
        layout.put(east, this.randomFactory.generateRandomRoom(east));
        layout.put(south, this.randomFactory.generateRandomRoom(south));
        layout.put(west, this.randomFactory.generateRandomRoom(west));

        return layout;
    }
}
