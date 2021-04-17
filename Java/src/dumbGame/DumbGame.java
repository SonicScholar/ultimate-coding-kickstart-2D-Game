package dumbGame;

import com.sonicscholar.simpleBoardGame.DefaultBoard;
import com.sonicscholar.simpleBoardGame.DefaultGameEngine;
import com.sonicscholar.simpleBoardGame.GameEngine;
import com.sonicscholar.simpleBoardGame.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class DumbGame {
    public static void main(String[] args) {
        DumbGamePlayer player1 = new DumbGamePlayer("Collin", 'X');
        DumbGamePlayer player2 = new DumbGamePlayer("Jim", 'O');
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        GameEngine engine = new DefaultGameEngine(players, new DefaultBoard(3, 3));
        engine.setGameChecker(new DumbGameChecker());

        engine.playGame();

        var winner = engine.getGameChecker().getWinner();
        System.out.println("The winning player was: " + (winner == null ? "nobody" : winner.getName()) + "!");

        //press a key to exit the program
        new Scanner(System.in).next();
    }
}
