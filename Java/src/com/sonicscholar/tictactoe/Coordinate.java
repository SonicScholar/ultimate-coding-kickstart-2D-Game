package com.sonicscholar.tictactoe;

/**
 * This is a class to keep track of a row and column
 * for a board space
 */
public class Coordinate extends Tuple<Integer, Integer>{

    public int getRow() {
        return this.value1;
    }
    public int getColumn() {
        return this.value2;
    }
    
    public Coordinate(int row, int column) {
        super(row, column);
    }

}