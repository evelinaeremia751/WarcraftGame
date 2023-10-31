import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Enemy extends Entity implements CellElement {
    List<Spell> spells;

    public Enemy() {
        spells = new LinkedList<>();

        int no = 2 + (int)Math.floor(3 * Math.random());

        for (int i = 1; i <= no; i++) {
            int type = (int)Math.floor(3 * Math.random());

            if (type == 0)
                spells.add(new Fire(30, 30));
            else if (type == 1)
                spells.add(new Ice(30, 30));
            else
                spells.add(new Earth(30, 30));
        }

        if ((int)Math.floor(3 * Math.random()) == 1)
            ice = true;

        if ((int)Math.floor(3 * Math.random()) == 1)
            fire = true;

        if ((int)Math.floor(3 * Math.random()) == 1)
            earth = true;
    }

    @Override
    public char toCharacter() {
        return 'E';
    }

    @Override
    protected void receiveDamage(float damage) {
        int type = (int)Math.floor(2 * Math.random());

        if (type == 0) {
            if (currHealth - damage <= 0)
                currHealth = 0;
            else
                currHealth -= damage;

            System.out.println("Enemy received " + Float.toString(damage) + " damage!");
        } else
            System.out.println("Enemy dodged!");
    }

    @Override
    protected float getDamage() {
        int type = (int)Math.floor(2 * Math.random());

        if (type == 0)
            return 12.5f;

        return 25.0f;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        String characterToString = new String();

        characterToString += "Enemy\n";
        characterToString += "Health: " + Float.toString(currHealth) + "\n";
        characterToString += "Mana: " + Float.toString(currMana) + "\n";

        for (int i = 0; i < spells.size(); i++)
            characterToString += Integer.toString(i + 1) + ") " + spells.get(i).toString();

        return characterToString;
    }
}
