package model.map.room.generators;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import model.character.DiurnalNPC;
import model.character.NocturnalNPC;
import model.character.NonPlayerCharacter;

public class EnemyGenerator {
    private static final String DATA_FILE = "data/enemies.csv";
    private static EnemyGenerator INSTANCE;
    private final static double ITEM_CHANCE = 0.5;

    private HashMap<Rarity, ArrayList<NonPlayerCharacter>> enemies;
    private final ArrayList<Rarity> probability;
    private ItemGenerator itemGenerator;

    private EnemyGenerator() {
        try {
            itemGenerator = ItemGenerator.instance();
            enemies = new HashMap<>();
            for(Rarity rarity : Rarity.values()) {
                enemies.put(rarity, new ArrayList<>());
            }

            File file = new File(DATA_FILE);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNext()) {
                String[] data = scanner.nextLine().split(",");
                String type = data[0].toLowerCase();
                String rarity = data[1].toLowerCase();
                int attack = Integer.parseInt(data[4]);
                int defense = Integer.parseInt(data[5]);
                int health = Integer.parseInt(data[6]);
                NonPlayerCharacter enemy;

                if(type.equals("nocturnal")) {
                    enemy = new NocturnalNPC(data[2], data[3], attack, defense, health);
                } else if(type.equals("diurnal")) {
                    enemy = new DiurnalNPC(data[2], data[3], attack, defense, health);
                } else {
                    continue;
                }

                enemies.get(Rarity.levels.get(rarity)).add(enemy);
            }

            scanner.close();
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        probability = new ArrayList<>();
        for(Rarity rarity : Rarity.values()) {
            for(int i = 0; i < Rarity.chance.get(rarity); i++) {
                probability.add(rarity);
            }
        }
    }

    public static synchronized EnemyGenerator instance() {
        if(INSTANCE == null) {
            INSTANCE = new EnemyGenerator();
        }

        return INSTANCE;
    }

    public NonPlayerCharacter generateEnemy() {
        Random rand = new Random();
        int rarityIndex = rand.nextInt(this.probability.size());
        Rarity rarity = this.probability.get(rarityIndex);
        int enemyIndex = rand.nextInt(this.enemies.get(rarity).size());
        
        NonPlayerCharacter npc = this.enemies.get(rarity).get(enemyIndex);
        
        if(Math.random() < ITEM_CHANCE) {
            npc.equipItem(itemGenerator.generateItem());
        }

        return npc;
    }
} 
