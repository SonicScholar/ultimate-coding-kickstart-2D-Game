package dumbGame;

import com.sonicscholar.simpleBoardGame.GameChecker;
import com.sonicscholar.simpleBoardGame.GameEngine;
import com.sonicscholar.simpleBoardGame.Player;

public class DumbGameChecker implements GameChecker {
    GameEngine _gameEngine;
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
        var board = _gameEngine.getBoard();
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (board.isEmpty(row, col))
                    return false;
            }
        }

        return true;
    }

    @Override
    public Player getWinner() {
        return null;
    }
}
