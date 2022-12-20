package model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.Character;
import model.character.PlayerCharacter;
import model.character.DiurnalNPC;
import model.map.room.concrete_tiles.*;
import model.map.room.concrete_tiles.trap.*;
import model.map.room.Tile;
import model.inventory.Item;
import model.inventory.concrete_items.*;

@Testable
public class TestTiles {
    @Test
    public void testMovableTileMoveOff() {
        Character player = new PlayerCharacter("John", "ABC");
        MovableTile tile = new EmptyTile(0, 0, player);

        Character currentPlayer = tile.moveOff();
        assertEquals(player, currentPlayer);
        assertEquals(null, tile.getOccupier());
    }

    @Test
    public void testMovableTileMoveOnSuccess() {
        Character player = new PlayerCharacter("John", "ABC");
        MovableTile tile = new EmptyTile(0, 0);

        tile.moveOn(player);
        assertEquals(player, tile.getOccupier());
    }

    @Test
    public void testMovableTileMoveOnFailure() {
        Character player = new PlayerCharacter("John", "ABC");
        Character player2 = new PlayerCharacter("Doe", "ABC");

        MovableTile tile = new EmptyTile(0, 0, player);

        tile.moveOn(player2);
        assertEquals(player, tile.getOccupier());
    }

    @Test
    public void testMovableTileRepresentation() {
        Character npc = new DiurnalNPC("John", "ABC");
        MovableTile tile = new EmptyTile(0, 0, npc);

        assertEquals("N", tile.getTileType());
    }

    @Test
    public void testEmptyTileTileTypeOccupied() {
        PlayerCharacter player = new PlayerCharacter("John", "ABC");
        EmptyTile tile = new EmptyTile(0, 0, player);

        assertEquals("P", tile.getTileType());
    }

    @Test
    public void testEmptyTileTileTypeUnoccupied() {
        EmptyTile tile = new EmptyTile(0, 0);

        assertEquals(" ", tile.getTileType());
    }

    @Test
    public void testObstacleTileType() {
        Tile tile = new ObstacleTile(0, 0, "A chair");

        assertEquals("O", tile.getTileType());
    }

    @Test
    public void testTrapTileDefaultCondition() {
        TrapTile tile = new TrapTile(0, 0);
        assertEquals(" ", tile.getTileType());
    }

    @Test
    public void testTrapTileHiddenMoveOn() {
        TrapTile tile = new TrapTile(0, 0);
        PlayerCharacter player = new PlayerCharacter("John", "Yea");

        tile.moveOn(player);

        assertEquals("P", tile.getTileType());
        assertEquals("V", tile.getTrapCondition().toString());
        assertEquals("Player John: Yea | 95/100 health 10 attack 0 defense", player.toString());
    }

    @Test
    public void testTrapTileHiddenAttemptDisarm() {
        TrapTile tileSuccess = new TrapTile(0, 0);
        TrapTile tileFailure = new TrapTile(0, 0);

        int successDamage = tileSuccess.attemptDisarmTester(true);
        int failureDamage = tileFailure.attemptDisarmTester(false);


        assertEquals("V", tileSuccess.getTileType());
        assertEquals("V", tileFailure.getTileType());
        assertEquals(0, successDamage);
        assertEquals(5, failureDamage);
    }

    @Test
    public void testTrapTileHiddenAttemptDetect() {
        TrapTile tileSuccess = new TrapTile(0, 0);
        TrapTile tileFailure = new TrapTile(0, 0);

        boolean success = tileSuccess.attemptDetectTester(true);
        boolean failure = tileFailure.attemptDetectTester(false);


        assertEquals("-", tileSuccess.getTileType());
        assertEquals(" ", tileFailure.getTileType());
        assertEquals(true, success);
        assertEquals(false, failure);
    }

    @Test
    public void testTrapTileDetectedMoveOn() {
        TrapCondition condition = new DetectedTrap();
        TrapTile tile = new TrapTile(0, 0, condition);
        PlayerCharacter player = new PlayerCharacter("John", "Yea");

        tile.moveOn(player);

        assertEquals("P", tile.getTileType());
        assertEquals("V", tile.getTrapCondition().toString());
        assertEquals("Player John: Yea | 95/100 health 10 attack 0 defense", player.toString());
    }

