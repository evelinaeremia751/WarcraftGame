import java.util.List;

public abstract class Entity implements Element {
    protected List<Spell> spells;
    protected float currHealth, maxHealth;
    protected float currMana, maxMana;
    protected boolean fire, ice, earth;

    protected Entity() {
        this(100.f, 100.f, 100.f, 100.f, false, false, false);
    }

    protected Entity(final float currHealth, final float maxHealth,
                     final float currMana, final float maxMana,
                     final boolean fire, final boolean ice, final boolean earth) {
        this.currHealth = currHealth; this.maxHealth = maxHealth;
        this.currMana = currMana; this.maxMana = maxMana;
        this.fire = fire; this.ice = ice; this.earth = earth;
    }

    protected void regenHealth(final float health) {
        currHealth = Math.min(currHealth + health, maxHealth);
    }

    protected void regenMana(final float mana) {
        currMana = Math.min(currMana + mana, maxMana);
    }

    protected void useSpell(Spell spell, Entity entity) {
        entity.accept(spell);
        spells.remove(spell);
    }

    protected abstract void receiveDamage(final float damage);
    protected abstract float getDamage();
}
