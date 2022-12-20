package model.map.room.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import model.map.room.Room;
import model.map.Coordinates;

public class RandomRoomFactory extends RoomFactory {
    private final static int DEFAULT_WIDTH = 7;
    private final static int DEFAULT_HEIGHT = 7;

    private final static ArrayList<Character> PASSABLE = new ArrayList<>(
        Arrays.asList(EMPTY, ENEMY, TRAP, EXIT)
    );

    private final static int CHEST_PROBABILITY = 5;
    private final static int EMPTY_PROBABILITY = 55;
    private final static int ENEMY_PROBABILITY = 10;
    private final static int MERCHANT_PROBABILITY = 5;
    private final static int OBSTICLE_PROBABILITY = 20;
    private final static int TRAP_PROBABILITY = 5;
    private final static double SHRINE_PROBABILITY = 0.1;

    private final ArrayList<Character> probability;

    public RandomRoomFactory() {
        this.probability = new ArrayList<>();

        for(int i = 0; i < CHEST_PROBABILITY; i++) {
            this.probability.add(CHEST);
        }
        for(int i = 0; i < EMPTY_PROBABILITY; i++) {
            this.probability.add(EMPTY);
        }
        for(int i = 0; i < ENEMY_PROBABILITY; i++) {
            this.probability.add(ENEMY);
        }
        for(int i = 0; i < MERCHANT_PROBABILITY; i++) {
            this.probability.add(MERCHANT);
        }
        for(int i = 0; i < OBSTICLE_PROBABILITY; i++) {
            this.probability.add(OBSTICLE);
        }
        for(int i = 0; i < TRAP_PROBABILITY; i++) {
            this.probability.add(TRAP);
        }
    }

    public Room generateRandomRoom(int roomX, int roomY) {
        char[][] layout = new char[DEFAULT_HEIGHT][DEFAULT_WIDTH];
        Random rand = new Random();
        boolean valid = false;
        boolean isShrine = Math.random() < SHRINE_PROBABILITY;

        while(!valid) {
            for(int y = 0; y < DEFAULT_HEIGHT; y++) {
                for(int x = 0; x < DEFAULT_WIDTH; x++) {
                    int index = rand.nextInt(this.probability.size());

                    layout[y][x] = this.probability.get(index);
                }
            }

            if(isShrine) {
                int x = rand.nextInt(1, DEFAULT_WIDTH - 1);
                int y = rand.nextInt(1, DEFAULT_HEIGHT - 1);
                layout[y][x] = SHRINE;
            }

            layout[0][DEFAULT_WIDTH / 2] = EXIT;
            layout[DEFAULT_HEIGHT - 1][DEFAULT_WIDTH / 2] = EXIT;
            layout[DEFAULT_HEIGHT / 2][0] = EXIT;
            layout[DEFAULT_HEIGHT / 2][DEFAULT_WIDTH - 1] = EXIT;

            valid = this.isValid(layout);
        }
        String format = "";
        for(int y = 0; y < DEFAULT_HEIGHT; y++) {
            for(int x = 0; x < DEFAULT_WIDTH; x++) {
                format += layout[y][x];
            }
            format += "|";
        }
        
        return this.generatePremadeRoom(roomX, roomY, format, null);
    }

    public Room generateRandomRoom(Coordinates coords) {
        char[][] layout = new char[DEFAULT_HEIGHT][DEFAULT_WIDTH];
        Random rand = new Random();
        boolean valid = false;
        boolean isShrine = Math.random() < SHRINE_PROBABILITY;

        while(!valid) {
            for(int y = 0; y < DEFAULT_HEIGHT; y++) {
                for(int x = 0; x < DEFAULT_WIDTH; x++) {
                    int index = rand.nextInt(this.probability.size());

                    layout[y][x] = this.probability.get(index);
                }
            }

            if(isShrine) {
                int x = rand.nextInt(1, DEFAULT_WIDTH - 1);
                int y = rand.nextInt(1, DEFAULT_HEIGHT - 1);
                layout[y][x] = SHRINE;
            }

            layout[0][DEFAULT_WIDTH / 2] = EXIT;
            layout[DEFAULT_HEIGHT - 1][DEFAULT_WIDTH / 2] = EXIT;
            layout[DEFAULT_HEIGHT / 2][0] = EXIT;
            layout[DEFAULT_HEIGHT / 2][DEFAULT_WIDTH - 1] = EXIT;

            valid = this.isValid(layout);
        }
        String format = "";
        for(int y = 0; y < DEFAULT_HEIGHT; y++) {
            for(int x = 0; x < DEFAULT_WIDTH; x++) {
                format += layout[y][x];
            }
            format += "|";
        }
        
        return this.generatePremadeRoom(coords, format, null);
    }

    private boolean isValid(char[][] layout) {
        HashMap<Coordinates, Character> passage = new HashMap<>();
        passage = this.pathFinder(passage, layout, DEFAULT_WIDTH / 2, 0);
        if(passage.size() == DEFAULT_HEIGHT * DEFAULT_WIDTH) {
            return true;
        } else {
            return false;
        }
    }

    private HashMap<Coordinates, Character> pathFinder(HashMap<Coordinates, Character> passage, 
                                                        char[][] layout,
                                                        int x, int y) {
        if(x < 0 || y < 0 || x >= DEFAULT_WIDTH || y >= DEFAULT_HEIGHT) {
            return passage;
        } else if(passage.get(new Coordinates(x, y)) != null) {
            return passage;
        } else if(!PASSABLE.contains(layout[y][x])) {
            passage.put(new Coordinates(x, y), layout[y][x]);
            return passage;
        } 

        passage.put(new Coordinates(x, y), layout[y][x]);
        passage = this.pathFinder(passage, layout, x - 1, y);
        passage = this.pathFinder(passage, layout, x, y - 1);
        passage = this.pathFinder(passage, layout, x + 1, y);
        passage = this.pathFinder(passage, layout, x, y + 1);

        return passage;
    }
}
