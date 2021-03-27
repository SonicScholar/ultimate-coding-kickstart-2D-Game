package com.sonicscholar.simpleBoardGame;

public class DefaultBoard implements Board {

    private final int _width;
    private final int _height;
    private final char[][] _field;

    /**
     * Space character represents unoccupied
     */
    public static final char _defaultMarker = ' ';

    /**
     * Create a com.sonicscholar.SimpleBoardGame.DefaultBoard object with specified width and height
     * @param width the width of the board
     * @param height the height of the board
     */
    public DefaultBoard(int width, int height) {
        _width = width;
        _height = height;

        //char[height][width] means it must be referenced like
        //field[row][col]
        _field = new char[height][width];
        resetBoard();
    }

    @Override
    public int getWidth() {
        return _width;
    }

    @Override
    public int getHeight() {
        return _height;
    }

    @Override
    public void resetBoard() {
        for (int row = 0; row < _height; row++) {
            for (int col = 0; col < _width; col++) {
                clearMarker(row, col);
            }
        }
    }

    @Override
    public void markPosition(int row, int col, char value) {
        //handle accidentally putting ' ' for value
        if(value == _defaultMarker) {
            String message = "Cannot manually mark a position using" +
                    " the default empty marker. Use clearPosition() instead.";
            throw new IllegalArgumentException(message);
        }
        _field[row][col] = value;
    }

    @Override
    public void clearMarker(int row, int col) {
        _field[row][col] = _defaultMarker;
    }

    @Override
    public boolean isEmpty(int row, int col) {
        return _field[row][col] == _defaultMarker;
    }

    @Override
    public char getMarkerAtPosition(int row, int col) {
        return _field[row][col];
    }
}
