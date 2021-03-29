package dumbGame;

import com.sonicscholar.simpleBoardGame.AbstractPlayer;
import com.sonicscholar.simpleBoardGame.DefaultGameAction;
import com.sonicscholar.simpleBoardGame.GameAction;

import java.util.Scanner;

public class DumbGamePlayer extends AbstractPlayer {

    public DumbGamePlayer(String name, char marker) {
        super(name, marker);
    }

    @Override
    public boolean isComputer() {
        return false;
    }

    @Override
    public GameAction requestAction() {
        System.out.println();
        System.out.println(_name + ", select a space (1-9):");
        System.out.println("1 2 3");
        System.out.println("4 5 6");
        System.out.println("7 8 9");

        GameAction gameAction;
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();

        int row = (result - 1) / 3;
        int col = (result - 1) % 3;
        gameAction = new DefaultGameAction(row, col, _marker);

        return gameAction;
    }
}
