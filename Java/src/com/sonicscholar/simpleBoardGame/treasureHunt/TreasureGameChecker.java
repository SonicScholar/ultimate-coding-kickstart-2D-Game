package com.sonicscholar.simpleBoardGame.treasureHunt;

import com.sonicscholar.simpleBoardGame.Board;
import com.sonicscholar.simpleBoardGame.GameChecker;
import com.sonicscholar.simpleBoardGame.GameEngine;
import com.sonicscholar.simpleBoardGame.Player;

import java.util.List;
import java.util.Random;

public class TreasureGameChecker implements GameChecker {
    GameEngine _gameEngine;
    Coordinate _treasureLocation;

    public TreasureGameChecker() {
    }

    private void initTreasureLocation(Board board) {
        Random random = new Random();

        //random number between 0 and board height
        int row = random.nextInt(board.getHeight());

        //random number between 0 and board width
        int col = random.nextInt(board.getWidth());

        _treasureLocation = new Coordinate(row, col);
    }

    @Override
    public GameEngine getGameEngine() {
        return _gameEngine;
    }

    @Override
    public void setGameEngine(GameEngine gameEngine) {
        _gameEngine = gameEngine;
        initTreasureLocation(_gameEngine.getBoard());
    }

    /**
     * Game is over when a marker is on the treasure location
     * @return true if game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        int treasureRow = _treasureLocation.getRow();
        int treasureCol = _treasureLocation.getColumn();
        Board board = _gameEngine.getBoard();
        return !board.isEmpty(treasureRow, treasureCol);
    }

    @Override
    public Player getWinner() {
        if (!isGameOver())
            throw new RuntimeException("Game must be over before getting the winner.");

        int treasureRow = _treasureLocation.getRow();
        int treasureCol = _treasureLocation.getColumn();

        Board board = _gameEngine.getBoard();
        char winnerMarker = board.getMarkerAtPosition(treasureRow, treasureCol);

        List<Player> players = _gameEngine.getPlayers();
        for (Player p : players) {
            if(p.getMarker() == winnerMarker)
                return p;
        }

        return null;
    }

    public Coordinate getTreasureLocation(){
        return _treasureLocation;
    }
}
