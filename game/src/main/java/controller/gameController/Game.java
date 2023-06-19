package controller.gameController;

import java.util.ArrayList;
import java.util.HashMap;

import controller.InventoryManagement.InventoryController;
import controller.gameGenerators.MapGenerator;
import controller.gameGenerators.PlayerGenerator;
import controller.turnMapper.Command;
import controller.turnMapper.TurnMapper;
import controller.turnMapper.turnMappers.EnemyTurnMapper;
import controller.turnMapper.turnMappers.PlayerTurnMapper;
import model.character.characters.PlayerCharacter;
import model.map.Map;
import view.interactionPTUI.DungeonPTUI;

public class Game {
    private boolean escaped;
    private Map map;
    private PlayerCharacter player;
    private HashMap<String, Command> moves;

    public Game(String mapFile, String name, String description) {
        this.player = PlayerGenerator.generatePlayer(name, description);
        this.map = MapGenerator.generateMap(mapFile, player, this);
        this.moves = null;
        this.escaped = false;
    }

    public boolean isGameOver() {
        return this.escaped || this.player.isDead();
    }

    public void notifyEscape() {
        this.escaped = true;
    }

    public String getRoomLayout() {
        return this.map.getRoomLayout();
    }

    public ArrayList<String> getPlayerMoves(DungeonPTUI ptui) {
        TurnMapper mapper = new PlayerTurnMapper(player, ptui);
        this.moves = this.getMoves(mapper);

        ArrayList<String> moveDescriptions = new ArrayList<>();

        for(String key : this.moves.keySet()) {
            moveDescriptions.add(key + " - " + this.moves.get(key).getAction());
        }

        return moveDescriptions;
    }

    public String executeMove(String command) {
        Command move = this.moves.getOrDefault(command, null);

        if(move == null) {
            return "Invalid move";
        }

        move.execute();
        this.executeEnemyMoves();
        this.map.processTurn();
        this.player.processTurn();

        return move.getResults();
    }
    
    public InventoryController manageInventory() {
        return new InventoryController(this.player);
    }

    private void executeEnemyMoves() {
        TurnMapper mapper = new EnemyTurnMapper(player);
        HashMap<String, Command> enemyMoves = this.getMoves(mapper);

        for(Command command : enemyMoves.values()) {
            command.execute();
        }
    }

    private HashMap<String, Command> getMoves(TurnMapper mapper) {
        this.map.generateMoves(mapper);

        return mapper.getMoves();
    }

}
