package com.sonicscholar.simpleBoardGame.treasureHunt;

import com.sonicscholar.simpleBoardGame.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreasureGameCheckerTest {

    static GameEngine getDefaultGameEngine(){
        Board board = new DefaultBoard(3, 3);

        TreasureHuntAIPlayer p1 = new TreasureHuntAIPlayer("Wall-E", 'X', board);
        TreasureHuntAIPlayer p2 = new TreasureHuntAIPlayer("Chappie", 'O', board);
        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        return new DefaultGameEngine(players, board);
    }

    @Test
    void testGameEngineIsNullByDefault() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = gameChecker.getGameEngine();
        assertNull(engine);
    }

    @Test
    void testGameEngineIsSetCorrectly() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine expected = getDefaultGameEngine();
        gameChecker.setGameEngine(expected);

        GameEngine actual = gameChecker.getGameEngine();
        assertEquals(expected, actual);
    }

    @Test
    void testDefaultGameCheckerHasNullTreasureLocationByDefault() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        Coordinate treasureLocation = gameChecker.getTreasureLocation();
        assertNull(treasureLocation);
    }

    @Test
    void testValidTreasureLocationAfterGameEngineSet() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = getDefaultGameEngine();
        gameChecker.setGameEngine(engine);

        Coordinate treasureLocation = gameChecker.getTreasureLocation();
        assertNotNull(treasureLocation);

        int treasureRow = treasureLocation.getRow();
        int treasureColumn = treasureLocation.getColumn();

        Board board = engine.getBoard();

        assertTrue(treasureRow >= 0);
        assertTrue(treasureRow < board.getHeight());

        assertTrue(treasureColumn >= 0);
        assertTrue(treasureColumn < board.getWidth());
    }

    @Test
    void testGameIsNotOverWhenBoardIsEmpty() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = getDefaultGameEngine();
        gameChecker.setGameEngine(engine);

        boolean isGameOver = gameChecker.isGameOver();
        assertFalse(isGameOver);
    }

    @Test
    void testGameIsNotOverWhenMarkersOnEverythingButTreasureLocation() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = getDefaultGameEngine();
        gameChecker.setGameEngine(engine);

        Coordinate treasureLocation = gameChecker.getTreasureLocation();
        Board board = engine.getBoard();

        //mark everything on the board except for the treasure location
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if(treasureLocation.getRow() == row && treasureLocation.getColumn() == col)
                    continue;
                board.markPosition(row, col, 'X');
            }
        }

        boolean isGameOver = gameChecker.isGameOver();
        assertFalse(isGameOver);
    }

    @Test
    void testIsGameOverWhenMarkerOnTreasureSpot() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = getDefaultGameEngine();
        gameChecker.setGameEngine(engine);

        Coordinate treasureLocation = gameChecker.getTreasureLocation();
        Board board = engine.getBoard();

        board.markPosition(treasureLocation.getRow(), treasureLocation.getColumn(), 'X');

        boolean isGameOver = gameChecker.isGameOver();
        assertTrue(isGameOver);
    }

    @Test
    void getWinnerPlayer1() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = getDefaultGameEngine();
        gameChecker.setGameEngine(engine);

        Coordinate treasureLocation = gameChecker.getTreasureLocation();
        Board board = engine.getBoard();

        Player p1 = engine.getPlayers().get(0);
        board.markPosition(treasureLocation.getRow(), treasureLocation.getColumn(), p1.getMarker());

        Player winner = gameChecker.getWinner();
        assertEquals(p1, winner);

    }

    @Test
    void getWinnerPlayer2() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = getDefaultGameEngine();
        gameChecker.setGameEngine(engine);

        Coordinate treasureLocation = gameChecker.getTreasureLocation();
        Board board = engine.getBoard();

        Player p2 = engine.getPlayers().get(1);
        board.markPosition(treasureLocation.getRow(), treasureLocation.getColumn(), p2.getMarker());

        Player winner = gameChecker.getWinner();
        assertEquals(p2, winner);
    }

    @Test
    void getWinnerIsNullWhenMarkerDoesntBelongToAPlayer() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = getDefaultGameEngine();
        gameChecker.setGameEngine(engine);

        Coordinate treasureLocation = gameChecker.getTreasureLocation();
        Board board = engine.getBoard();

        board.markPosition(treasureLocation.getRow(), treasureLocation.getColumn(), 'A');

        boolean isGameOver = gameChecker.isGameOver();
        assertTrue(isGameOver);

        Player winner = gameChecker.getWinner();
        assertNull(winner);
    }

    @Test
    void getWinnerThrowsExceptionWhenGameIsNotOver() {
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        GameEngine engine = getDefaultGameEngine();
        gameChecker.setGameEngine(engine);

        //RuntimeException ex = assertThrows(RuntimeException.class, gameChecker::getWinner );

        RuntimeException ex = assertThrows(RuntimeException.class, () -> gameChecker.getWinner());
        String expectedErrorMessage = "Game must be over before getting the winner.";
        assertEquals(expectedErrorMessage, ex.getMessage());
    }
}