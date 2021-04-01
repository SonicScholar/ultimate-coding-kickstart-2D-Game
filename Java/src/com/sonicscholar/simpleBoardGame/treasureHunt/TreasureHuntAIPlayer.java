package com.sonicscholar.simpleBoardGame.treasureHunt;

import com.sonicscholar.simpleBoardGame.AbstractPlayer;
import com.sonicscholar.simpleBoardGame.Board;
import com.sonicscholar.simpleBoardGame.DefaultGameAction;
import com.sonicscholar.simpleBoardGame.GameAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreasureHuntAIPlayer extends AbstractPlayer {
    private Board _board;

    public TreasureHuntAIPlayer(String name, char marker, Board board) {
        super(name, marker);
        _board = board;
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    /**
     * Tallies up the empty spaces, and selects from them,
     * a random coordinate
     * @return GameAction for the AI Player
     */
    @Override
    public GameAction requestAction() {

        List<Coordinate> emptySpaces = new ArrayList<>();

        //tallying the empty spaces
        for (int row = 0; row < _board.getHeight(); row++){
            for (int col = 0; col < _board.getWidth(); col++) {
                boolean isEmpty = _board.isEmpty(row, col);
                if(isEmpty) {
                    Coordinate coordinate = new Coordinate(row, col);
                    emptySpaces.add(coordinate);
                }
            }
        }

        //pick an empty at random
        int numEmptySpaces = emptySpaces.size();
        Random random = new Random();
        int randomIndex = random.nextInt(numEmptySpaces);
        Coordinate desiredCoordinate = emptySpaces.get(randomIndex);
        int row = desiredCoordinate.getRow();
        int col = desiredCoordinate.getColumn();
        return new DefaultGameAction(row, col, _marker);
    }
}
