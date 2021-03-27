package com.sonicscholar.simpleBoardGame;

/**
 * A GameAction represents a move that can be made
 * as part of a turn. It is simply represents an
 * action of putting a marker on a specific space
 * (row, column) of the game board.
 */
public interface GameAction {

    /**
     * The row to place the marker
     * @return the row to place the marker
     */
    int getRow();

    /**
     * The column to place the marker.
     * @return the column to place the marker
     */
    int getColumn();

    /**
     * The marker to place on the board.
     * @return the marker to place on the board
     */
    char getMarker();
}
