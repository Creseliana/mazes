package com.creseliana.mazes;

/**
 * @author Valeria Sterzhanova
 */
//@Getter
public enum Direction {
    LEFT(0, -1),
    RIGHT(0, 1),
    ABOVE(-1, 0),
    BELOW(1, 0),
    ;

    private final int row;
    private final int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
