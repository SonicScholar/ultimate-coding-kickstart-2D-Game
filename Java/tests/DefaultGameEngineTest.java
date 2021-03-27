import com.sonicscholar.simpleBoardGame.*;
import com.sonicscholar.simpleBoardGame.mock.MockGameChecker;
import com.sonicscholar.simpleBoardGame.mock.MockHumanPlayer;
import com.sonicscholar.simpleBoardGame.mock.MockPlayer;
import com.sonicscholar.simpleBoardGame.mock.MockPoorlyImplementedAIPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DefaultGameEngineTest {

    static GameEngine getDefaultGameEngine(int boardSize){
        Board board = new DefaultBoard(boardSize, boardSize);

        //create 2 players
        List<Player> players = new ArrayList<>();
        Player player1 = new MockHumanPlayer("Bob", 'X', board);
        Player player2 = new MockPlayer("Sue", 'O', board);
        players.add(player1);
        players.add(player2);

        DefaultGameEngine engine = new DefaultGameEngine(players, board);
        engine.setGameChecker(new MockGameChecker());
        return engine;
    }

    @Test
    void getAndSetGameChecker() {
        GameChecker gameChecker = new MockGameChecker();
        DefaultGameEngine engine = new DefaultGameEngine(null, null);
        assertNull(engine.getGameChecker());
        engine.setGameChecker(gameChecker);
        assertEquals(gameChecker, engine.getGameChecker());
    }

    @Test
    void getBoard() {
        GameEngine engine = getDefaultGameEngine(2);
        Board board = engine.getBoard();
        assertEquals(2, board.getHeight());
        assertEquals(2, board.getWidth());

        engine = getDefaultGameEngine(3);
        board = engine.getBoard();
        assertEquals(3, board.getHeight());
        assertEquals(3, board.getWidth());
    }

    @Test
    void getPlayers() {
        GameEngine engine = getDefaultGameEngine(1);
        List<Player> players = engine.getPlayers();
        assertEquals(2, players.size());
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        assertEquals("Bob", player1.getName());
        assertEquals("Sue", player2.getName());
    }

    @Test
    void resetGame() {
        //set up the game, do 1 turn, reset the Game, and verify initial state
        GameEngine engine = getDefaultGameEngine(2);
        Board board = engine.getBoard();
        List<Player> players = engine.getPlayers();
        assertEquals(2, players.size());
        Player player1 = players.get(0);

        engine.doTurn();
        assertFalse(board.isEmpty(0,0));

        engine.resetGame();
        //board should be clear
        assertTrue(board.isEmpty(0,0));
        //player turn should be reset
        //game should not be finished
        assertEquals(player1, engine.getNextTurnPlayer());
        assertFalse(engine.getGameChecker().isGameOver());
    }

    @Test
    void playGame2x2() {
        GameEngine engine = getDefaultGameEngine(2);
        engine.playGame();
        GameChecker gameChecker = engine.getGameChecker();
        assertTrue(gameChecker.isGameOver());
        assertNull(gameChecker.getWinner());
    }

    @Test
    void playGame3x3() {
        GameEngine engine = getDefaultGameEngine(3);
        engine.playGame();
        GameChecker gameChecker = engine.getGameChecker();
        assertTrue(gameChecker.isGameOver());

        //player 1 should be the winner since he went first and filled up
        //5 out of the 9 spaces.
        Player player1 = engine.getPlayers().get(0);
        assertEquals(player1, gameChecker.getWinner());
    }

    @Test
    void getNextTurnPlayer() {
        GameEngine engine = getDefaultGameEngine(2);
        List<Player> players = engine.getPlayers();
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Player nextPlayer = engine.getNextTurnPlayer();
        assertEquals(player1, nextPlayer);

        engine.doTurn();
        nextPlayer = engine.getNextTurnPlayer();
        assertEquals(player2, nextPlayer);
    }

    @Test
    void gameActionOnEmptySpaceIsValid() {
        GameEngine engine = getDefaultGameEngine(2);
        List<Player> players = engine.getPlayers();
        Player player2 = players.get(1);
        assertTrue(player2 instanceof MockPlayer);
        assertFalse(player2 instanceof MockHumanPlayer);
        GameAction action = player2.requestAction();

        boolean isValid = engine.isValidGameAction(action);
        assertTrue(isValid);
    }

    @Test
    void gameActionOnOccupiedSpaceIsNotValid() {
        GameEngine engine = getDefaultGameEngine(2);
        List<Player> players = engine.getPlayers();
        Player player2 = players.get(1);
        GameAction action = player2.requestAction();

        //should request an action from player 1 and do that action
        engine.doTurn();

        //the original requested action should no longer be valid
        boolean isValid = engine.isValidGameAction(action);
        assertFalse(isValid);
    }
    @Test
    void gameActionOnOutOfBoundsRowAndColumnIsNotValid() {
        GameEngine engine = getDefaultGameEngine(2);
        GameAction action1 = new DefaultGameAction(-1,0,'X');
        GameAction action2 = new DefaultGameAction(2,0,'X');
        GameAction action3 = new DefaultGameAction(0, -1, 'X');
        GameAction action4 = new DefaultGameAction(0, 2, 'X');

        //the original requested action should no longer be valid
        boolean isValid = engine.isValidGameAction(action1);
        assertFalse(isValid);

        isValid = engine.isValidGameAction(action2);
        assertFalse(isValid);
        isValid = engine.isValidGameAction(action3);
        assertFalse(isValid);
        isValid = engine.isValidGameAction(action4);
        assertFalse(isValid);
    }

    @Test
    void doTurn() {
        GameEngine engine = getDefaultGameEngine(2);
        Board board = engine.getBoard();
        List<Player> players = engine.getPlayers();
        Player player2 = players.get(1);

        assertTrue(board.isEmpty(0,0));
        //should request an action from player 1 and do that action at 0,0
        engine.doTurn();
        assertFalse(board.isEmpty(0,0));

        Player nextPlayer = engine.getNextTurnPlayer();
        assertEquals(player2, nextPlayer);
    }

    @Test
    void PoorlyImplementedAIPlayerCausesException(){
        GameEngine engine = getDefaultGameEngine(2);
        List<Player> players = engine.getPlayers();
        //replace a player with a poorly implemented AI
        Player badAIPlayer = new MockPoorlyImplementedAIPlayer("Collin", 'T', null);
        players.set(0, badAIPlayer);

        assertThrows(RuntimeException.class, engine::doTurn);
    }
}