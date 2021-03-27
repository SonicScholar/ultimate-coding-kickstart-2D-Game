package com.sonicscholar.simpleBoardGame.mock;

import com.sonicscholar.simpleBoardGame.Board;
import com.sonicscholar.simpleBoardGame.GameAction;

/**
 * This class simulates a poorly implemented AI
 * player that does not return valid game actions.
 * It's used for testing that proper exceptions are
 * thrown so the programmer is made aware.
 */
public class MockPoorlyImplementedAIPlayer extends MockPlayer {
    public MockPoorlyImplementedAIPlayer(String name, char marker, Board board) {
        super(name, marker, board);
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    @Override
    public GameAction requestAction() {
        return null;
    }
}
