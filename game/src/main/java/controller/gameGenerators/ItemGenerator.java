package controller.gameGenerators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.character.inventory.Item;
import model.character.inventory.items.Armor;
import model.character.inventory.items.Bag;
import model.character.inventory.items.Buff;
import model.character.inventory.items.Food;
import model.character.inventory.items.Weapon;

public class ItemGenerator {
    private static String armorFilePath;
    private static String bagsFilePath;
    private static String buffsFilePath;
    private static String weaponsFilePath;
    private static String foodFilePath;

    private static HashMap<String, ArrayList<Item>> itemsByRarity;

    public static void loadItems(String armorFilePath, String bagsFilePath, String buffsFilePath, String weaponsFilePath, String foodFilePath) {
        ItemGenerator.armorFilePath = armorFilePath;
        ItemGenerator.bagsFilePath = bagsFilePath;
        ItemGenerator.buffsFilePath = buffsFilePath;
        ItemGenerator.weaponsFilePath = weaponsFilePath;
        ItemGenerator.foodFilePath = foodFilePath;

        itemsByRarity = new HashMap<String, ArrayList<Item>>();
        itemsByRarity.put("common", new ArrayList<Item>());
        itemsByRarity.put("uncommon", new ArrayList<Item>());
        itemsByRarity.put("rare", new ArrayList<Item>());
        itemsByRarity.put("epic", new ArrayList<Item>());
        itemsByRarity.put("legendary", new ArrayList<Item>());

        ItemGenerator.loadItemsHelper();
    }

    private static void loadItemsFromFile(String filePath, String itemType) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] itemData = line.split(",");
                if (itemData.length >= 5) {
                    String rarity = itemData[0].trim().toLowerCase();
                    String name = itemData[1].trim();
                    String description = itemData[2].trim();
                    int value = Integer.parseInt(itemData[3].trim());
    
                    Item item = null;
                    switch (itemType) {
                        case "armor":
                            if (itemData.length >= 5) {
                                int defense = Integer.parseInt(itemData[4].trim());
                                item = new Armor(name, description, value, defense);
                            }
                            break;
                        case "bags":
                            if (itemData.length >= 5) {
                                int size = Integer.parseInt(itemData[4].trim());
                                item = new Bag(name, description, value, size);
                            }
                            break;
                        case "buffs":
                            if (itemData.length >= 6) {
                                int attack = Integer.parseInt(itemData[4].trim());
                                int defense = Integer.parseInt(itemData[5].trim());
                                int turns = Integer.parseInt(itemData[6].trim());

                                item = new Buff(name, description, value, attack, defense, turns);
                            }
                            break;
                        case "weapons":
                            if (itemData.length >= 5) {
                                int attack = Integer.parseInt(itemData[4].trim());
                                item = new Weapon(name, description, value, attack);
                            }
                            break;
                        case "food":
                            if (itemData.length >= 5) {
                                int health = Integer.parseInt(itemData[4].trim());
                                item = new Food(name, description, value, health);
                            }
                            break;
                        default:
                            System.out.println("Invalid item type: " + itemType);
                            break;
                    }
    
                    if (item != null) {
                        ItemGenerator.itemsByRarity.get(rarity).add(item);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void loadItemsHelper() {
        loadItemsFromFile(armorFilePath, "armor");
        loadItemsFromFile(bagsFilePath, "bags");
        loadItemsFromFile(buffsFilePath, "buffs");
        loadItemsFromFile(weaponsFilePath, "weapons");
        loadItemsFromFile(foodFilePath, "food");
    }

    public static Item generateItem() {
        int randomNumber = ItemGenerator.generateRandomInt(100);
        String rarity;

        if (randomNumber < 40) {
            rarity = "common";
        } else if (randomNumber < 70) {
            rarity = "uncommon";
        } else if (randomNumber < 85) {
            rarity = "rare";
        } else if (randomNumber < 95) {
            rarity = "epic";
        } else {
            rarity = "legendary";
        }

        ArrayList<Item> items = ItemGenerator.itemsByRarity.get(rarity);
        int randomIdx = ItemGenerator.generateRandomInt(items.size());

        return ItemGenerator.cloneItem(items, randomIdx);
    }

    public static Item generateMonsterItem() {
        int hasItem = generateRandomInt(2);

        if(hasItem == 0) {
            return null;
        } else {
            return ItemGenerator.generateItem();
        }
    }

    public static Item[] generateChestItems() {
        Item[] items = new Item[5];
        items[0] = ItemGenerator.generateItem();

        for(int i = 1; i < items.length; i++) {
            items[i] = generateMonsterItem();
        }

        return items;
    }

    private static int generateRandomInt(int range) {
        Random random = new Random();

        return random.nextInt(range);
    }

    private static Item cloneItem(ArrayList<Item> items, int idx) {
        Item item = items.get(idx);

        return item.clone();
    }
}
