package dungeonforge.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractFactoryTest {

    @Test
    public void testThemeRegistryMapping() {
        // Verify depth maps to the correct theme name
        ThemeKit level1Kit = ThemeRegistry.getKitForLevel(1);
        assertEquals("Crypt", level1Kit.themeName());

        ThemeKit level2Kit = ThemeRegistry.getKitForLevel(2);
        assertEquals("Forge", level2Kit.themeName());

        ThemeKit level3Kit = ThemeRegistry.getKitForLevel(3);
        assertEquals("Frost", level3Kit.themeName());
    }

    @Test
    public void testCryptThemeKitIntegrity() {
        ThemeKit kit = new CryptThemeKit();
        assertEquals("Crypt", kit.themeName());
        assertNotNull(kit.createRoomFlavor());

        MonsterDef monster = kit.createMonster(1);
        assertNotNull(monster);
        assertEquals("Crypt", monster.getTheme());
        assertFalse(monster.isBoss());

        MonsterDef boss = kit.createBoss(1);
        assertNotNull(boss);
        assertEquals("Crypt", boss.getTheme());
        assertTrue(boss.isBoss());
    }

    @Test
    public void testForgeThemeKitIntegrity() {
        ThemeKit kit = new ForgeThemeKit();
        assertEquals("Forge", kit.themeName());
        assertNotNull(kit.createRoomFlavor());

        MonsterDef monster = kit.createMonster(2);
        assertNotNull(monster);
        assertEquals("Forge", monster.getTheme());
        assertFalse(monster.isBoss());

        MonsterDef boss = kit.createBoss(2);
        assertNotNull(boss);
        assertEquals("Forge", boss.getTheme());
        assertTrue(boss.isBoss());
    }

    @Test
    public void testFrostThemeKitIntegrity() {
        ThemeKit kit = new FrostThemeKit();
        assertEquals("Frost", kit.themeName());
        assertNotNull(kit.createRoomFlavor());

        MonsterDef monster = kit.createMonster(3);
        assertNotNull(monster);
        assertEquals("Frost", monster.getTheme());
        assertFalse(monster.isBoss());

        MonsterDef boss = kit.createBoss(3);
        assertNotNull(boss);
        assertEquals("Frost", boss.getTheme());
        assertTrue(boss.isBoss());
    }
}