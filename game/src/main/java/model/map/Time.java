package model.map;

public class Time {
    private int turns;
    private Cycle time;
    
    public Time() {
        this.turns = 0;
        this.time = Cycle.DAY;
    }

    public Time(int turns, Cycle time) {
        this.turns = turns;
        this.time = time;
    }
}
