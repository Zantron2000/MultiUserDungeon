package model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestDirections {
    
    @Test
    public void testDirectionsOpposite() {
        Direction west = Direction.WEST;
        Direction east = Direction.EAST;
        Direction north = Direction.NORTH;
        Direction south = Direction.SOUTH;

        assertEquals(east, Direction.opposite.get(west));
        assertEquals(west, Direction.opposite.get(east));
        assertEquals(north, Direction.opposite.get(south));
        assertEquals(south, Direction.opposite.get(north));
    }

    @Test
    public void testCoordinates() {
        Coordinates start = new Coordinates(0, 0);

        Coordinates north = start.nextCoordinates(Direction.NORTH);
        Coordinates northEast = start.nextCoordinates(Direction.NORTHEAST);
        Coordinates east = start.nextCoordinates(Direction.EAST);
        Coordinates southEast = start.nextCoordinates(Direction.SOUTHEAST);
        Coordinates south = start.nextCoordinates(Direction.SOUTH);
        Coordinates southWest = start.nextCoordinates(Direction.SOUTHWEST);
        Coordinates west = start.nextCoordinates(Direction.WEST);
        Coordinates northWest = start.nextCoordinates(Direction.NORTHWEST);

        assertEquals("0,1".hashCode(), north.hashCode());
        assertEquals("1,1".hashCode(), northEast.hashCode());
        assertEquals("1,0".hashCode(), east.hashCode());
        assertEquals("1,-1".hashCode(), southEast.hashCode());
        assertEquals("0,-1".hashCode(), south.hashCode());
        assertEquals("-1,-1".hashCode(), southWest.hashCode());
        assertEquals("-1,0".hashCode(), west.hashCode());
        assertEquals("-1,1".hashCode(), northWest.hashCode());
    }
}
