package controller.gameGenerators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.character.characters.DiurnalNPC;
import model.character.characters.NocturnalNPC;
import model.character.characters.NonPlayerCharacter;
import model.character.inventory.Inventory;
import model.character.inventory.inventories.SingleInventory;
import model.character.stats.Stat;
import model.character.stats.StatsManager;
import model.character.stats.stat.BaseStat;
import model.character.stats.statsManager.SimpleStatsManager;

public class EnemyGenerator {
    private static String filePath;
    private static List<String> monstersCommon;
    private static List<String> monstersUncommon;
    private static List<String> monstersRare;
    private static List<String> monstersEpic;
    private static List<String> monstersLegendary;

    public static void loadEnemies(String filePath) {
        EnemyGenerator.filePath = filePath;
        EnemyGenerator.monstersCommon = new ArrayList<>();
        EnemyGenerator.monstersUncommon = new ArrayList<>();
        EnemyGenerator.monstersRare = new ArrayList<>();
        EnemyGenerator.monstersEpic = new ArrayList<>();
        EnemyGenerator.monstersLegendary = new ArrayList<>();

        EnemyGenerator.loadEnemiesHelper();
    }

    private static void loadEnemiesHelper() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] enemyData = line.split(",");
                String rarity = enemyData[1];

                switch (rarity) {
                    case "common":
                        monstersCommon.add(line);
                        break;
                    case "uncommon":
                        monstersUncommon.add(line);
                        break;
                    case "rare":
                        monstersRare.add(line);
                        break;
                    case "epic":
                        monstersEpic.add(line);
                        break;
                    case "legendary":
                        monstersLegendary.add(line);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NonPlayerCharacter generateNPC() {
        int randomNumber = EnemyGenerator.generateRandomInt(100);
        List<String> npcs;

        if (randomNumber < 40) {
            npcs = EnemyGenerator.monstersCommon;
        } else if (randomNumber < 70) {
            npcs = EnemyGenerator.monstersUncommon;
        } else if (randomNumber < 85) {
            npcs = EnemyGenerator.monstersRare;
        } else if (randomNumber < 95) {
            npcs = EnemyGenerator.monstersEpic;
        } else {
            npcs = EnemyGenerator.monstersLegendary;
        }

        int randomIdx = EnemyGenerator.generateRandomInt(npcs.size());

        NonPlayerCharacter npc = EnemyGenerator.createEnemy(npcs, randomIdx);

        return npc;
    }

    private static NonPlayerCharacter createEnemy(List<String> enemies, int idx) {
        String enemy = enemies.get(idx);

        String[] enemyData = enemy.split(",");
        String type = enemyData[0];
        String name = enemyData[2];
        String description = enemyData[3];
        int health = Integer.parseInt(enemyData[4]);
        int attack = Integer.parseInt(enemyData[5]);
        int defense = Integer.parseInt(enemyData[6]);

        Stat baseStat = new BaseStat(attack, defense);
        StatsManager manager = new SimpleStatsManager(health, baseStat);

        int gold = EnemyGenerator.generateRandomInt(26);
        Inventory inventory = new SingleInventory();
        inventory.addGold(gold);
        inventory.addItem(ItemGenerator.generateMonsterItem());

        NonPlayerCharacter npc;
        if (type.equals("diurnal")) {
            npc = new DiurnalNPC(inventory, manager, null, name, description);
        } else {
            npc = new NocturnalNPC(inventory, manager, null, name, description);
        }

        return npc;
    }

    private static int generateRandomInt(int range) {
        Random random = new Random();

        return random.nextInt(range);
    }
}
