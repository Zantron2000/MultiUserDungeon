package model.map.room.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import model.character.PlayerCharacter;
import model.map.Coordinates;
import model.map.room.Room;

public class SafeRoomFactory extends RoomFactory {
    private final static int DEFAULT_WIDTH = 7;
    private final static int DEFAULT_HEIGHT = 7;

    private final static ArrayList<Character> PASSABLE = new ArrayList<>(
        Arrays.asList(EMPTY, EXIT, GOAL)
    );

    private final static ArrayList<Character> PERMINATES = new ArrayList<>(
        Arrays.asList(PLAYER, GOAL, EXIT, MERCHANT, SHRINE)
    );

    private final static int CHEST_PROBABILITY = 1;
    private final static int EMPTY_PROBABILITY = 85;
    private final static int OBSTICLE_PROBABILITY = 14;

    private final ArrayList<Character> probability;

    public SafeRoomFactory() {
        this.probability = new ArrayList<>();

        for(int i = 0; i < CHEST_PROBABILITY; i++) {
            this.probability.add(CHEST);
        }
        for(int i = 0; i < EMPTY_PROBABILITY; i++) {
            this.probability.add(EMPTY);
        }
        for(int i = 0; i < OBSTICLE_PROBABILITY; i++) {
            this.probability.add(OBSTICLE);
        }
    }

    public Room generateRandomRoom(int roomX, int roomY, PlayerCharacter player) {
        char[][] layout = new char[DEFAULT_HEIGHT][DEFAULT_WIDTH];
        Random rand = new Random();
        boolean valid = false;

        while(!valid) {
            for(int y = 0; y < DEFAULT_HEIGHT; y++) {
                for(int x = 0; x < DEFAULT_WIDTH; x++) {
                    int index = rand.nextInt(this.probability.size());

                    layout[y][x] = this.probability.get(index);
                }
            }

            this.insertMerchant(layout);
            this.insertPlayer(layout);
            this.insertShrine(layout);
            this.insertGoal(layout);

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

        return this.generatePremadeRoom(roomX, roomY, format, player);
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

    private char[][] insertShrine(char[][] layout) {
        Random rand = new Random();
        int x = rand.nextInt(1, DEFAULT_WIDTH - 1);
        int y = rand.nextInt(1, DEFAULT_HEIGHT - 1);

        if(!PERMINATES.contains(layout[y][x])) {
            layout[y][x] = SHRINE;
            return layout;
        }

        return insertShrine(layout);
    }

    private char[][] insertPlayer(char[][] layout) {
        Random rand = new Random();
        int x = rand.nextInt(1, DEFAULT_WIDTH - 1);
        int y = rand.nextInt(1, DEFAULT_HEIGHT - 1);

        if(!PERMINATES.contains(layout[y][x])) {
            layout[y][x] = PLAYER;
            return layout;
        }

        return insertPlayer(layout);
    }

    private char[][] insertMerchant(char[][] layout) {
        Random rand = new Random();
        int x = rand.nextInt(1, DEFAULT_WIDTH - 1);
        int y = rand.nextInt(1, DEFAULT_HEIGHT - 1);

        if(!PERMINATES.contains(layout[y][x])) {
            layout[y][x] = MERCHANT;
            return layout;
        }

        return insertMerchant(layout);
    }

    private char[][] insertGoal(char[][] layout) {
        Random rand = new Random();
        int x = rand.nextInt(1, DEFAULT_WIDTH - 1);
        int y = rand.nextInt(1, DEFAULT_HEIGHT - 1);

        if(!PERMINATES.contains(layout[y][x])) {
            layout[y][x] = GOAL;
            return layout;
        }

        return insertGoal(layout);
    }
}
