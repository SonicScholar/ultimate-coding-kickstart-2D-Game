package com.sonicscholar.simpleBoardGame;

import java.util.List;

/**
 * The GameEngine interface is responsible for keeping track
 * of all the game state, and running the game until its
 * completion.
 */
public interface GameEngine {
    /**
     * Gets the GameChecker used by this engine.
     * @return the GameChecker used by this engine
     */
    GameChecker getGameChecker();

    /**
     * Sets the GameChecker to be used by this engine
     * @param gameChecker The GameChecker to be used
     */
    void setGameChecker(GameChecker gameChecker);

    /**
     * Gets the Board used by the GameEngine
     * @return the board used by the engine
     */
    Board getBoard();

    /**
     * Gets a list of players currently registered by the engine
     * @return list of players currently registered
     */
    List<Player> getPlayers();

    /**
     * Resets the entire state of the game engine back to its
     * initial state just before playGame() is called.
     */
    void resetGame();

    /**
     * Starts the game and returns when the game is finished
     */
    void playGame();

    /**
     * Gets the player whose next turn it is.
     * @return the player whose next turn it is.
     */
    Player getNextTurnPlayer();

    /**
     * Validates whether or not a given GameAction is valid
     * for the specific game being played
     * @param gameAction the GameAction to validate
     * @return `true` if the action is valid. `false` if the
     * action is invalid.
     */
    boolean isValidGameAction(GameAction gameAction);

    /**
     * Completes one turn of the game.
     */
    void doTurn();
}
