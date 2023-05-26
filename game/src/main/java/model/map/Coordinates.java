package model.map;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hashCode() {
        String hash = "(" + this.x + ", " + this.y + ")";
        return hash.hashCode();
    }
}
