package model;

public class Map {
    private final int width = 8;
    private final int height = 8;
    private MapElement[][] grid = new MapElement[height][width];

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }


    public boolean isPassable(int x, int y) {
        return isValidPosition(x, y) && !(grid[y][x] instanceof Wall);
    }

    public boolean placeElement(MapElement element, int x, int y) {
        if (!isValidPosition(x, y) || grid[y][x] != null) {
            return false;
        }

        grid[y][x] = element;
        if (element instanceof Hero) {
            ((Hero) element).setPosition(x, y);
        }
        return true;
    }

    public boolean removeElementAt(int x, int y) {
        if (!isValidPosition(x, y)) {
            return false;
        }
        grid[y][x] = null;
        return true;
    }

    public boolean moveHero(Hero hero, int newX, int newY) {
        if (!isPassable(newX, newY)) {
            return false;
        }

        int oldX = hero.getX();
        int oldY = hero.getY();

        grid[oldY][oldX] = null;
        grid[newY][newX] = hero;
        hero.setPosition(newX, newY);
        return true;
    }

    public MapElement getElementAt(int x, int y) {
        if (!isValidPosition(x, y)) {
            return null;
        }

        return grid[y][x];
    }

    public void display() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(grid[y][x].getMapSymbol() + " ");
                }
            }
            System.out.println();
        }
    }
}
