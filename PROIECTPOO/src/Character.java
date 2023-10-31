import java.util.LinkedList;
import java.util.Scanner;

public abstract class Character extends Entity {
    protected String name;
    protected int x, y;
    protected int level, experience;
    protected float strength, charisma, dexterity;
    protected Inventory inventory;

    protected Character(final String name, final int level, final int experience) {
        this(name, level, experience, 0, 0);
    }

    protected Character(final String name, final int level, final int experience, final int x, final int y) {
        this.name = name; this.level = level; this.experience = experience; this.x = x; this.y = y;
        strength = 1.f; charisma = 1.f; dexterity = 1.f;

        strength += level;
        charisma += level / 2;
        dexterity += level / 3;

        inventory = new Inventory(100);

        int no = 3;

        spells = new LinkedList<>();

        for (int i = 1; i <= no; i++) {
            int type = (int)Math.floor(3 * Math.random());

            if (type == 0)
                spells.add(new Fire(30, 30));
            else if (type == 1)
                spells.add(new Ice(30, 30));
            else
                spells.add(new Earth(30, 30));
        }
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    boolean buyPotion(Potion potion) {
        if (potion.getPrice() > inventory.getCoins()) {
            System.out.println("Not enough coins!\n");
            return false;
        }

        if (potion.getWeight() > inventory.getRemainingWeight()) {
            System.out.println("Not enough space!\n");
            return false;
        }

        inventory.addPotion(potion);
        return true;
    }

    void interact(Shop shop) throws InvalidCommandException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String s;

            System.out.println(shop);
            System.out.println(inventory);
            System.out.print("Choose the index of the potion you would like to buy or press x to close the shop: ");
            s = scanner.next();

            if (s.contains("x"))
                break;

            int index = Integer.parseInt(s);

            if (index <= 0 || index > shop.potions.size())
                throw new InvalidCommandException();

            Potion potion = shop.select(index);

            if (!buyPotion(potion))
                shop.potions.add(index - 1, potion);


            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

    boolean interact(Enemy enemy) throws InvalidCommandException {
        Scanner scanner = new Scanner(System.in);
        int a = 0;

        while (true) {
            String s;

            System.out.println(this);
            System.out.println("\n" + enemy);

            if (a == 0) {
                System.out.println("Your turn:");

                if (inventory.potions.isEmpty())
                    System.out.print("Press a for attack: ");
                else
                    System.out.print("Press a for attack or p for using a potion: ");

                s = scanner.next();

                if (s.contains("a")) {
                    if (spells.isEmpty()) {
                        float damage = getDamage();

                        enemy.receiveDamage(damage);
                        System.out.println("Enemy received " + damage + " damage!\n");
                    } else {
                        System.out.print("Choose the index of the spell you would like to use or n for normal attack: ");
                        s = scanner.next();

                        if (s.contains("n")) {
                            float damage = getDamage();

                            enemy.receiveDamage(damage);
                        } else {
                            float health = enemy.currHealth;

                            int index = Integer.parseInt(s);

                            if (index >= 1 && index <= spells.size()) {
                                useSpell(spells.get(Integer.parseInt(s) - 1), enemy);
                                System.out.println("Enemy received " + (health - currHealth) + " damage!\n");
                            } else
                                throw new InvalidCommandException();
                        }
                    }
                } else if (s.contains("p")) {
                    System.out.print("Choose the index of the potion you would like to use: ");
                    s = scanner.next();

                    int index = Integer.parseInt(s);

                    if (index >= 1 && index <= inventory.potions.size())
                        inventory.potions.remove(index - 1).use(this);
                    else
                        throw new InvalidCommandException();
                } else
                    throw new InvalidCommandException();
            } else {
                System.out.println("Enemy turn:");

                float damage = enemy.getDamage();

                receiveDamage(damage);
                System.out.println("You received " + damage + " damage!");

                if (currHealth <= 0) {
                    System.out.println("You died!");
                    return true;
                }
            }

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            if (a == 0)
                a = 1;
            else
                a = 0;

            if (enemy.currHealth <= 0)
                break;
        }

        return false;
    }

    public String toString() {
        String characterToString = new String();

        characterToString += "Character\n";
        characterToString += "Health: " + Float.toString(currHealth) + "\n";
        characterToString += "Mana: " + Float.toString(currMana) + "\n";

        for (int i = 0; i < spells.size(); i++)
            characterToString += Integer.toString(i + 1) + ") " + spells.get(i).toString();

        characterToString += inventory.toString() + "\n";

        return characterToString;
    }
}
