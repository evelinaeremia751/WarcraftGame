import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private List<Account> accounts;
    private Map<Cell.Type, List<String>> stories;

    private static Game game;

    private Game() {

    }

    public static Game getInstance() {
        if (game == null)
            game = new Game();

        return game;
    }

    public void run() throws InformationIncompleteException, IOException, InvalidCommandException {
        Scanner keyboard = new Scanner(System.in);
        String s;

        accounts = Test.parseAccounts();
        stories = Test.parseStories();

        for (int i = 0; i < accounts.size(); i++)
            System.out.println(i + 1 + ") " + accounts.get(i));

        System.out.print("Choose the index of the account you would like to use: ");
        s = keyboard.next();

        int index = Integer.parseInt(s);
        Account account;
        Character character;

        if (index >= 1 && index <= accounts.size())
            account = accounts.get(index - 1);
        else
            throw new InvalidCommandException();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        for (int i = 0; i < account.characters.size(); i++)
            System.out.println(i + 1 + ") " + account.characters.get(i));

        System.out.print("Choose the index of the character you would like to use: ");
        s = keyboard.next();

        index = Integer.parseInt(s);

        if (index >= 1 && index <= account.characters.size())
            character = account.characters.get(index - 1);
        else
            throw new InvalidCommandException();

        boolean shouldPrint = true;
        Grid<Cell> grid = Grid.generateMap(5, 5, character);

        List<String> moves = new LinkedList<>();

        moves.add("right");
        moves.add("right");
        moves.add("right");
        moves.add("right");
        moves.add("down");
        moves.add("down");
        moves.add("down");
        moves.add("down");

        while (true) {
            if (grid.grid.get(grid.character.getX()).get(grid.character.getY()).getType() == Cell.Type.FINISH) {
                System.out.println(grid);
                printStory(grid);
                System.out.println("You won!");
                break;
            }

            if (shouldPrint) {
                System.out.println(grid);
                printStory(grid);
                showCellOptions(grid);
                shouldPrint = false;

                if (grid.character.currHealth <= 0)
                    return;
            }

            System.out.print("Press p for next move: ");
            s = keyboard.next();

            if (s.contains("p")) {
                String move = moves.remove(0);

                if (move == "right")
                    grid.goEast();
                else if (move == "left")
                    grid.goWest();
                else if (move == "north")
                    grid.goNorth();
                else
                    grid.goSouth();

                shouldPrint = true;
            } else
                throw new InvalidCommandException();
        }
    }

    void showCellOptions(Grid<Cell> grid) throws InvalidCommandException {
        if (grid.grid.get(grid.character.getX()).get(grid.character.getY()).getType() == Cell.Type.SHOP) {
            Shop shop = (Shop)grid.grid.get(grid.character.getX()).get(grid.character.getY()).getCellElement();

            grid.character.interact(shop);
        } else if (grid.grid.get(grid.character.getX()).get(grid.character.getY()).getType() == Cell.Type.ENEMY) {
            Enemy enemy = (Enemy)grid.grid.get(grid.character.getX()).get(grid.character.getY()).getCellElement();

            grid.character.interact(enemy);
        }
    }

    void printStory(Grid<Cell> grid) {
        Cell.Type type = grid.grid.get(grid.character.getX()).get(grid.character.getY()).getType();
        List<String> stories_of_that_type = stories.get(type);

        System.out.println(stories_of_that_type.get((int)Math.floor(stories_of_that_type.size() * Math.random())));
    }
}
