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

    public boolean equals(Object other) {
        if(other instanceof Coordinates) {
            Coordinates otherCoords = (Coordinates) other;

            return otherCoords.row == this.row && otherCoords.col == this.col;
        }

        return false;
    }

    public int hashCode() {
        String hash = "(" + this.row + ", " + this.col + ")";
        return hash.hashCode();
    }
}
