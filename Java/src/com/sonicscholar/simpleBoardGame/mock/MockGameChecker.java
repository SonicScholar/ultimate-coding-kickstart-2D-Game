package com.sonicscholar.simpleBoardGame.mock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sonicscholar.simpleBoardGame.*;

/**
 * This is a dummy, or mock, class whose sole purpose is to implement
 * a "dumb" version of a 2d game so that the interface can be tested
 * in conjunction with other classes that depend on this interface.
 *
 * The rules are that this game is over when 100% of the spaces are
 * filled up. The winner is the player who has the most spaces
 */
public class MockGameChecker implements GameChecker {

    private GameEngine _gameEngine;
    public MockGameChecker()
    {
        _gameEngine = null;
    }

    @Override
    public GameEngine getGameEngine() {
        return _gameEngine;
    }

    @Override
    public void setGameEngine(GameEngine gameEngine) {
        _gameEngine = gameEngine;
    }

    @Override
    public boolean isGameOver() {
        //game is over when all spaces filled up
        Board board = _gameEngine.getBoard();
        for(int row=0; row< board.getHeight(); row++){
            for (int col=0; col<board.getWidth(); col++){
                if(board.isEmpty(row, col))
                    return false;
            }
        }
        return true;
    }

    @Override
    public Player getWinner() {

        //lookup table of players and marker, for quick reference
        //also lookup table of player and marker count
        List<Player> players = _gameEngine.getPlayers();
        Map<Character, Player> characterPlayerMap = new HashMap<>();
        Map<Player, Integer> playerMarkerCountMap = new HashMap<>();
        for (Player p : players ) {
            characterPlayerMap.put(p.getMarker(), p);
            playerMarkerCountMap.put(p, 0);
        }

        //count up all the markers on the board
        Board board = _gameEngine.getBoard();
        for(int row=0; row< board.getHeight(); row++){
            for (int col=0; col<board.getWidth(); col++){
                if(!board.isEmpty(row, col)){
                    //get the player who belongs to that marker
                    char marker = board.getMarkerAtPosition(row, col);
                    Player p = characterPlayerMap.get(marker);

                    //increment the count for that player
                    int count = playerMarkerCountMap.get(p);
                    playerMarkerCountMap.put(p, count +1);
                }
            }
        }

        //see which one had the max
        int max = -1;
        Player winner = null;
        for(Player p : players) {
            int markerCount = playerMarkerCountMap.get(p);
            if(markerCount > max) {
                max = markerCount;
                winner = p;
            } else if (markerCount == max) {
                //no winner in case of a tie (equal number of squares)
                winner = null;
            }
        }
        return winner;
    }
}
