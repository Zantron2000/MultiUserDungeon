package model.map;

import java.util.HashMap;

import model.map.room.Room;

public class Map {
    private Room currentRoom;
    private HashMap<Coordinates, Room> layout;
    private Time time;

    public Map(Room currentRoom, HashMap<Coordinates, Room> layout) {
        this.currentRoom = currentRoom;
        this.layout = layout;
        this.time = new Time();
    }
}
