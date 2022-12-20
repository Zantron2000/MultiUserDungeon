package model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.PlayerCharacter;
import model.map.factories.EndlessMapFactory;
import model.map.factories.PreloadMapFactory;
import model.map.room.Room;

@Testable
public class TestMapFactories {
    @Test
    public void testPreloadMapFactory() {
        PreloadMapFactory factory = new PreloadMapFactory("data/maps/map1.csv");
        PlayerCharacter pc = new PlayerCharacter("null", "null");
        String expectedLayout1 = "| | | |\n| |P| |\n| | | |\n| |E| |\n";
        String expectedLayout2 = "| |E| |\n| | | |\n| |G| |\n";

        Map map = factory.generate(pc);
        Room room1 = map.getCurrentRoom();

        map.moveRooms(Direction.SOUTH);
        Room room2 = map.getCurrentRoom();

        map.moveRooms(Direction.SOUTH);
        Room room3 = map.getCurrentRoom();

        assertEquals(expectedLayout1, room1.toString());
        assertEquals(expectedLayout2, room2.toString());
        assertEquals(room2, room3);
    }

    @Test
    public void testEndlessMapFactory() {
        EndlessMapFactory factory = new EndlessMapFactory();
        PlayerCharacter pc = new PlayerCharacter("null", "null");

        Map map = factory.generate(pc);
        Room room1 = map.getCurrentRoom();

        map.moveRooms(Direction.SOUTH);
        Room room2 = map.getCurrentRoom();

        map.moveRooms(Direction.SOUTH);
        Room room3 = map.getCurrentRoom();

        map.moveRooms(Direction.SOUTH);
        Room room4 = map.getCurrentRoom();

        assertTrue(room1.isSafe());
        assertNotEquals(room1, room2);
        assertNotEquals(room2, room3);
        assertNotEquals(room3, room4);
    }
}
