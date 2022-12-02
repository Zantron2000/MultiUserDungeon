package model.map.generators;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import model.inventory.Item;
import model.inventory.concrete_items.Armor;
import model.inventory.concrete_items.Bag;
import model.inventory.concrete_items.Buff;
import model.inventory.concrete_items.Food;
import model.inventory.concrete_items.Weapon;

public class ItemGenerator {
    private static final String ITEM_FILE = "data/items.csv";
    private static final String BAG_FILE = "data/bags.csv";
    private static ItemGenerator INSTANCE;

    private HashMap<Rarity, ArrayList<Item>> items;
    private ArrayList<Rarity> probability;

    private ItemGenerator() {
        try {
            items = new HashMap<>();
            for(Rarity rarity : Rarity.values()) {
                items.put(rarity, new ArrayList<>());
            }

            Scanner scanner = new Scanner(new File(ITEM_FILE));
            scanner.nextLine();
            while(scanner.hasNext()) {
                String[] data = scanner.nextLine().split(",");
                String type = data[0].toLowerCase();
                String rarity = data[3].toLowerCase();
                String name = data[1];
                int value = Integer.parseInt(data[2]);
                int attack = Integer.parseInt(data[4]);
                int defense = Integer.parseInt(data[5]);
                int health = Integer.parseInt(data[6]);
                Item item;

                if(type.equals("armor")) {
                    item = new Armor(name, value, defense);
                } else if(type.equals("weapon")) {
                    item = new Weapon(name, value, attack);
                } else if(type.equals("buff")) {
                    item = new Food(name, value, health);
                } else if(type.equals("food")) {
                    item = new Buff(name, value, attack, defense, health);
                } else {
                    continue;
                }
                
                items.get(Rarity.levels.get(rarity)).add(item);
            }
            scanner.close();

            scanner = new Scanner(new File(BAG_FILE));
            scanner.nextLine();
            while(scanner.hasNext()) {
                String[] data = scanner.nextLine().split(",");
                String name = data[0];
                String rarity = data[2].toLowerCase();
                int value = Integer.parseInt(data[1]);
                int size = Integer.parseInt(data[3]);
                Item item = new Bag(name, value, size);
                
                items.get(Rarity.levels.get(rarity)).add(item);
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

    public static synchronized ItemGenerator instance() {
        if(INSTANCE == null) {
            INSTANCE = new ItemGenerator();
        }

        return INSTANCE;
    }

    public Item generateItem() {
        Random rand = new Random();
        int rarityIndex = rand.nextInt(this.probability.size());
        Rarity rarity = this.probability.get(rarityIndex);
        int itemIndex = rand.nextInt(this.items.get(rarity).size());

        return this.items.get(rarity).get(itemIndex);
    }
} 