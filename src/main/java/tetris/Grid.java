package tetris;

public class Grid {
    private final int width;
    private final int height;
    private final int visibleHeight;
    private final Matrix<Integer> grid;

    public Grid(int width, int height, int visibleHeight) {
        this.width = width;
        this.height = height;
        this.visibleHeight = visibleHeight;
        this.grid = new Matrix<>(Integer.class, width, height);
        grid.fill(null);

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getVisibleHeight() { return visibleHeight; }

    public boolean isRowFull(int row) {
        for (int i = 0; i < width; i++) {
            if (grid.get(i, row) == null) return false;
        }
        return true;
    }

    public boolean isRowEmpty(int row) {
        for (int i = 0; i < width; i++) {
            if (grid.get(i, row) != null) return false;
        }
        return true;
    }

    public void clearRow(int row) {
        for (int i = 0; i < width; i++) {
            grid.set(i, row, null);
        }
    }

    public void moveRowDown(int row, int numRows) {
        for (int i = 0; i < width; i++) {
            grid.set(i, row - numRows, grid.get(i, row));
            grid.set(i, row, null);
        }
    }

    public int clearFullRows() {
        int cleared = 0;
        for (int j = 0; j < height; j++) {
            if (isRowFull(j)) {
                clearRow(j);
                cleared++;
            } else if (cleared > 0) {
                moveRowDown(j, cleared);
            }
        }
        return cleared;
    }


    public boolean isEmpty(int column, int row) {
        return grid.isEmpty(column, row);
    }

    public boolean isEmpty(Coordinate position) {
        return grid.isEmpty(position);
    }

    public void set(int column, int row, int id) {
        grid.set(column, row, id);
    }

    public Integer get(int column, int row) {
        return grid.get(column, row);
    }
}
