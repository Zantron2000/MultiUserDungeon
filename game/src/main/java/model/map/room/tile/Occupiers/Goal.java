package model.map.room.tile.Occupiers;

import controller.gameController.Game;
import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.map.room.tile.Terrain;

public class Goal implements Terrain {
    private Game game;

    public Goal(Game game) {
        this.game = game;
    }

    public void interact(Character character) {
        this.game.notifyEscape();
    }

    public void moveOnto(Character character) {
        // TODO PTUI for asking if they want to move on
    }

    public void acceptTurnGenerator(TurnMapper mapper, Direction direction) {
        // TODO make method for mapper and call here
    }
}
