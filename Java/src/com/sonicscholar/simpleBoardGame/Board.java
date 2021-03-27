package com.sonicscholar.simpleBoardGame;

/**
 * A simple 2 dimensional, width x height, rectangular board that uses
 * characters as markers that occupy space on the board.
 */
public interface Board {
    /**
     * Gets the width of the board
     * @return width of the board
     */
    int getWidth();

    /**
     * Gets the height of the board
     * @return height of the board
     */
    int getHeight();

    /**
     * Resets the board to whatever represents the default state
     * for this type of board.
     */
    void resetBoard();

    /**
     * Marks a position on the board with the specified character
     * value. If an existing marker is in this space, it will be
     * overwritten.
     * @param row The row value of the position to be marked.
     * @param col The column value of the position to be marked.
     * @param value The character value to be marked.
     */
    void markPosition(int row, int col, char value);

    /**
     * Clears a marker at the specified position, resetting it
     * back to the default value.
     * @param row The row value of the position to clear.
     * @param col The column value of the position to clear.
     */
    void clearMarker(int row, int col);

    /**
     * Gets whether or not a space on the board is considered
     * occupied at the position specified by row and col.
     * @param row The row value of the position to check.
     * @param col The column value of the position to check.
     * @return true if the space is considered occupied
     */
    boolean isEmpty(int row, int col);

    /**
     * Gets the character value of the specified position on the
     * board. The character value may represent a real marker, or
     * the default character for an unoccupied space.
     * @param row The row value of the position to get
     * @param col The column value of the position to get
     * @return the char marker value
     */
    char getMarkerAtPosition(int row, int col);
}
