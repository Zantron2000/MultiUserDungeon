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
    public DiurnalNPC(String name, String description, int health, int attack, int defence) {
        super(name, description, health, attack, defence);
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

    public String toString() {
        return "(Diurnal) " + super.toString();
    }
}
