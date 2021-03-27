import com.sonicscholar.simpleBoardGame.*;
import com.sonicscholar.simpleBoardGame.mock.MockGameChecker;
import com.sonicscholar.simpleBoardGame.mock.MockPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MockGameCheckerTest {

    static final int testBoardSize = 2;
    static GameEngine getDefaultGameEngine(){
        Board board = new DefaultBoard(MockGameCheckerTest.testBoardSize, MockGameCheckerTest.testBoardSize);

        //create 2 players
        List<Player> players = new ArrayList<>();
        Player player1 = new MockPlayer("Bob", 'X', board);
        Player player2 = new MockPlayer("Sue", 'O', board);
        players.add(player1);
        players.add(player2);


        return new DefaultGameEngine(players, board);
    }

    @Test
    void getGameEngineIsNullByDefault() {
        MockGameChecker gameChecker = new MockGameChecker();
        assertNull(gameChecker.getGameEngine());
    }

    @Test
    void setGameEngine() {
        GameEngine gameEngine = getDefaultGameEngine();
        MockGameChecker gameChecker = new MockGameChecker();
        gameChecker.setGameEngine(gameEngine);

        Object actual = gameChecker.getGameEngine();
        assertNotNull(actual);
        assertEquals(gameEngine, actual);
    }

    @Test
    void isGameOverOnDefaultBoardIsFalse() {
        GameEngine gameEngine = getDefaultGameEngine();
        MockGameChecker gameChecker = new MockGameChecker();
        gameChecker.setGameEngine(gameEngine);

        boolean isGameOver = gameChecker.isGameOver();
        assertFalse(isGameOver);
    }

    @Test
    void isGameOverOnPartiallyFilledBoardIsFalse() {
        GameEngine gameEngine = getDefaultGameEngine();
        MockGameChecker gameChecker = new MockGameChecker();
        gameChecker.setGameEngine(gameEngine);
        Player player1 = gameEngine.getPlayers().get(0);
        char player1Marker = player1.getMarker();

        Board board = gameEngine.getBoard();
        board.markPosition(0,0,player1Marker);

        boolean isGameOver = gameChecker.isGameOver();
        assertFalse(isGameOver);
    }

    @Test
    void isGameOverOnFilledUpBoardIsTrue() {
        GameEngine gameEngine = getDefaultGameEngine();
        MockGameChecker gameChecker = new MockGameChecker();
        gameChecker.setGameEngine(gameEngine);
        Player player1 = gameEngine.getPlayers().get(0);
        char player1Marker = player1.getMarker();

        Board board = gameEngine.getBoard();
        board.markPosition(0,0,player1Marker);
        board.markPosition(0,1,player1Marker);
        board.markPosition(1,0,player1Marker);
        board.markPosition(1,1,player1Marker);

        boolean isGameOver = gameChecker.isGameOver();
        assertTrue(isGameOver);
    }

    @Test
    void getWinner() {
        GameEngine gameEngine = getDefaultGameEngine();
        MockGameChecker gameChecker = new MockGameChecker();
        gameChecker.setGameEngine(gameEngine);
        Player player2 = gameEngine.getPlayers().get(1);
        char player2Marker = player2.getMarker();

        Board board = gameEngine.getBoard();
        board.markPosition(0,0,player2Marker);
        board.markPosition(0,1,player2Marker);
        board.markPosition(1,0,player2Marker);
        board.markPosition(1,1,player2Marker);

        boolean isGameOver = gameChecker.isGameOver();
        assertTrue(isGameOver);
        Player winner = gameChecker.getWinner();
        assertNotNull(winner);
        assertEquals(player2, winner);
    }

    @Test
    void getWinnerOfTieGameIsNull() {
        GameEngine gameEngine = getDefaultGameEngine();
        MockGameChecker gameChecker = new MockGameChecker();
        gameChecker.setGameEngine(gameEngine);

        Player player1 = gameEngine.getPlayers().get(0);
        char player1Marker = player1.getMarker();
        Player player2 = gameEngine.getPlayers().get(1);
        char player2Marker = player2.getMarker();

        Board board = gameEngine.getBoard();
        board.markPosition(0,0,player1Marker);
        board.markPosition(0,1,player2Marker);
        board.markPosition(1,0,player1Marker);
        board.markPosition(1,1,player2Marker);

        boolean isGameOver = gameChecker.isGameOver();
        assertTrue(isGameOver);
        Player winner = gameChecker.getWinner();
        assertNull(winner);
        assertNotEquals(null, player1);
        assertNotEquals(null, player2);
    }
}