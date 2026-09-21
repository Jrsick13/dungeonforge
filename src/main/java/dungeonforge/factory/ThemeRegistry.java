package dungeonforge.factory;

public class ThemeRegistry {
    public static ThemeKit getKitForLevel(int depth) {
        return switch (depth) {
            case 2 -> new ForgeThemeKit();
            case 3 -> new FrostThemeKit();
            case 4 -> new VolcanicThemeKit(); // Add your new 4th theme here
            default -> new CryptThemeKit(); // Covers level 1 and any fallback
        };
    }
}