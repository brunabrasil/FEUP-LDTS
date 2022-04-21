package tetris;

import java.lang.reflect.Array;

public class Matrix<E> {
    private final int columns;
    private final int rows;
    private final int length;
    private final E[] array;

    public Matrix(Class<E> componentType, int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.length = columns * rows;
        //this.matrix = (E[][]) Array.newInstance(componentType, columns, rows);
        this.array = (E[]) Array.newInstance(componentType, length);
    }

    public E get(int column, int row) {
        return array[calculateIndex(column, row)];
    }

    public E get(Coordinate position) {
        return get(position.getColumn(), position.getRow());
    }

    public void set(int column, int row, E e) {
        array[calculateIndex(column, row)] = e;
    }

    public void set(Coordinate position, E e) {
        set(position.getColumn(), position.getRow(), e);
    }

    private int calculateIndex(int column, int row) {
        return column + row * columns;
    }

    public boolean isInside(int column, int row) {
        return 0 <= column && column < columns && 0 <= row && row < rows;
    }

    public boolean isInside(Coordinate position) {
        return isInside(position.getColumn(), position.getRow());
    }

    public boolean isEmpty(int column, int row) {
        return isInside(column, row) && get(column, row) == null;
    }

    public boolean isEmpty(Coordinate position) {
        return isEmpty(position.getColumn(), position.getRow());
    }

    public void fill(E e) {
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++) {
                set(i, j, e);
            }
        }
    }
}
