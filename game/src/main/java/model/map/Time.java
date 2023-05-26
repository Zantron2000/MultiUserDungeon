package model.map;

import controller.gameController.TurnObserver;

public class Time implements TurnObserver {
    private int turns;
    private Cycle time;
    private TimeObserver observer;
    
    public Time() {
        this.turns = 0;
        this.time = Cycle.DAY;
    }

    public Time(int turns, Cycle time) {
        this.turns = turns;
        this.time = time;
    }

    public void processTurn() {
        this.turns++;

        if(turns % 12 == 0) {
            switchTime();
            this.observer.changeTime(this.time);
        }
    }

    public void updateObserver(TimeObserver observer) {
        this.observer = observer;
    }

    private void switchTime() {
        this.time = (this.time == Cycle.DAY) ? Cycle.NIGHT : Cycle.DAY;
    }
}
