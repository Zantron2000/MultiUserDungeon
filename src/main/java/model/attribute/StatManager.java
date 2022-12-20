package model.attribute;

import java.util.ArrayList;

import model.inventory.Item;
import model.inventory.concrete_items.Armor;
import model.inventory.concrete_items.Bag;
import model.inventory.concrete_items.Buff;
import model.inventory.concrete_items.Food;
import model.inventory.concrete_items.Weapon;

public class StatManager {
    private Stat weaponBuff; // The weapon buff currently equipped
    private Stat armorBuff; // The stat buff currently equipped
    private final ArrayList<Stat> flatBuffs; // List of all flat buffs
    private final ArrayList<Stat> scalarBuffs; // List of all scalar buffs
    private final int maxHealth; // The max health
    private int health; // The current health

    /**
     * The StatManager constructor, generates the base stats for the
     * manager 
     * 
     * @param attack the base attack stat value
     * @param defense the base defense stat value
     * @param health the base health
     */
    public StatManager(int attack, int defense, int health) {
        this.weaponBuff = null;
        this.armorBuff = null;
        this.flatBuffs = new ArrayList<>();
        this.scalarBuffs = new ArrayList<>();
        
        this.flatBuffs.add(new Stat(attack, defense, -1));
        this.maxHealth = health;
        this.health = health;
    }

    /**
     * Obtains the total attack by calculating all the 
     * attack buffs from used buffs and equipment
     * 
     * @return the total attack stat
     */
    public int attack() {
        int attack;
        int multiplier;
        
        attack = getFlatAttack();
        multiplier = getScalarAttack();

        attack += this.getEquipmentAttack();
        return multiplier * attack;
    }

    /**
     * Calculates damage to take and health remaining after
     * being attacked for a certain amount of damage
     * 
     * @param attack the amount of damage to take
     * @return boolean depending on if health has dropped below 0
     */
    public boolean takeDamage(int attack) {
        int defense = this.getDefense();
        int damage = attack > defense ? attack - defense : 1;

        this.health -= damage;
        return this.isDead();
    }

    /**
     * Advances the turn of all stats that can expire and
     * removes any that do
     */
    public void advanceTurn() {
        this.advanceFlatTurn();
        this.advanceScalarTurn();
    }

    /**
     * Handles weapon information to make a new stat, sets it
     * as the new weapon buff
     * 
     * @param weapon the weapon to create a stat from
     */
    public void handleWeapon(Weapon weapon) {
        this.weaponBuff = new Stat(weapon.getBuffs(), -1);
    }

    /**
     * Handles armor information to make a new stat, sets it
     * as the new armor buff
     * 
     * @param armor the armor to create a stat from
     */
    public void handleArmor(Armor armor) {
        this.armorBuff = new Stat(armor.getBuffs(), -1);
    }

    /**
     * Handles food information to heal the player, doesn't
     * allow for user to go above max health
     * 
     * @param food the food to heal with
     */
    public void handleFood(Food food) {
        int healedHealth = food.getBuffs().get(Item.HEALTH_KEY);

        this.health = Math.min(this.maxHealth, this.health + healedHealth);
    }

    /**
     * Handles buff information to make a new stat, adds it
     * to the flat buff stats
     * 
     * @param buff the buff to create a stat from
     */
    public void handleBuff(Buff buff) {
        this.flatBuffs.add(new Stat(buff.getBuffs(), 10));
    }

    /**
     * Handles bag information to make a new stat, doesn't
     * do anything as bags don't create stats
     * 
     * @param bag the bag to create a stat from
     */
    public void handleBag(Bag bag) {}
    
    /**
     * The toString function, gets string representation of the current stats
     * 
     * @return the string representation of the StatManager
     */
    public String toString() {
        return this.health + "/" + this.maxHealth + " health " + this.attack() + " attack " + this.getDefense() + " defense";
    }

    /**
     * Checks to see if the health has dropped below 0
     * 
     * @return boolean based on if health has dropped below 0
     */
    private boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Advances all flat buffs and removes any that
     * have expired in the list
     */
    private void advanceFlatTurn() {    
        int i = 0;

        while(i < this.flatBuffs.size()) {
            Stat stat = this.flatBuffs.get(i);
            if(stat.advanceTurn()) {
                this.flatBuffs.remove(stat);
            } else {
                i++;
            }
        }
    }

    /**
     * Advances all scalar buffs and removes any
     * that have expired in the list
     */
    private void advanceScalarTurn() {
        int i = 0;

        while(i < this.scalarBuffs.size()) {
            Stat stat = this.scalarBuffs.get(i);
            if(stat.advanceTurn()) {
                this.scalarBuffs.remove(stat);
            } else {
                i++;
            }
        }
    }

    /**
     * Gets the total flat attack buffs from all flat buffs
     * 
     * @return the total flat attack buff from all flat buffs
     */
    private int getFlatAttack() {
        int attack = 0;

        for (Stat flatBuff : flatBuffs) {
            attack += flatBuff.getAttack();
        }

        return attack;
    }

    /**
     * Gets the total scalar attack buffs using multiplicative
     * rules for stats
     * 
     * @return the total scalar attack buff from all scalar buffs
     */
    private int getScalarAttack() {
        int attack = 1;

        for (Stat scalarBuff : scalarBuffs) {
            attack *= scalarBuff.getAttack();
        }

        return attack;
    }

    /**
     * Obtains the total defense by calculating all the 
     * defense buffs from used buffs and equipment
     * 
     * @return the total defense stat
     */
    private int getDefense() {
        int defense;
        int multiplier;
        
        defense = getFlatDefense();
        multiplier = getScalarDefense();

        defense += this.getEquipmentDefense();
        return multiplier * defense;
    }

    /**
     * Gets the total flat defense buffs from all flat buffs
     * 
     * @return the total flat defense buff from all flat buffs
     */
    private int getFlatDefense() {
        int defense = 0;

        for (Stat flatBuff : flatBuffs) {
            defense += flatBuff.getDefense();
        }

        return defense;
    }

    /**
     * Gets the total scalar defense buffs using multiplicative
     * rules for stats
     * 
     * @return the total scalar defense buff from all scalar buffs
     */
    private int getScalarDefense() {
        int defense = 1;

        for (Stat scalarBuff : scalarBuffs) {
            defense *= scalarBuff.getDefense();
        }

        return defense;
    }

    /**
     * Gets the attack buffs from the equipment if any is equipped
     * 
     * @return the total flat attack buff from all equipment
     */
    private int getEquipmentAttack() {
        int attack = 0;

        attack += (this.weaponBuff == null ? 0 : this.weaponBuff.getAttack());
        attack += (this.armorBuff == null ? 0 : this.armorBuff.getAttack());

        return attack;
    }

    /**
     * Gets the defense buffs from the equipment if any is equipped
     * 
     * @return the total flat defense buff from all equipment
     */
    private int getEquipmentDefense() {
        int defense = 0;

        defense += (this.weaponBuff == null ? 0 : this.weaponBuff.getDefense());
        defense += (this.armorBuff == null ? 0 : this.armorBuff.getDefense());

        return defense;
    }
}
