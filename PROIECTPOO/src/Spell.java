public abstract class Spell implements Visitor{
    protected float damage, mana;

    protected Spell(final float damage, final float mana) {
        this.damage = damage; this.mana = mana;
    }
}
