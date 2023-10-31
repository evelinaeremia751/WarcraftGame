import java.util.LinkedList;
import java.util.List;

public class Inventory {
    public List<Potion> potions;
    private float maxWeight;
    private int coins;

    public Inventory(final float maxWeight) {
        this.maxWeight = maxWeight;

        potions = new LinkedList<>();
        coins = 100;
    }

    public void addPotion(final Potion potion) {
        potions.add(potion);
        coins -= potion.getPrice();
    }

    public void removePotion(final Potion potion) {
        potions.remove(potion);
    }

    public float getRemainingWeight() {
        float currWeight = 0.f;

        for (var potion : potions)
            currWeight += potion.getWeight();

        return maxWeight - currWeight;
    }

    public int getCoins() {
        return coins;
    }

    public String toString() {
        String inventoryToString = "Inventory\n";

        inventoryToString += "Potions:\n";

        for (int i = 0; i < potions.size(); i++)
            inventoryToString += Integer.toString(i + 1) + ") " + potions.get(i).toString();

        inventoryToString += "Coins: " + Integer.toString(coins) + "\n";
        inventoryToString += "Remaining weight: " + Float.toString(getRemainingWeight());

        return inventoryToString;
    }
}
