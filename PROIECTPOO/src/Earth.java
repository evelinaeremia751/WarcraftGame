public class Earth extends Spell {
    protected Earth(float damage, float mana) {
        super(damage, mana);
    }

    @Override
    public void visit(Entity entity) {
        if (!entity.earth)
            entity.receiveDamage(damage);
    }

    public String toString() {
        String s = new String();

        s += "earth: " + "damage(" + Float.toString(damage) + "), " +
                "mana(" + Float.toString(mana) + ")\n";

        return s;
    }
}
