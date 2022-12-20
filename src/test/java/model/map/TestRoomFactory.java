package model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.PlayerCharacter;
import model.map.room.Room;
import model.map.room.Tile;
import model.map.room.concrete_tiles.EmptyTile;
import model.map.room.concrete_tiles.ExitTile;
import model.map.room.factories.RandomRoomFactory;
import model.map.room.factories.RoomFactory;
import model.map.room.factories.SafeRoomFactory;

@Testable
public class TestRoomFactory {
    
    @Test
    public void testPremadeRoomNoExits() {
        RoomFactory factory = new RoomFactory();
        int roomX = 0;
        int roomY = 0;
        String layout = "BOB|BOB|BBB|BCB";
        String expectedLayout = "| |C| |\n| | | |\n| |O| |\n| |O| |\n";
        
        Room room = factory.generatePremadeRoom(roomX, roomY, layout, new PlayerCharacter("John", "John"));

        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 4; y++) {
                assertNotNull(room.getTile(new Coordinates(x, y)));
            }
        }
        assertEquals(expectedLayout, room.toString());
        assertEquals(new Coordinates(0, 1), room.getNeighbor(Direction.NORTH));
        assertEquals(new Coordinates(0, -1), room.getNeighbor(Direction.SOUTH));
        assertEquals(new Coordinates(-1, 0), room.getNeighbor(Direction.WEST));
        assertEquals(new Coordinates(1, 0), room.getNeighbor(Direction.EAST));

        assertNull(room.getExit(Direction.NORTH));
        assertEquals(" ", room.getTile(new Coordinates(0, 0)).getTileType());
        assertEquals("C", room.getTile(new Coordinates(1, 3)).getTileType());
    }

    @Test
    public void testPremadeRoomExits() {
        RoomFactory factory = new RoomFactory();
        int roomX = 0;
        int roomY = 0;
        String layout = "BBEB|EBBE|BEBB";
        String expectedLayout = "| |E| | |\n|E| | |E|\n| | |E| |\n";
        
        Room room = factory.generatePremadeRoom(roomX, roomY, layout, new PlayerCharacter("John", "John"));

        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 3; y++) {
                assertNotNull(room.getTile(new Coordinates(x, y)));
            }
        }
        assertEquals(expectedLayout, room.toString());
        assertEquals(new Coordinates(0, 1), room.getNeighbor(Direction.NORTH));
        assertEquals(new Coordinates(0, -1), room.getNeighbor(Direction.SOUTH));
        assertEquals(new Coordinates(-1, 0), room.getNeighbor(Direction.WEST));
        assertEquals(new Coordinates(1, 0), room.getNeighbor(Direction.EAST));

        assertNotNull(room.getExit(Direction.NORTH));
        assertNotNull(room.getExit(Direction.WEST));
        assertNotNull(room.getExit(Direction.EAST));
        assertNotNull(room.getExit(Direction.SOUTH));

        assertTrue(room.getExit(Direction.NORTH) instanceof ExitTile);
        assertTrue(room.getExit(Direction.WEST) instanceof ExitTile);
        assertTrue(room.getExit(Direction.EAST) instanceof ExitTile);
        assertTrue(room.getExit(Direction.SOUTH) instanceof ExitTile);
        
        assertEquals(" ", room.getTile(new Coordinates(0, 0)).getTileType());
        assertEquals("E", room.getTile(new Coordinates(1, 2)).getTileType());
    }

    @Test
    public void testPremadeRoomSquare() {
        RoomFactory factory = new RoomFactory();
        int roomX = 0;
        int roomY = 0;
        String layout = "BEB|EBE|BEB";
        String expectedLayout = "| |E| |\n|E| |E|\n| |E| |\n";
        
        Room room = factory.generatePremadeRoom(roomX, roomY, layout, new PlayerCharacter("John", "John"));

        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                assertNotNull(room.getTile(new Coordinates(x, y)));
            }
        }
        assertEquals(expectedLayout, room.toString());
        assertEquals(new Coordinates(0, 1), room.getNeighbor(Direction.NORTH));
        assertEquals(new Coordinates(0, -1), room.getNeighbor(Direction.SOUTH));
        assertEquals(new Coordinates(-1, 0), room.getNeighbor(Direction.WEST));
        assertEquals(new Coordinates(1, 0), room.getNeighbor(Direction.EAST));

        assertNotNull(room.getExit(Direction.NORTH));
        assertNotNull(room.getExit(Direction.WEST));
        assertNotNull(room.getExit(Direction.EAST));
        assertNotNull(room.getExit(Direction.SOUTH));

        assertTrue(room.getExit(Direction.NORTH) instanceof ExitTile);
        assertTrue(room.getExit(Direction.WEST) instanceof ExitTile);
        assertTrue(room.getExit(Direction.EAST) instanceof ExitTile);
        assertTrue(room.getExit(Direction.SOUTH) instanceof ExitTile);
        
        assertEquals(" ", room.getTile(new Coordinates(1, 1)).getTileType());
        assertEquals("E", room.getTile(new Coordinates(1, 2)).getTileType());
    }

    @Test
    public void testRandomRoomFactory() {
        RandomRoomFactory factory = new RandomRoomFactory();
        Room room = factory.generateRandomRoom(0, 0);

        for(int x = 0; x < 7; x++) {
            for(int y = 0; y < 7; y++) {
                assertNotNull(room.getTile(new Coordinates(x, y)));
            }
        }

        assertEquals(new Coordinates(0, 1), room.getNeighbor(Direction.NORTH));
        assertEquals(new Coordinates(0, -1), room.getNeighbor(Direction.SOUTH));
        assertEquals(new Coordinates(-1, 0), room.getNeighbor(Direction.WEST));
        assertEquals(new Coordinates(1, 0), room.getNeighbor(Direction.EAST));

        assertNotNull(room.getExit(Direction.NORTH));
        assertNotNull(room.getExit(Direction.WEST));
        assertNotNull(room.getExit(Direction.EAST));
        assertNotNull(room.getExit(Direction.SOUTH));

        assertTrue(room.getExit(Direction.NORTH) instanceof ExitTile);
        assertTrue(room.getExit(Direction.WEST) instanceof ExitTile);
        assertTrue(room.getExit(Direction.EAST) instanceof ExitTile);
        assertTrue(room.getExit(Direction.SOUTH) instanceof ExitTile);
    }

    @Test
    public void testSafeRoomFactory() {
        SafeRoomFactory factory = new SafeRoomFactory();
        Room room = factory.generateRandomRoom(0, 0, new PlayerCharacter("null", "null"));

        for(int x = 0; x < 7; x++) {
            for(int y = 0; y < 7; y++) {
                assertNotNull(room.getTile(new Coordinates(x, y)));
            }
        }

        assertEquals(new Coordinates(0, 1), room.getNeighbor(Direction.NORTH));
        assertEquals(new Coordinates(0, -1), room.getNeighbor(Direction.SOUTH));
        assertEquals(new Coordinates(-1, 0), room.getNeighbor(Direction.WEST));
        assertEquals(new Coordinates(1, 0), room.getNeighbor(Direction.EAST));

        assertNotNull(room.getExit(Direction.NORTH));
        assertNotNull(room.getExit(Direction.WEST));
        assertNotNull(room.getExit(Direction.EAST));
        assertNotNull(room.getExit(Direction.SOUTH));

        assertTrue(room.getExit(Direction.NORTH) instanceof ExitTile);
        assertTrue(room.getExit(Direction.WEST) instanceof ExitTile);
        assertTrue(room.getExit(Direction.EAST) instanceof ExitTile);
        assertTrue(room.getExit(Direction.SOUTH) instanceof ExitTile);
    }

    @Test
    public void testSafeRooms() {
        SafeRoomFactory safe = new SafeRoomFactory();
        RoomFactory dangerous = new RoomFactory();
        PlayerCharacter pc = new PlayerCharacter("null", "null");
        Room safeRoom = safe.generateRandomRoom(0, 0, pc);
        Room dangerRoom = dangerous.generatePremadeRoom(0, 0, "NNN|NPN|NNN", pc);

        assertTrue(safeRoom.isSafe());
        assertFalse(dangerRoom.isSafe());
    }

    @Test
    public void testRoomReplaceTile() {
        String expectedLayout = "| |N| |\n| |P| |\n| |N| |\n";
        String expectedLayout1 = "| |N| |\n| |P| |\n| | | |\n";
        String expectedLayout2 = "| | | |\n| |P| |\n| | | |\n";
        RoomFactory factory = new RoomFactory();
        PlayerCharacter pc = new PlayerCharacter("null", "null");
        Room room = factory.generatePremadeRoom(0, 0, "BNB|BPB|BNB", pc);
        Tile tile = new EmptyTile(0, 0);

        assertEquals(expectedLayout, room.toString());
        assertFalse(room.isSafe());
        room.changeTile(new Coordinates(1, 0), tile);
        assertEquals(expectedLayout1, room.toString());
        assertFalse(room.isSafe());
        room.changeTile(new Coordinates(1, 2), tile);
        assertEquals(expectedLayout2, room.toString());
        assertTrue(room.isSafe());
    }

    @Test
    public void testResetRoom() {
        String expectedLayout = "| |N|G|\n| |P| |\n| |N| |\n";
        String expectedLayout1 = "| |N|G|\n| |P| |\n| | | |\n";
        String expectedLayout2 = "| | |G|\n| |P| |\n| | | |\n";
        RoomFactory factory = new RoomFactory();
        PlayerCharacter pc = new PlayerCharacter("null", "null");
        Room room = factory.generatePremadeRoom(0, 0, "BNB|BPB|BNG", pc);
        Tile tile = new EmptyTile(0, 0);

        assertEquals(expectedLayout, room.toString());
        assertFalse(room.isSafe());
        room.changeTile(new Coordinates(1, 0), tile);
        assertEquals(expectedLayout1, room.toString());
        assertFalse(room.isSafe());
        room.changeTile(new Coordinates(1, 2), tile);
        assertEquals(expectedLayout2, room.toString());
        assertTrue(room.isSafe());

        room = factory.generatePremadeRoom(0, 0, room.getFloorPlan(), pc);
        assertEquals(expectedLayout, room.toString());
        assertFalse(room.isSafe());
    }
}
