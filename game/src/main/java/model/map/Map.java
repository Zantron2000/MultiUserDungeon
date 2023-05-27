package model.map;

import java.util.HashMap;

import controller.gameController.TurnObserver;
import controller.turnMapper.TurnGenerator;
import model.map.room.Room;

public class Map implements TurnObserver {
    private Room currentRoom;
    private HashMap<Coordinates, Room> layout;
    private Time time;

    public Map(Room currentRoom, HashMap<Coordinates, Room> layout) {
        this.currentRoom = currentRoom;
        this.layout = layout;
        this.time = new Time();

        this.time.updateObserver(this.currentRoom);
    }

    public void processTurn() {
        this.time.processTurn();
        this.currentRoom.processTurn();
    }

    public void generateMoves(TurnGenerator generator) {
        this.currentRoom.generateMoves(generator);
    }
}
