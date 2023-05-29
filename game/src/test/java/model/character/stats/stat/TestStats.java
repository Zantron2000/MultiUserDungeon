package model.character.stats.stat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.stats.Stat;

@Testable
public class TestStats {
    @Test
    public void testBaseStat() {
        Stat stat = new BaseStat(10, 10);

        assertEquals(10, stat.getAttack(), "The attack of the base stat doesn't match the expected");
        assertEquals(10, stat.getDefense(), "The defense of the base stat doesn't match the expected");
        assertEquals(stat, stat.progressTurn(), "The stat returned from progressing the turn isn't the base stat");
    }

    @Test
    public void testEquippedStat() {
        Stat stat = new EquippedStat(10, 10);

        assertEquals(10, stat.getAttack(), "The attack of the equipped stat doesn't match the expected");
        assertEquals(10, stat.getDefense(), "The defense of the equipped stat doesn't match the expected");
        assertEquals(stat, stat.progressTurn(), "The stat returned from progressing the turn isn't the equipped stat");
    }

    @Test
    public void testTurnStat() {
        Stat base = new BaseStat(10, 10);
        Stat layer1 = new TurnStat(10, 10, 5, base);
        Stat layer2 = new TurnStat(10, 10, 3, layer1);

        Stat stat = layer2.progressTurn();

        assertEquals(30, stat.getAttack(), "The attack of the equipped stat doesn't match the expected");
        assertEquals(30, stat.getDefense(), "The defense of the equipped stat doesn't match the expected");
        assertEquals(layer2, stat, "The stat returned from progressing the turn isn't the layer 2 stat");

        stat = stat.progressTurn();
        assertEquals(30, stat.getAttack(), "The attack of the equipped stat doesn't match the expected");
        assertEquals(30, stat.getDefense(), "The defense of the equipped stat doesn't match the expected");
        assertEquals(layer2, stat, "The stat returned from progressing the turn isn't the layer 2 stat");

        stat = stat.progressTurn();
        assertEquals(20, stat.getAttack(), "The attack of the equipped stat doesn't match the expected");
        assertEquals(20, stat.getDefense(), "The defense of the equipped stat doesn't match the expected");
        assertEquals(layer1, stat, "The stat returned from progressing the turn isn't the layer 1 stat");

        stat = stat.progressTurn();
        assertEquals(20, stat.getAttack(), "The attack of the equipped stat doesn't match the expected");
        assertEquals(20, stat.getDefense(), "The defense of the equipped stat doesn't match the expected");
        assertEquals(layer1, stat, "The stat returned from progressing the turn isn't the layer 1 stat");

        stat = stat.progressTurn();
        assertEquals(10, stat.getAttack(), "The attack of the equipped stat doesn't match the expected");
        assertEquals(10, stat.getDefense(), "The defense of the equipped stat doesn't match the expected");
        assertEquals(base, stat, "The stat returned from progressing the turn isn't the base stat");
    }
}
