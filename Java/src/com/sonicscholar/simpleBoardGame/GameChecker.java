package com.sonicscholar.simpleBoardGame;

/**
 * An interface for determining whether a game is over
 * and who the winner is. It is up to the implementer
 * to determine how this business logic works
 */
@SuppressWarnings("unused")
public interface GameChecker {

    /**
     * Get the com.sonicscholar.SimpleBoardGame.GameEngine object assigned to this com.sonicscholar.SimpleBoardGame.GameChecker
     * @return The com.sonicscholar.SimpleBoardGame.GameEngine object assigned to this com.sonicscholar.SimpleBoardGame.GameChecker
     */
    GameEngine getGameEngine();

    /**
     * Sets the com.sonicscholar.SimpleBoardGame.GameEngine for this com.sonicscholar.SimpleBoardGame.GameChecker to analyze
     * @param gameEngine The com.sonicscholar.SimpleBoardGame.GameEngine object to assign to this com.sonicscholar.SimpleBoardGame.GameChecker
     */
    void setGameEngine(GameEngine gameEngine);

    /**
     * Analyzes the board and determines whether or not the game is over
     * according to the rules of this game.
     * @return `true` if the game is over. `false` otherwise.
     */
    boolean isGameOver();

    /**
     * If the game is over, returns which player is the winner. If there is no
     * winner, this method should return null. If the game is over this method
     * should throw an exception indicating the game is still in progress.
     * @return The player object who won the game, or null if no winner.
     */
    Player getWinner();
}
