package com.sonicscholar.simpleBoardGame;

import java.util.List;

@SuppressWarnings("unused")
public interface GameEngine {
    GameChecker getGameChecker();
    void setGameChecker(GameChecker gameChecker);

    Board getBoard();

    List<Player> getPlayers();

    void resetGame();
    void playGame();

    Player getNextTurnPlayer();

    boolean isValidGameAction(GameAction gameAction);
    void doTurn();
}
