package model.map;

public class Coordinates {
    private int row;
    private int col;

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int hashCode() {
        String hash = "(" + this.row + ", " + this.col + ")";
        return hash.hashCode();
    }
}
