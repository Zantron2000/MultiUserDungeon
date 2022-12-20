package model.map;

import java.util.HashMap;

import model.character.PlayerCharacter;
import model.map.room.Room;
import model.map.room.factories.RandomRoomFactory;

public class EndlessMap implements Map {
    private HashMap<Coordinates, Room> layout;
    private Room currentRoom;
    private HashMap<PlayerCharacter, Room> safeRooms;

    private RandomRoomFactory randomFactory;

    public EndlessMap(HashMap<Coordinates, Room> layout, Room currentRoom, HashMap<PlayerCharacter, Room> safeRooms) {
        this.layout = layout;
        this.currentRoom = currentRoom;
        this.safeRooms = safeRooms;
        this.randomFactory = new RandomRoomFactory();
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void moveRooms(Direction direction) {
        Coordinates coords = currentRoom.getNeighbor(direction);
        Room room = layout.get(coords);

        this.currentRoom = room;
        this.generateRooms(coords);
    }

    private void generateRooms(Coordinates coords) {
        Coordinates north = coords.nextCoordinates(Direction.NORTH);
        Coordinates east = coords.nextCoordinates(Direction.EAST);
        Coordinates south = coords.nextCoordinates(Direction.SOUTH);
        Coordinates west = coords.nextCoordinates(Direction.WEST);

        if(this.layout.get(north) == null) {
            this.layout.put(north, this.randomFactory.generateRandomRoom(north));
        }
        if(this.layout.get(east) == null) {
            this.layout.put(east, this.randomFactory.generateRandomRoom(east));
        }
        if(this.layout.get(south) == null) {
            this.layout.put(south, this.randomFactory.generateRandomRoom(south));
        }
        if(this.layout.get(west) == null) {
            this.layout.put(west, this.randomFactory.generateRandomRoom(west));
        }
    }
}
