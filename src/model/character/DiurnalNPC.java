package model.character;

public class DiurnalNPC extends NonPlayerCharacter {
    /**
     * The DiurnalNPC constructor for a character with defined stats
     * 
     * @param name the name of the DirunalNPC
     * @param description the description of the DirunalNPC
     * @param health the object's base max health
     * @param attack the object's base attack
     * @param defence the object's base defense
     */
    public DiurnalNPC(String name, String description, int attack, int defence, int health) {
        super(name, description, attack, defence, health);
    }

    /**
     * The DirunalNPC constructor for a character using random stats
     * 
     * @param name the name of the DirunalNPC
     * @param description the description of the DirunalNPC
     */
    public DiurnalNPC(String name, String description) {
        super(name, description);
    }

    /**
     * The DiurnalNPC's toString method, displays information on
     * the character's state and stats
     */
    public String toString() {
        return "(Diurnal) " + super.toString();
    }
}
