package model.character;

public class NocturnalNPC extends NonPlayerCharacter {
    /**
     * The NocturnalNPC constructor for a character with defined stats
     * 
     * @param name the name of the NocturnalNPC
     * @param description the description of the NocturnalNPC
     * @param health the object's base max health
     * @param attack the object's base attack
     * @param defence the object's base defense
     */
    public NocturnalNPC(String name, String description, int health, int attack, int defence) {
        super(name, description, health, attack, defence);
    }

    /**
     * The NocturnalNPC constructor for a character using random stats
     * 
     * @param name the name of the NocturnalNPC
     * @param description the description of the NocturnalNPC
     */
    public NocturnalNPC(String name, String description) {
        super(name, description);
    }

    /**
     * The NocturnalNPC's toString method, displays information on
     * the character's state and stats
     */
    public String toString() {
        return "(Nocturnal) " + super.toString();
    }
}
