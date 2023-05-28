package model.map.room;

import java.util.ArrayList;

import controller.gameController.TurnObserver;
import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.map.Coordinates;
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

    public void generateMoves(TurnMapper generator) {
        ArrayList<Coordinates> moveCoords = generator.getMoveCoordinates();
        ArrayList<Direction> moveDirections = generator.getMoveDirections();

        for(int i = 0; i < moveCoords.size(); i++) {
            Coordinates moveCoord = moveCoords.get(i);
            Direction moveDirection = moveDirections.get(i);
            int row = moveCoord.getRow();
            int col = moveCoord.getCol();

            if(inBounds(moveCoord)) {
                this.layout[row][col].acceptTurnGenerator(generator, moveDirection);
            }
        }
    }

    private boolean inBounds(Coordinates tileCoord) {
        int row = tileCoord.getRow();
        int col = tileCoord.getCol();

        boolean validRow = row >= 0 && row < this.height;
        boolean validCol = col >= 0 && col < this.width;

        return validRow && validCol;
    }
}
