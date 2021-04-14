package com.sonicscholar.tictactoe;

import com.sonicscholar.simpleBoardGame.DefaultBoard;
import com.sonicscholar.simpleBoardGame.DefaultGameEngine;
import com.sonicscholar.simpleBoardGame.GameEngine;
import com.sonicscholar.simpleBoardGame.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {

        var board = new DefaultBoard(3,3);

        //set up the players
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Please Enter Player 1's name (X):");

        String playerName = scanner.nextLine();
        var player1 = new TicTacToePlayer(playerName, 'X', board);

        System.out.println("Please Enter Player 2's name (O):");
        playerName = scanner.nextLine();
        var player2 = new TicTacToePlayer(playerName, 'O', board);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        //set up turn based engine and play
        GameEngine engine = new DefaultGameEngine(players, board);
        engine.setGameChecker(new NInARowGameChecker(3));
        engine.playGame();

        var winner = engine.getGameChecker().getWinner();
        System.out.println("The winning player was: " + (winner == null ? "nobody" : winner.getName()) + "!");

        //press a enter key to exit the program
        scanner.nextLine();
    }
}
