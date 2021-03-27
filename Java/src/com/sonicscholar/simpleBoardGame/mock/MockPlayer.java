package com.sonicscholar.simpleBoardGame.mock;

import com.sonicscholar.simpleBoardGame.AbstractPlayer;
import com.sonicscholar.simpleBoardGame.Board;
import com.sonicscholar.simpleBoardGame.DefaultGameAction;
import com.sonicscholar.simpleBoardGame.GameAction;

public class MockPlayer extends AbstractPlayer {
    protected final Board _board;
    public MockPlayer(String name, char marker, Board board) {
        super(name, marker);
        _board = board;
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    /**
     * Dummy player just picks the first available space on the board
     * going by row first, then column
     * @return com.sonicscholar.SimpleBoardGame.GameAction representing the player's next desired move
     */
    @Override
    public GameAction requestAction() {
        for (int row = 0; row < _board.getHeight(); row++) {
            for (int col = 0; col < _board.getWidth(); col++){
                if(_board.isEmpty(row, col))
                    return new DefaultGameAction(row, col, _marker);
            }
        }
        return null;
    }
}

