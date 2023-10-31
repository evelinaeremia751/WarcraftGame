public class CharactersFactory {
    public static Character getType(final String profession, final String name, final int level, final int experience) {
        if (profession.contains("Warrior"))
            return new Warrior(name, level, experience, 0, 0);
        else if (profession.contains("Mage"))
            return new Mage(name, level, experience, 0, 0);
        else
            return new Rogue(name, level, experience, 0, 0);
    }
}
