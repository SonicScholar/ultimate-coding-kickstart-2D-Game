package com.sonicscholar.tictactoe;

import com.sonicscholar.simpleBoardGame.*;
import com.sonicscholar.simpleBoardGame.mock.MockPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameCheckerTest {

    static final int ticTacToeBoardSize = 3;
    static final int numToWin = 3;
    static GameEngine getDefaultGameEngine(){
        Board board = new DefaultBoard(ticTacToeBoardSize, ticTacToeBoardSize);

        //create 2 players
        List<Player> players = new ArrayList<>();
        Player player1 = new MockPlayer("Bob", 'X', board);
        Player player2 = new MockPlayer("Sue", 'O', board);
        players.add(player1);
        players.add(player2);

        var gameEngine = new DefaultGameEngine(players, board);
        gameEngine.setGameChecker(new NInARowGameChecker(numToWin));
        return gameEngine;
    }

    @Test
    void testGameIsNotOverOnEmptyBoard(){
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();

        assertFalse(gameChecker.isGameOver());
    }

    @Test
    void testGameIsNotOverOnPartiallyFilledBoard(){
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        // X O X
        // X X O
        // O   O
        board.markPosition(0,0, 'X');
        board.markPosition(0, 1, 'O');
        board.markPosition(0, 2, 'X');

        board.markPosition(1,0, 'X');
        board.markPosition(1,1,'X');
        board.markPosition(1,2,'O');

        board.markPosition(2,0, 'O');
        board.markPosition(2,2,'O');

        assertFalse(gameChecker.isGameOver());
    }

    @Test
    void testGameIsOverWhenAllSpacesAreOccupied() {
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        // X O X
        // X X O
        // O X O
        board.markPosition(0,0, 'X');
        board.markPosition(0, 1, 'O');
        board.markPosition(0, 2, 'X');

        board.markPosition(1,0, 'X');
        board.markPosition(1,1,'X');
        board.markPosition(1,2,'O');

        board.markPosition(2,0, 'O');
        board.markPosition(2,1,'X');
        board.markPosition(2,2,'O');

        assertTrue(gameChecker.isGameOver());
        assertNull(gameChecker.getWinner());
    }

    @Test
    void testGameIsOverWhen3ConsecutiveInRow(){
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        // X X X
        //
        //
        board.markPosition(0,0, 'X');
        board.markPosition(0, 1, 'X');
        board.markPosition(0, 2, 'X');

        assertTrue(gameChecker.isGameOver());
    }

    @Test
    void testGameIsOverWhen3ConsecutiveInColumn(){
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        // X
        // X
        // X
        board.markPosition(0,0, 'X');
        board.markPosition(1, 0, 'X');
        board.markPosition(2, 0, 'X');

        assertTrue(gameChecker.isGameOver());
    }

    @Test
    void testGameIsOverWhen3ConsecutiveInDiagonal() {
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        // X
        //   X
        //     X
        board.markPosition(0,0, 'X');
        board.markPosition(1, 1, 'X');
        board.markPosition(2, 2, 'X');

        assertTrue(gameChecker.isGameOver());
    }

    @Test
    void testGetWinnerWhen3ConsecutiveInRow(){
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        var p1 = gameEngine.getPlayers().get(0);
        // X X X
        //
        //
        board.markPosition(0,0, p1.getMarker());
        board.markPosition(0, 1, p1.getMarker());
        board.markPosition(0, 2, p1.getMarker());

        assertTrue(gameChecker.isGameOver());
        var winner = gameChecker.getWinner();
        assertEquals(p1, winner);
    }

    @Test
    void testGetWinnerWhen3ConsecutiveInColumn(){
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        var p2 = gameEngine.getPlayers().get(1);
        // O
        // O
        // O
        board.markPosition(0,0, p2.getMarker());
        board.markPosition(1, 0, p2.getMarker());
        board.markPosition(2, 0, p2.getMarker());

        assertTrue(gameChecker.isGameOver());
        var winner = gameChecker.getWinner();
        assertEquals(p2, winner);
    }

    @Test
    void testGetWinnerWhen3ConsecutiveInDiagonal(){
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        var p1 = gameEngine.getPlayers().get(0);
        // X
        //   X
        //     X
        board.markPosition(0,0, p1.getMarker());
        board.markPosition(1, 1, p1.getMarker());
        board.markPosition(2, 2, p1.getMarker());

        assertTrue(gameChecker.isGameOver());
        var winner = gameChecker.getWinner();
        assertEquals(p1, winner);
    }

    //added this unit test during implementation of check3InADiagonal
    //because I wanted a test that checked different slope directions
    @Test
    void testGetWinnerWhen3ConsecutiveInDiagonal2(){
        var gameEngine = getDefaultGameEngine();
        var gameChecker = gameEngine.getGameChecker();
        var board = gameEngine.getBoard();

        var p1 = gameEngine.getPlayers().get(0);
        //     X
        //   X
        // X
        board.markPosition(0,2, p1.getMarker());
        board.markPosition(1, 1, p1.getMarker());
        board.markPosition(2, 0, p1.getMarker());

        assertTrue(gameChecker.isGameOver());
        var winner = gameChecker.getWinner();
        assertEquals(p1, winner);
    }
}