package com.sonicscholar.simpleBoardGame;

/**
 * Player represents an object that can interact
 * with a game.
 */
public interface Player {
    /**
     * Gets the name of the Player object
     * @return the name of the Player object
     */
    String getName();

    /**
     * Gets the marker of the Player object
     * @return the marker of the Player object
     */
    char getMarker();

    /**
     * Gets whether the Player object is AI
     * @return true if the Player object is AI
     */
    boolean isComputer();

    /**
     * Requests an action from the Player object.
     * The implementing Player is responsible for
     * keeping track of game state.
     * @return a GameAction of the player's desired
     * next action
     */
    GameAction requestAction();

}
