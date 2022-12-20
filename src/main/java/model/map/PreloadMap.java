package model.map;

import java.util.HashMap;

import model.map.room.Room;

public class PreloadMap implements Map {
    private HashMap<Coordinates, Room> layout;
    private Room currentRoom;

    public PreloadMap(HashMap<Coordinates, Room> layout, Room currentRoom) {
        this.layout = layout;
        this.currentRoom = currentRoom;
    }
    
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void moveRooms(Direction direction) {
        Coordinates coords = currentRoom.getNeighbor(direction);
        Room room = layout.get(coords);

        if(room != null) {
            this.currentRoom = room;
        }
    }
}
