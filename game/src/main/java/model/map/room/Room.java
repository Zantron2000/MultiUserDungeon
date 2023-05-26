package model.map.room;

import java.util.ArrayList;

import controller.gameController.TurnObserver;
import model.map.Cycle;
import model.map.TimeObserver;
import model.map.room.tile.Tile;

public class Room implements TurnObserver, TimeObserver {
    private int width;
    private int height;
    private Tile[][] layout;
    private ArrayList<TurnObserver> turnObservers;
    private ArrayList<TimeObserver> timeObservers;

    public Room(Tile[][] layout, ArrayList<TurnObserver> turnObservers, ArrayList<TimeObserver> timeObservers) {
        this.height = layout.length;
        this.width = layout[0].length;
        this.layout = layout;
        this.turnObservers = turnObservers;
        this.timeObservers = timeObservers;
    }

    public void processTurn() {
        for(TurnObserver observer : this.turnObservers) {
            observer.processTurn();
        }
    }

    public void changeTime(Cycle time) {
        for(TimeObserver observer : this.timeObservers) {
            observer.changeTime(time);
        }
    }
}
