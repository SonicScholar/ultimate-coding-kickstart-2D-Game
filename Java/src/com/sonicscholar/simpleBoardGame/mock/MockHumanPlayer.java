package com.sonicscholar.simpleBoardGame.mock;

import com.sonicscholar.simpleBoardGame.Board;
import com.sonicscholar.simpleBoardGame.GameAction;

/**
 * Mock Human player simulates human error by returning invalid actions
 * when requested for the first few times, eventually resulting in
 * providing a valid game action as defined by its parent class.
 */
public class MockHumanPlayer extends MockPlayer {

    int _counter = 0;

    public MockHumanPlayer(String name, char marker, Board board) {
        super(name, marker, board);
    }

    /**
     * It's a computer pretending to be a human :)
     * @return false
     */
    @Override
    public boolean isComputer() {
        return false;
    }

    /**
     * Simulates human error by returning invalid GameActions. Every 3rd
     * requestAction() results in a valid game move in the next available
     * space on the board
     * @return GameAction that is either valid at time of request or null.
     */
    @Override
    public GameAction requestAction() {
        _counter++;
        //returns valid game actions every 3rd request
        boolean returnValidAction = _counter % 3 == 0;

        if(returnValidAction)
            return super.requestAction();
        return null;
    }
}
