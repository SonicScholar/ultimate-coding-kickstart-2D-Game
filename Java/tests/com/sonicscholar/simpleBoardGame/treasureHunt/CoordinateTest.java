package com.sonicscholar.simpleBoardGame.treasureHunt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    static Coordinate getTestCoordinate()
    {
        return new Coordinate(4,5);
    }
    @Test
    void getRow() {
        Coordinate c = getTestCoordinate();
        int row = c.getRow();
        assertEquals(4, row);
    }

    @Test
    void setRow() {
        Coordinate c = getTestCoordinate();
        c.setRow(9);
        int row = c.getRow();
        assertEquals(9, row);
    }

    @Test
    void getColumn() {
        Coordinate c = getTestCoordinate();
        int col = c.getColumn();
        assertEquals(5, col);
    }

    @Test
    void setColumn() {
        Coordinate c = getTestCoordinate();
        c.setColumn(10);
        int col = c.getColumn();
        assertEquals(10, col);
    }
}