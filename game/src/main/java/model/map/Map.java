package model.map;

import java.util.HashMap;

import controller.gameController.TurnObserver;
import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.map.room.Room;
import model.map.room.tile.Tile;

public class Map implements TurnObserver {
    private Room currentRoom;
    private Coordinates currentCoords;
    private HashMap<Coordinates, Room> layout;
    private Time time;

    public Map() {
        this.time = new Time();
    }

    public void moveRooms(Character character, Direction direction) {
        Coordinates newRoomCoords = Direction.applyDirection(currentCoords, direction);
        Room newRoom = this.layout.get(newRoomCoords);
        Tile enterance = newRoom.getExit(Direction.getReverse(direction));

        character.moveOnto(enterance);
        this.currentCoords = newRoomCoords;
        this.currentRoom = newRoom;
    }

    public void finishSetup(Room currentRoom, HashMap<Coordinates, Room> layout) {
        this.currentRoom = currentRoom;
        this.layout = layout;

        this.time.updateObserver(this.currentRoom);
    }

    public void processTurn() {
        this.time.processTurn();
        this.currentRoom.processTurn();
    }

    public void generateMoves(TurnMapper generator) {
        this.currentRoom.generateMoves(generator);
    }
}
