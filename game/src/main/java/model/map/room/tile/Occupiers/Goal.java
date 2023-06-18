package model.map.room.tile.Occupiers;

import controller.gameController.Game;
import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.map.room.tile.Terrain;

public class Goal implements Terrain {
    private static char ICON = 'G';

    private Game game;

    public Goal(Game game) {
        this.game = game;
    }

    public String interact(Character character) {
        this.game.notifyEscape();

        return "The player has escaped from the dungeon";
    }

    public String moveOnto(Character character) {
        return "Stepped onto the portal of freedom";
    }

    public void acceptTurnGenerator(TurnMapper mapper, Direction direction) {
        mapper.generateCommand(this, direction);
    }

    public char getIcon() {
        return Goal.ICON;
    }
}
