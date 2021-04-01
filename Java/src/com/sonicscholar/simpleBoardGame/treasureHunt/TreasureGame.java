package com.sonicscholar.simpleBoardGame.treasureHunt;

import com.sonicscholar.simpleBoardGame.Board;
import com.sonicscholar.simpleBoardGame.DefaultBoard;
import com.sonicscholar.simpleBoardGame.DefaultGameEngine;
import com.sonicscholar.simpleBoardGame.Player;

import java.util.ArrayList;
import java.util.List;

public class TreasureGame {
    public static void main(String[] args) {

        Board board = new DefaultBoard(3, 3);

        //TreasureHuntAIPlayer p1 = new TreasureHuntAIPlayer("Wall-E", 'X', board);
        TreasureHuntAIPlayer p2 = new TreasureHuntAIPlayer("Chappie", 'O', board);

        TreasureHuntPlayer p1 = new TreasureHuntPlayer("Collin", 'X', board);
        //TreasureHuntPlayer p2 = new TreasureHuntPlayer("Mark", 'O', board);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        DefaultGameEngine engine = new DefaultGameEngine(players, board);
        TreasureGameChecker gameChecker = new TreasureGameChecker();
        engine.setGameChecker(gameChecker);

        engine.playGame();

        Player winner = engine.getGameChecker().getWinner();

        String winnerName;
        if(winner == null)
            winnerName = "Nobody";
        else
            winnerName = winner.getName();

        System.out.println(winnerName + " was the winner!");

    }
}
