package tests.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.DiurnalNPC;
import model.character.NocturnalNPC;
import model.character.PlayerCharacter;
import model.inventory.Item;
import model.inventory.concrete_items.Weapon;

@Testable
public class TestCharacter {
    @Test
    public void TestNocturnalNPC() {
        String expectedString = "(Nocturnal) Enemy Enemy: An enemy | 10/10 health 10 attack 10 defense";
        String expectedHurtString = "(Nocturnal) Enemy Enemy: An enemy | 5/10 health 10 attack 10 defense";

        NocturnalNPC npc = new NocturnalNPC("Enemy", "An enemy", 10, 10, 10);
        
        assertEquals(expectedString, npc.toString());
        assertEquals(10, npc.attack());
        assertFalse(npc.takeDamage(15));
        assertEquals(expectedHurtString, npc.toString());
        assertTrue(npc.takeDamage(15));
    }

    @Test
    public void TestDiurnalNPC() {
        String expectedString = "(Diurnal) Enemy Enemy: An enemy | 10/10 health 10 attack 10 defense";
        String expectedHurtString = "(Diurnal) Enemy Enemy: An enemy | 5/10 health 10 attack 10 defense";

        DiurnalNPC npc = new DiurnalNPC("Enemy", "An enemy", 10, 10, 10);
        
        assertEquals(expectedString, npc.toString());
        assertEquals(10, npc.attack());
        assertFalse(npc.takeDamage(15));
        assertEquals(expectedHurtString, npc.toString());
        assertTrue(npc.takeDamage(15));
    }

    @Test
    public void testPlayerCharacter() {
        String expectedString = "Player Player: A Player | 100/100 health 10 attack 10 defense";
        String expectedHurtString = "Player Player: A Player | 1/100 health 10 attack 10 defense";
        PlayerCharacter pc = new PlayerCharacter("Player", "A Player");


        assertEquals(expectedString, pc.toString());
        assertEquals(10, pc.attack());
        assertFalse(pc.takeDamage(109));
        assertEquals(expectedHurtString, pc.toString());
        assertTrue(pc.takeDamage(10));
    }

    @Test
    public void testPlayerCharacterUse() {
        String expectedString = "Player Player: A Player | 100/100 health 20 attack 10 defense";
        PlayerCharacter pc = new PlayerCharacter("Player", "A Player");
        Item sword = new Weapon("Weapon", 10, 10);

        pc.addItem(sword);
        pc.useItem(0, 0);
        pc.useItem(1, 1);

        assertEquals(expectedString, pc.toString());
        assertEquals(20, pc.attack());
    }
}
