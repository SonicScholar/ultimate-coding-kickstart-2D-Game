package com.sonicscholar.tictactoe;

import com.sonicscholar.simpleBoardGame.AbstractPlayer;
import com.sonicscholar.simpleBoardGame.Board;
import com.sonicscholar.simpleBoardGame.DefaultGameAction;
import com.sonicscholar.simpleBoardGame.GameAction;

import java.util.Scanner;

public class TicTacToePlayer extends AbstractPlayer {

    private final Board _board;

    public TicTacToePlayer(String name, char marker, Board board) {
        super(name, marker);
        _board = board;
    }

    public static Coordinate getCoordinateFromUserInput(int userInput, int width) {
        userInput--;

        int row = userInput / width;
        int col = userInput % width;

        return new Coordinate(row, col);
    }

    @Override
    public boolean isComputer() {
        return false;
    }

    @Override
    public GameAction requestAction() {

        int totalSpaces = _board.getWidth() * _board.getHeight();
        System.out.println(_name + ", please select a space: 1-" + totalSpaces);
        for (int row = 0; row < _board.getHeight(); row++){
            for(int col = 0; col < _board.getWidth(); col++) {
                System.out.print(row* _board.getWidth() + col +1);
            }
            System.out.println();
        }


        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        Coordinate c = getCoordinateFromUserInput(userInput, _board.getWidth());
        int row = c.getRow();
        int col = c.getColumn();
        return new DefaultGameAction(row, col, _marker);
    }
}
