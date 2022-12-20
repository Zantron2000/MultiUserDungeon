package model.map;

import java.util.HashMap;

/**
 * Represents the possible directions a room/tile could exist relative to a player's current room/tile
 */
public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTHEAST,
    SOUTHEAST,
    NORTHWEST,
    SOUTHWEST;

    public static final HashMap<Direction, Direction> opposite = new HashMap<>() {{
        put(NORTH, SOUTH);
        put(SOUTH, NORTH);
        put(EAST, WEST);
        put(WEST, EAST);
    }};
}
