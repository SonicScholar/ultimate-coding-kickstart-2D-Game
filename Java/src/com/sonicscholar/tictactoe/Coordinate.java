package com.sonicscholar.tictactoe;

/**
 * This is a class to keep track of a row and column
 * for a board space
 */
public class Coordinate {
    private int _row;
    private int _column;

    public int getRow() {
        return _row;
    }
    public void setRow(int row) {
        this._row = row;
    }

    public int getColumn() {
        return _column;
    }
    public void setColumn(int column) {
        this._column = column;
    }

    public Coordinate(int row, int column) {
        _row = row;
        _column = column;
    }

}