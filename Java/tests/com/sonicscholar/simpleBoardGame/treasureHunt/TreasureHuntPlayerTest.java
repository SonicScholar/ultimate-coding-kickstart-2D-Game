package com.sonicscholar.simpleBoardGame.treasureHunt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureHuntPlayerTest {

    // 1 2 3
    // 4 5 6
    // 7 8 9

    @Test
    void getCoordinateFromUserInputTestRows() {
        int[] firstRowNumbers = new int[] {1, 2, 3};
        int[] secondRowNumbers = new int[] {4, 5, 6};
        int[] thirdRowNumbers = new int[] {7, 8, 9};


        for (int num: firstRowNumbers) {
            Coordinate c = TreasureHuntPlayer.getCoordinateFromUserInput(num, 3);
            int row = c.getRow();
            assertEquals(0, row);
        }

        for (int num: secondRowNumbers) {
            Coordinate c = TreasureHuntPlayer.getCoordinateFromUserInput(num, 3);
            int row = c.getRow();
            assertEquals(1, row);
        }

        for (int num: thirdRowNumbers) {
            Coordinate c = TreasureHuntPlayer.getCoordinateFromUserInput(num, 3);
            int row = c.getRow();
            assertEquals(2, row);
        }
    }

    @Test
    void getCoordinateFromUserInputTestColumns() {
        int[] firstColumnNumbers = new int[] {1, 4, 7};
        int[] secondColumnNumbers = new int[] {2, 5, 8};
        int[] thirdColumnNumbers = new int[] {3, 6, 9};


        for (int num: firstColumnNumbers) {
            Coordinate c = TreasureHuntPlayer.getCoordinateFromUserInput(num, 3);
            int column = c.getColumn();
            assertEquals(0, column);
        }

        for (int num: secondColumnNumbers) {
            Coordinate c = TreasureHuntPlayer.getCoordinateFromUserInput(num, 3);
            int column = c.getColumn();
            assertEquals(1, column);
        }

        for (int num: thirdColumnNumbers) {
            Coordinate c = TreasureHuntPlayer.getCoordinateFromUserInput(num, 3);
            int column = c.getColumn();
            assertEquals(2, column);
        }
    }

}