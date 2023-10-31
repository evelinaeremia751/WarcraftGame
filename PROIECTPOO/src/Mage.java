public class Mage extends Character {
    public Mage(final String name, final int level, final int experience, final int x, final int y) {
        super(name, level, experience, x, y);

        charisma += 4.f;
        ice = true;
    }

    @Override
    protected void receiveDamage(float damage) {
        if ((int)Math.floor((20 / dexterity) * Math.random()) == 1) {
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
        if ((int)Math.floor((10 / charisma) * Math.random()) == 1)
            return 20;

        return 10;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}