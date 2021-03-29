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

    /// <summary>
    ///
    /// </summary>
    /// <param name="c">The character to string together</param>
    /// <returns>String of 'c' length Width*2+1</returns>

    /**
     * Helper method used by ToString() to get a string of
     * characters that matches the board's ASCII representation
     * width.
     * @param c the character to string together
     * @return String of 'c' of length 2*width+1
     */
    private String GetStringRowOfCharacters(char c) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 2 * _width + 1; i++) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    /**
     * Helper method used to get a row of characters that represent
     * the state of the board on a given row.
     * @param row the row number of the board
     * @return String representing this row
     */
    private String GetStringRowOfBoard(int row) {
        StringBuilder stringBuilder = new StringBuilder();
        //print all the columns in this row
        for (int col = 0; col < _width; col++) {
            char marker = getMarkerAtPosition(row, col);
            stringBuilder.append("|" + marker);
        }
        //append closing |
        stringBuilder.append("|");
        return stringBuilder.toString();
    }


    /**
     * Creates an ASCII representation of the board's state
     * @return an ASCII representation of the board's state
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        //print top row
        stringBuilder.append(GetStringRowOfCharacters('='));
        stringBuilder.append("\r\n");

        //print all the rows
        for (int row = 0; row < _height; row++)
        {
            stringBuilder.append(GetStringRowOfBoard(row));
            stringBuilder.append("\r\n");

            //if it's the last row, print ===='s
            //else print ----'s
            stringBuilder.append(row == _height - 1
                    ? GetStringRowOfCharacters('=')
                    : GetStringRowOfCharacters('-'));
            stringBuilder.append("\r\n");
        }

        return stringBuilder.toString();
    }
}
