package dungeonforge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkeletonTest {

    @Test
    public void testSkeletonNotNull() {
        String testStr = "DungeonForge";
        assertNotNull(testStr);
        assertFalse(testStr.isEmpty());
    }

    @Test
    public void testSkeletonTrue() {
        assertTrue(true);
    }
}