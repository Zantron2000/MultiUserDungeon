package controller.turnMapper;

import model.map.Coordinates;

public enum Direction {
    NORTH(new int[]{-1, 0}, "north"),
    NORTH_WEST(new int[]{-1, -1}, "north west"),
    WEST(new int[]{0, -1}, "west"),
    SOUTH_WEST(new int[]{1, -1}, "south west"),
    SOUTH(new int[]{1, 0}, "south"),
    SOUTH_EAST(new int[]{1, 1}, "south east"),
    EAST(new int[]{0, 1}, "east"),
    NORTH_EAST(new int[]{-1, 1}, "north east"),
    CURRENT(new int[]{0, 0}, "current");

    private final int[] coords;
    private final String description;

    private Direction(int[] coords, String description) {
        this.coords = coords;
        this.description = description;
    }

    public int[] getCoords() {
        return this.coords;
    }

    public String toString() {
        return this.description;
    }

    public static Coordinates applyDirection(Coordinates coords, Direction direction) {
        int row = coords.getRow();
        int col = coords.getCol();
        int[] dirCoords = direction.getCoords();

        row = row + dirCoords[0];
        col = col + dirCoords[1];

        return new Coordinates(row, col);
    }
}
