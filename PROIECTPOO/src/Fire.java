public class Fire extends Spell {
    protected Fire(float damage, float mana) {
        super(damage, mana);
    }

    @Override
    public void visit(Entity entity) {
        if (!entity.fire)
            entity.receiveDamage(damage);
    }

    public String toString() {
        String s = new String();

        s += "fire: " + "damage(" + Float.toString(damage) + "), " +
                "mana(" + Float.toString(mana) + ")\n";

        return s;
    }
}
