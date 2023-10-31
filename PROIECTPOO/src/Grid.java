import java.util.ArrayList;

public class Grid<E> extends ArrayList<E> {
    private int width, height;
    public Character character;
    public Grid<Grid<Cell>> grid;

    private Grid(final int width, final int height, final Character character) {
        this.width = width; this.height = height; this.character = character;
    }

    public static Grid generateMap(final int width, final int height, final Character character) {
        Grid<Cell> map = new Grid<>(width, height, character);

        map.grid = new Grid<>(width, height, character);

        for (int i = 0; i < height; i++) {
            Grid<Cell> line = new Grid<>(width, height, character);

            for (int j = 0; j < width; j++)
                line.add(new Cell(i, j, Cell.Type.EMPTY));

            map.grid.add(line);
        }

        // Shops
        map.grid.get(0).set(3, new Cell(0, 3, Cell.Type.SHOP, new Shop()));
        map.grid.get(1).set(3, new Cell(0, 3, Cell.Type.SHOP, new Shop()));
        map.grid.get(2).set(0, new Cell(0, 3, Cell.Type.SHOP, new Shop()));

        // Enemy
        map.grid.get(3).set(4, new Cell(3, 4, Cell.Type.ENEMY, new Enemy()));

        // Finish
        map.grid.get(4).set(4, new Cell(4, 4, Cell.Type.FINISH));

        return map;
    }

    public void goNorth() {
        grid.get(character.x).get(character.y).visited = true;

        if (character.x - 1 >= 0)
            character.x--;
        else
            System.out.println("Can't go there!");
    }

    public void goSouth() {
        grid.get(character.x).get(character.y).visited = true;

        if (character.x + 1 >= 0)
            character.x++;
        else
            System.out.println("Can't go there!");
    }

    public void goEast() {
        grid.get(character.x).get(character.y).visited = true;

        if (character.y + 1 >= 0)
            character.y++;
        else
            System.out.println("Can't go there!");
    }

    public void goWest() {
        grid.get(character.x).get(character.y).visited = true;

        if (character.y - 1 >= 0)
            character.y--;
        else
            System.out.println("Can't go there!");
    }

    public Grid getGrid() {
        return grid;
    }

    public String toString() {
        String mapToString = "";

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (i == character.getX() && j == character.getY()) {
                    mapToString += "P";
                    continue;
                }

                if (grid.get(i).get(j).visited == false) {
                    mapToString += "?";
                    continue;
                }

                if (grid.get(i).get(j).getType() == Cell.Type.EMPTY)
                    mapToString += "N";
                else if (grid.get(i).get(j).getType() == Cell.Type.FINISH)
                    mapToString += "F";
                else if (grid.get(i).get(j).getCellElement() != null)
                    mapToString += grid.get(i).get(j).getCellElement().toCharacter();
            }

            mapToString += "\n";
        }

        return mapToString;
    }
}
