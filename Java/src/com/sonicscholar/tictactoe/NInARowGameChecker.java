package com.sonicscholar.tictactoe;

import com.sonicscholar.simpleBoardGame.GameChecker;
import com.sonicscholar.simpleBoardGame.GameEngine;
import com.sonicscholar.simpleBoardGame.Player;

import java.util.ArrayList;

public class NInARowGameChecker implements GameChecker {
    GameEngine _gameEngine;
    /**
     * You need N in a row to win
     */
    public final int _numToWin;

    /**
     * List of vectors (directions) to check when doing the N-in-a-row check.
     * The 1st value in the tuple represents the change in the row.
     * The 2nd value in the tuple represents the change in the column.
     * For example, if _numToWin == 4, the vector is (1,0) and we're checking
     * space (0,0)... the following 4 spaces on the board will be checked to
     * see if the markers are identical.
     *  1: (0,0) row 0, col 0
     *  2: (1,0) row 1, col 0
     *  3: (2,0) row 2, col 0
     *  4: (3,0)) row 3, col 0
     *
     *  i.e.
     *  vector of (1,0) checks a column for N consecutive markers
     *  vector of (0,1) checks a row for N consecutive markers
     *  vector of (1,1) checks diagonally down-right for N consecutive markers.
     *  vector of (1,-1) checks diagonally down-left for N consecutive markers.
     */
    private final ArrayList<Tuple<Integer,Integer>> _directionalVectors;

    public NInARowGameChecker(int numToWin) {
        _numToWin = numToWin;

        //initialize list of tuples for directions to check
        //initial capacity 4 because vectors (right, down-right, down, down-left)
        _directionalVectors = new ArrayList<>(4);
        _directionalVectors.add(new Tuple<>(0, 1));  //right
        _directionalVectors.add(new Tuple<>(1,1));   //down-right
        _directionalVectors.add(new Tuple<>(1,0));   //down
        _directionalVectors.add(new Tuple<>(1, -1)); //down-left
    }

    @Override
    public GameEngine getGameEngine() {
        return _gameEngine;
    }

    @Override
    public void setGameEngine(GameEngine gameEngine) {
        _gameEngine = gameEngine;
    }

    /**
     * Checks to see if a given space on the board is connected to N
     * consecutive markers in the right, right-down, down, and down-left vectors.
     * @param row the row of the first space to check
     * @param col the column of the first space to check
     * @return Character matching player marker if the current space is connected
     * to N-in-a-row, null otherwise.
     */
    private Character checkSpaceForNConsecutive(int row, int col) {
        var board = _gameEngine.getBoard();

        //if the space we're checking isn't valid or is empty, it's
        //obviously not connected to N-in-a-row in ANY vector
        if(!board.isValidSpace(row, col) || board.isEmpty(row, col))
            return null;

        //the marker on the space to check. N consecutive markers along
        //the current vector have to match.
        char testMarker = board.getMarkerAtPosition(row, col);

        //check each directional vector
        for (var vector : _directionalVectors) {
            int rowDelta = vector.value1; //the amount to change the row by
            int colDelta = vector.value2; //the amount to change the column by

            //start at i=1 because we already checked the validity of the
            //first space above.
            for (int i = 1; i < _numToWin; i++) {
                int rowToCheck = row + (i * rowDelta);
                int columnToCheck = col + (i * colDelta);

                if(!board.isValidSpace(rowToCheck, columnToCheck) ||
                        board.isEmpty(rowToCheck, columnToCheck))
                    break;

                char currentMarker = board.getMarkerAtPosition(rowToCheck, columnToCheck);

                //this space + current vector does not have N in a row
                //break out of this loop and check the next vector
                if(testMarker != currentMarker)
                    break;

                //if we're on the last marker and it matches, then we found a winner!
                if(i == _numToWin - 1)
                    return testMarker;
            }
        }

        //we checked everything and didn't find a match
        return null;
    }

    @Override
    public boolean isGameOver() {

        //the game is over if any of the spaces on the board are part of
        //n-in-a-row
        var board = _gameEngine.getBoard();
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if(checkSpaceForNConsecutive(row, col) != null)
                    return true;
            }
        }
        //...or if all the spaces are filled out
        return allSpacesOccupied();
    }

    private boolean allSpacesOccupied() {
        //check all the spaces, return false if any are
        var board = _gameEngine.getBoard();
        for (int row = 0; row <board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if(board.isEmpty(row,col))
                    return false;
            }
        }
        return true;
    }

    @Override
    public Player getWinner() {

        var board = _gameEngine.getBoard();
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                Character marker = checkSpaceForNConsecutive(row, col);
                if(marker != null)
                    return getPlayer(marker);
            }
        }
        return null;
    }

    /**
     * helper function to find the player that matches the character marker
     * @param marker char that represents a player's marker
     * @return first player object from the game engine with a matching marker
     */
    private Player getPlayer(char marker) {
        for (var player: _gameEngine.getPlayers()) {
            if(player.getMarker() == marker)
                return player;
        }
        return null;
    }
}
