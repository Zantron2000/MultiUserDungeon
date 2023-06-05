package controller.gameGenerators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.characters.PlayerCharacter;

@Testable
public class TestPlayerGenerator {
    @Test
    public void testGeneratePlayer() {
        PlayerCharacter player = PlayerGenerator.generatePlayer("John Doe", "A random player");
        String emptyInventory = "Inventory: 0 gold\n\t1. Backpack 0/6 capacity\n\t\t1. None\n\t\t2. None\n\t\t3. None\n\t\t4. None\n\t\t5. None\n\t\t6. None\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        String output = "John Doe - A random player: 100/100 health, 10 attack, 0 defense";

        assertEquals(output, player.toString(), "The player toString is off");
        assertEquals(10, player.getDamage(), "The initial starting attack is wrong");
        assertEquals(0, player.getArmor(), "The initial starting defense is wrong");

        player.takeDamage(99);
        assertFalse(player.isDead(), "The initial health for the player is wrong");
        player.takeDamage(1);
        assertTrue(player.isDead(), "The initial health for the player is wrong");

        assertEquals(emptyInventory, player.openInventory(), "The inventory contents are wrong");
    }
}
