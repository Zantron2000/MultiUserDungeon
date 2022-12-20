package model.map;

import model.map.room.Room;

public interface Map {
    public void moveRooms(Direction direction);

    public Room getCurrentRoom();
}
