package com.sonicscholar.tictactoe;

import com.sonicscholar.simpleBoardGame.GameChecker;
import com.sonicscholar.simpleBoardGame.GameEngine;
import com.sonicscholar.simpleBoardGame.Player;

public class NInARowGameChecker implements GameChecker {
    GameEngine _gameEngine;
    /**
     * You need 3 in a row to win
     */
    public final int _numToWin;

    public NInARowGameChecker(int numToWin) {
        _numToWin = numToWin;
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
     * On any size board, check if there are 3 consecutive markers
     * in a given row
     * @return character that belongs to a string of 3 in a row, or
     * a null '\0' character if no match
     */
    public char check3InARow(){
        var board = _gameEngine.getBoard();
        //temp variable to hold the marker to test for 3 in a row
        char testMarker = '\0';

        //check each row on the board
        for(int row=0; row < board.getHeight(); row++) {

            //check each column for N in a row until the column is greater than
            // (board width) - (numToWin)
            //e.g. Admittedly over achieving here, but if board is 4x4 and numToWin
            //is 3 we would check columns [0][1][2] and also columns [1][2][3]
            for(int col = 0; col <= board.getWidth() - _numToWin; col++){

                //check for `numToWin` in a row... this could be 3 for tictactoe,
                //or 4 in the case of a game like Connect 4
                for(int i = 0; i < _numToWin; i++){
                    //if the current space is empty, break out
                    if(board.isEmpty(row, col+i))
                        break;

                    char currentMarker = board.getMarkerAtPosition(row, col + i);

                    //if this is the first space we're testing, set the testMarker
                    //to whatever the marker is on this space
                    if(i==0) {
                        testMarker = currentMarker;
                        continue;
                    }
                    //if the current space we're testing doesn't match the test marker
                    //break out of this loop entirely.
                    if(currentMarker != testMarker)
                        break;

                    //if we're on the last space to check, and it matches the test marker
                    //that should mean we found N in a row!
                    if(i == _numToWin -1)
                        return testMarker;
                }
            }
        }
        return '\0';
    }

    private char check3InAColumn() {
        var board = _gameEngine.getBoard();
        //temp variable to hold the marker to test for 3 in a row
        char testMarker = '\0';

        //check each column on the board
        for(int col=0; col < board.getWidth(); col++) {

            //check each row for N in a column until the row is greater than
            // (board height) - (numToWin)
            //e.g. Admittedly over achieving here, but if board is 4x4 and numToWin
            //is 3 we would check rows [0][1][2] and also rows [1][2][3]
            for(int row = 0; row <= board.getHeight() - _numToWin; row++){

                //check for `numToWin` in a column... this could be 3 for tictactoe,
                //or 4 in the case of a game like Connect 4
                for(int i = 0; i < _numToWin; i++){
                    //if the current space is empty, break out
                    if(board.isEmpty(row +i, col))
                        break;

                    char currentMarker = board.getMarkerAtPosition(row+i, col);

                    //if this is the first space we're testing, set the testMarker
                    //to whatever the marker is on this space
                    if(i==0) {
                        testMarker = currentMarker;
                        continue;
                    }
                    //if the current space we're testing doesn't match the test marker
                    //break out of this loop entirely.
                    if(currentMarker != testMarker)
                        break;

                    //if we're on the last space to check, and it matches the test marker
                    //that should mean we found N in a column!
                    if(i == _numToWin -1)
                        return testMarker;
                }
            }
        }
        return '\0';
    }

    private char check3InADiagonal() {
        //check diagonals that slope uphill and downhill directions
        //lets say we start with row zero and check all the spaces to
        //see if they even have `numToWin` in a diagonal either down
        //and to the left, or down and to the right.

        //e.g. for a 3x3 board, (0,0) would be a candidate for a diagonal
        //because it has two more spaces down and to the right.
        //(0,2) would be a candidate for a diagonal because it has two
        //spaces down and to the left.

        var board = _gameEngine.getBoard();
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                boolean isDiagonalCandidateLeft = isDiagonalCandidateLeft(row, col);
                boolean isDiagonalCandidateRight = isDiagonalCandidateRight(row, col);

                //if there aren't enough spaces down and to the right, or down and
                //to the left, then checking this space to see if it's part of a
                //N in a row doesn't make sense.
                if(!isDiagonalCandidateLeft && !isDiagonalCandidateRight)
                    continue;

                //when we find a spot that does have at least N spaces down and to the
                //left or down and to the right. check those spaces to see if all the
                //markers match up.

                char testMarker = '\0';
                if(isDiagonalCandidateLeft){
                    //check numToWinSpaces down and to the left
                    for(int i = 0; i < _numToWin; i++){
                        //if the current space is empty, break out
                        if(board.isEmpty(row +i, col-i))
                            break;

                        char currentMarker = board.getMarkerAtPosition(row+i, col-i);

                        //if this is the first space we're testing, set the testMarker
                        //to whatever the marker is on this space
                        if(i==0) {
                            testMarker = currentMarker;
                            continue;
                        }
                        //if the current space we're testing doesn't match the test marker
                        //break out of this loop entirely.
                        if(currentMarker != testMarker)
                            break;

                        //if we're on the last space to check, and it matches the test marker
                        //that should mean we found N in a column!
                        if(i == _numToWin -1)
                            return testMarker;
                    }
                } else //noinspection ConstantConditions
                    if(isDiagonalCandidateRight){
                    for(int i = 0; i < _numToWin; i++){
                        //if the current space is empty, break out
                        if(board.isEmpty(row +i, col+i))
                            break;

                        char currentMarker = board.getMarkerAtPosition(row+i, col+i);

                        //if this is the first space we're testing, set the testMarker
                        //to whatever the marker is on this space
                        if(i==0) {
                            testMarker = currentMarker;
                            continue;
                        }
                        //if the current space we're testing doesn't match the test marker
                        //break out of this loop entirely.
                        if(currentMarker != testMarker)
                            break;

                        //if we're on the last space to check, and it matches the test marker
                        //that should mean we found N in a column!
                        if(i == _numToWin -1)
                            return testMarker;
                    }
                }
            }
        }
        return '\0';
    }

    /**
     * Checks to see if a space has `numToWin` spaces down and to the left
     * @param row input row position
     * @param col input column position
     * @return true if this space has `numToWin` spaces down and to the left
     */
    private boolean isDiagonalCandidateLeft(int row, int col) {
        //figure out where the end of the diagonal to test is, then check
        //to see if it's a valid board space.

        row += (_numToWin -1); //down
        col -= (_numToWin -1); //to the left

        var board = _gameEngine.getBoard();
        return board.isValidSpace(row, col);
    }

    /**
     * Checks to see if a space has `numToWin` spaces down and to the left
     * @param row input row position
     * @param col input column position
     * @return true if this space has `numToWin` spaces down and to the left
     */
    private boolean isDiagonalCandidateRight(int row, int col) {
        //figure out where the end of the diagonal to test is, then check
        //to see if it's a valid board space.

        row += (_numToWin -1); //down
        col += (_numToWin -1); //to the right

        var board = _gameEngine.getBoard();
        return board.isValidSpace(row, col);
    }


    @Override
    public boolean isGameOver() {
        var board = _gameEngine.getBoard();
        if(check3InARow() != '\0')
            return true;

        //check all the columns
        if(check3InAColumn() != '\0')
            return true;

        //check all the diagonals
        if(check3InADiagonal() != '\0')
            return true;

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
        char marker;
        marker = check3InARow();
        if(marker != '\0')
            return getPlayer(marker);

        marker = check3InAColumn();
        if(marker != '\0')
            return getPlayer(marker);

        marker = check3InADiagonal();
        if(marker != '\0')
            return getPlayer(marker);
        return null;
    }

    private Player getPlayer(char marker) {
        for (var player: _gameEngine.getPlayers()) {
            if(player.getMarker() == marker)
                return player;
        }
        return null;
    }
}
