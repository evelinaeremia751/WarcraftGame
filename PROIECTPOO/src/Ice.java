public class Ice extends Spell {
    protected Ice(float damage, float mana) {
        super(damage, mana);
    }

    @Override
    public void visit(Entity entity) {
        if (!entity.ice)
            entity.receiveDamage(damage);
    }

    public String toString() {
        String s = new String();

        s += "ice: " + "damage(" + Float.toString(damage) + "), " +
                "mana(" + Float.toString(mana) + ")\n";

        return s;
    }
}
