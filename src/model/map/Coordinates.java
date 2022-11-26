package model.map;

import java.util.HashMap;

public class Coordinates {
    private static final HashMap<Direction, Integer[]> SHIFTS = new HashMap<Direction, Integer[]>() {{
        put(Direction.EAST, new Integer[]{1, 0});
        put(Direction.WEST, new Integer[]{-1, 0});
        put(Direction.NORTH, new Integer[]{0, 1});
        put(Direction.SOUTH, new Integer[]{0, -1});
        put(Direction.NORTHEAST, new Integer[]{1, 1});
        put(Direction.SOUTHEAST, new Integer[]{1, -1});
        put(Direction.SOUTHWEST, new Integer[]{-1, -1});
        put(Direction.NORTHWEST, new Integer[]{-1, 1});
    }};

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates nextCoordinates(Direction direction) {
        Integer[] movement = Coordinates.SHIFTS.get(direction);

        int x = this.x + movement[0];
        int y = this.y + movement[1];

        return new Coordinates(x, y);
    }

    @Override
    public int hashCode() {
        return (x + "," + y).hashCode();
    }
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof Coordinates coord)
            return this.x == coord.x && this.y == coord.y;
        return false;
    }
}
