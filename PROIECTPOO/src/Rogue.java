public class Rogue extends Character {
    public Rogue(final String name, final int level, final int experience, final int x, final int y) {
        super(name, level, experience, x, y);

        dexterity += 4.f;
        earth = true;
    }

    @Override
    protected void receiveDamage(float damage) {
        if ((int)Math.floor((20 / charisma) * Math.random()) == 1) {
            if (currHealth - damage / 2 <= 0)
                currHealth = 0;
            else
                currHealth -= damage / 2;
        } else if ((int)Math.floor((20 / strength) * Math.random()) == 1) {
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
        if ((int)Math.floor((35 / dexterity) * Math.random()) == 1)
            return 30;

        return 15;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}