package dungeonforge.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractFactoryTest {

    @Test
    public void testThemeRegistryMapping() {
        MonsterFactory factory = new MonsterFactory();
        ThemeRegistry registry = new ThemeRegistry(factory);

        // Verify depth maps to the correct theme name using forDepth
        ThemeKit level1Kit = registry.forDepth(1);
        assertEquals("Crypt", level1Kit.themeName());

        ThemeKit level2Kit = registry.forDepth(2);
        assertEquals("Forge", level2Kit.themeName());

        ThemeKit level3Kit = registry.forDepth(3);
        assertEquals("Frost", level3Kit.themeName());
    }

    @Test
    public void testCryptThemeKitIntegrity() {
        MonsterFactory factory = new MonsterFactory();
        ThemeKit kit = new CryptThemeKit(factory);
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
        MonsterFactory factory = new MonsterFactory();
        ThemeKit kit = new ForgeThemeKit(factory);
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
        MonsterFactory factory = new MonsterFactory();
        ThemeKit kit = new FrostThemeKit(factory);
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