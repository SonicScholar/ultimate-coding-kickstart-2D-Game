package com.sonicscholar.simpleBoardGame.treasureHunt;

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
    public void setRow(int _row) {
        this._row = _row;
    }

    public int getColumn() {
        return _column;
    }
    public void setColumn(int _column) {
        this._column = _column;
    }



    public Coordinate(int row, int column) {
        _row = row;
        _column = column;
    }

}
