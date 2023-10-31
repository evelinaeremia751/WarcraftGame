public class Warrior extends Character {
    public Warrior(final String name, final int level, final int experience, final int x, final int y) {
        super(name, level, experience, x, y);

        strength += 4.f;
        fire = true;
    }

    @Override
    protected void receiveDamage(float damage) {
        if ((int)Math.floor((20 / dexterity) * Math.random()) == 1) {
            if (currHealth - damage / 2 <= 0)
                currHealth = 0;
            else
                currHealth -= damage / 2;
        } else if ((int)Math.floor((20 / charisma) * Math.random()) == 1) {
            if (currHealth - damage / 2 <= 0)
                currHealth = 0;
            else
                currHealth -= damage / 2;
        } else {
            if (currHealth - damage <= 0)
                currHealth = 0;
            else
                currHealth -= damage;
        }
    }

    @Override
    protected float getDamage() {
        if ((int)Math.floor((50 / strength) * Math.random()) == 1)
            return 40;

        return 20;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
