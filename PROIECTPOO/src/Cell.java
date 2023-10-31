public class Cell {
    public enum Type {
        EMPTY,
        ENEMY,
        SHOP,
        FINISH
    }

    private final int x, y;
    private Type type;
    private CellElement cellElement;
    public boolean visited;

    public Cell(final int x, final int y, final Type type) {
        this(x, y, type, null);
    }

    public Cell(final int x, final int y, final Type type, final CellElement cellElement) {
        this.x = x; this.y = y; this.type = type; this.cellElement = cellElement;

        visited = false;
    }

    public Type getType() {
        return type;
    }

    public CellElement getCellElement() {
        return cellElement;
    }

    public static Type getTypeFromString(final String type) {
        if (type.contains("EMPTY"))
            return Type.EMPTY;

        if (type.contains("ENEMY"))
            return Type.ENEMY;

        if (type.contains("SHOP"))
            return Type.SHOP;

        return Type.FINISH;
    }
}