    @Test
    public void testTrapTileDetectedAttemptDisarm() {
        TrapCondition condition = new DetectedTrap();
        TrapTile tileSuccess = new TrapTile(0, 0, condition);
        TrapTile tileFailure = new TrapTile(0, 0, condition);

        int successDamage = tileSuccess.attemptDisarmTester(true);
        int failureDamage = tileFailure.attemptDisarmTester(false);


        assertEquals("V", tileSuccess.getTileType());
        assertEquals("V", tileFailure.getTileType());
        assertEquals(0, successDamage);
        assertEquals(5, failureDamage);
    }

    @Test
    public void testTrapTileDetectedAttemptDetect() {
        TrapCondition condition = new DetectedTrap();
        TrapTile tileSuccess = new TrapTile(0, 0, condition);
        TrapTile tileFailure = new TrapTile(0, 0, condition);

        boolean success = tileSuccess.attemptDetectTester(true);
        boolean failure = tileFailure.attemptDetectTester(false);


        assertEquals("-", tileSuccess.getTileType());
        assertEquals("-", tileFailure.getTileType());
        assertEquals(false, success);
        assertEquals(false, failure);
    }

    @Test
    public void testTrapTileDetectedTileType() {
        TrapCondition condition = new DetectedTrap();
        TrapTile tile = new TrapTile(0, 0, condition);

        assertEquals("-", tile.getTileType());
    }

    @Test
    public void testTrapTileDisarmedMoveOn() {
        TrapCondition condition = new DisarmedTrap();
        TrapTile tile = new TrapTile(0, 0, condition);
        PlayerCharacter player = new PlayerCharacter("John", "Yea");

        tile.moveOn(player);

        assertEquals("P", tile.getTileType());
        assertEquals("V", tile.getTrapCondition().toString());
        assertEquals("Player John: Yea | 100/100 health 10 attack 0 defense", player.toString());
    }

    @Test
    public void testTrapTileDisarmedAttemptDisarm() {
        TrapCondition condition = new DisarmedTrap();
        TrapTile tileSuccess = new TrapTile(0, 0, condition);
        TrapTile tileFailure = new TrapTile(0, 0, condition);

        int successDamage = tileSuccess.attemptDisarmTester(true);
        int failureDamage = tileFailure.attemptDisarmTester(false);


        assertEquals("V", tileSuccess.getTileType());
        assertEquals("V", tileFailure.getTileType());
        assertEquals(0, successDamage);
        assertEquals(0, failureDamage);
    }

    @Test
    public void testTrapTileDisarmedAttemptDetect() {
        TrapCondition condition = new DisarmedTrap();
        TrapTile tileSuccess = new TrapTile(0, 0, condition);
        TrapTile tileFailure = new TrapTile(0, 0, condition);

        boolean success = tileSuccess.attemptDetectTester(true);
        boolean failure = tileFailure.attemptDetectTester(false);


        assertEquals("V", tileSuccess.getTileType());
        assertEquals("V", tileFailure.getTileType());
        assertEquals(false, success);
        assertEquals(false, failure);
    }

    @Test
    public void testTrapTileDisarmedTileType() {
        TrapCondition condition = new DisarmedTrap();
        TrapTile tile = new TrapTile(0, 0, condition);

        assertEquals("V", tile.getTileType());
    }

    @Test
    public void testChestTileGetItem() {
        Item[] items = new Item[6];
        Item dagger = new Weapon("Dagger", 10, 10);
        items[0] = dagger;
        items[1] = new Armor("Iron Armor", 10, 10);
        items[2] = new Buff("Potion of all", 10, 10, 10, 10);
        items[3] = new Food("Steak", 10, 10);
        items[4] = new Bag("Bag", 5, 5);

        ChestTile chest = new ChestTile(0, 0, items);
        Item daggerClone = chest.getItem(1);

        assertEquals(null, chest.getItem(1));
        assertEquals(dagger, daggerClone);
    }

    @Test
    public void testChestTileDepositItemUnfull() {
        Item dagger = new Weapon("Dagger", 10, 10);
        

        ChestTile chest = new ChestTile(0, 0, new Item[5]);
        boolean success = chest.depositItem(dagger);

        assertEquals(null, chest.getItem(2));
        assertEquals(true, success);
    }

    @Test
    public void testChestTileDepositItemFull() {
        Item[] items = new Item[6];
        Item dagger = new Weapon("Dagger", 10, 10);
        items[0] = dagger;
        items[1] = new Armor("Iron Armor", 10, 10);
        items[2] = new Buff("Potion of all", 10, 10, 10, 10);
        items[3] = new Food("Steak", 10, 10);
        items[4] = new Bag("Bag", 5, 5);

        ChestTile chest = new ChestTile(0, 1, items);
        boolean success = chest.depositItem(dagger);

        assertEquals(false, !success);
    }
}
