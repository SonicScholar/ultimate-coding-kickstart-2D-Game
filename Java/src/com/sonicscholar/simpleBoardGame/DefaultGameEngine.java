package com.sonicscholar.simpleBoardGame;

import java.util.List;

/**
 * This is a simple com.sonicscholar.SimpleBoardGame.GameEngine that takes N players and makes each player
 * take a turn in order until the game is over as specified by the com.sonicscholar.SimpleBoardGame.GameChecker.
 */
@SuppressWarnings("CanBeFinal")
public class DefaultGameEngine implements GameEngine {

    protected Board _board;
    protected List<Player> _players;
    private int _currentTurn = 0;
    GameChecker _gameChecker;

    public DefaultGameEngine(List<Player> players, Board board) {
        _players = players;
        _board = board;
    }

    @Override
    public GameChecker getGameChecker() {
        return _gameChecker;
    }

    @Override
    public void setGameChecker(GameChecker gameChecker) {
        _gameChecker = gameChecker;
        _gameChecker.setGameEngine(this);
    }

    @Override
    public Board getBoard() {
        return _board;
    }

    @Override
    public List<Player> getPlayers() {
        return _players;
    }

    @Override
    public void resetGame() {
        _board.resetBoard();
        _currentTurn = 0;
    }

    @Override
    public void playGame() {
        do {
            doTurn();
        }while(!_gameChecker.isGameOver());
    }

    @Override
    public Player getNextTurnPlayer() {
        int numPlayers = _players.size();
        return _players.get(_currentTurn % numPlayers);
    }

    @Override
    public boolean isValidGameAction(GameAction gameAction) {
        //default engine behavior is that all game moves on empty spaces are
        //allowed.
        if(gameAction == null)
            return false;

        int row = gameAction.getRow();
        int column = gameAction.getColumn();

        //Game moves outside index of board dimensions are invalid.
        if(row < 0 || row >= _board.getHeight())
            return false;
        else if(column < 0 || column >= _board.getWidth())
            return false;

        return _board.isEmpty(row, column);
    }

    @Override
    public void doTurn() {
        //get the next player, and increment
        Player player = getNextTurnPlayer();
        _currentTurn++;

        GameAction action;
        boolean isValid;
        //Considering what happens here if player keeps requesting
        //invalid actions. Not a big deal if it's a human player
        //but an AI player may cause infinite loops.

        //One solution is to add a isComputer() boolean to Player
        //interface, and throw an exception if an AI player screws up.
        do {
            action = player.requestAction();
            isValid = isValidGameAction(action);
            if(player.isComputer() && !isValid)
                throw new RuntimeException("AI player requested invalid action");
        } while (!isValid);

        //action is valid at this point
        _board.markPosition(action.getRow(), action.getColumn(), action.getMarker());
    }
}
