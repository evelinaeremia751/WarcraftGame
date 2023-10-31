import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Shop implements CellElement {
    public List<Potion> potions;

    public Shop() {
        potions = new LinkedList<>();

        int noOfPotions = 2 + (int)Math.floor(3 * Math.random());

        potions.add(new HealthPotion(30, 25, 50));
        potions.add(new ManaPotion(30, 25, 50));

        for (int i = 3; i <= noOfPotions; i++) {
            int type = (int)Math.floor(2 * Math.random());

            if (type == 0)
                potions.add(new HealthPotion(30, 25, 50));
            else
                potions.add(new ManaPotion(30, 25, 50));
        }
    }

    public Potion select(final int index) {
        if (index - 1 >= potions.size())
            return null;

        return potions.remove(index - 1);
    }

    @Override
    public char toCharacter() {
        return 'S';
    }

    public String toString() {
        String shopToString = "Shop\n";

        for (int i = 0; i < potions.size(); i++)
            shopToString += i + 1 + ") " + potions.get(i).toString();

        return shopToString;
    }
}
