public class HealthPotion implements Potion {
    private float price, weight, regenValue;

    public HealthPotion(final float price, final float weight, final float regenValue) {
        this.price = price; this.weight = weight; this.regenValue = regenValue;
    }

    @Override
    public void use(Entity entity) {
        entity.regenHealth(regenValue);
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public float getRegenValue() {
        return regenValue;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    public String toString() {
        return "HP: " +
                "price(" + Float.toString(price) + "), " +
                "weight(" + Float.toString(weight) + "), " +
                "regenValue("  + Float.toString(regenValue) + ")\n";
    }
}
