package com.sonicscholar.simpleBoardGame;

/**
 * Default implementation of GameAction
 */
public class DefaultGameAction implements GameAction {
    final int _row;
    final int _column;
    final char _marker;
    public DefaultGameAction(int row, int col, char marker) {
        _row = row;
        _column = col;
        _marker = marker;
    }

    @Override
    public int getRow() {
        return _row;
    }

    @Override
    public int getColumn() {
        return _column;
    }

    @Override
    public char getMarker() {
        return _marker;
    }
}
