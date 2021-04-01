package com.sonicscholar.simpleBoardGame.treasureHunt;

import com.sonicscholar.simpleBoardGame.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreasureHuntAIPlayerTest {

    static int numTestsForRandom = 100;

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
    void isComputer() {
        TreasureHuntAIPlayer p = new TreasureHuntAIPlayer("Wall-E", 'X', null);
        boolean isComputer = p.isComputer();
        assertTrue(isComputer);
    }

    @Test
    void requestAction() {
        GameEngine engine = getDefaultGameEngine();
        TreasureHuntAIPlayer p1 = (TreasureHuntAIPlayer)engine.getPlayers().get(0);

        for(int i = 0; i < numTestsForRandom; i++) {
            GameAction p1Action = p1.requestAction();
            assertTrue(p1Action.getRow() >= 0);
            assertTrue(p1Action.getRow() < 3);
            assertTrue(p1Action.getColumn() >= 0);
            assertTrue(p1Action.getColumn() < 3);
        }
    }

    @Test
    void requestActionForFilledRowIsValid() {
        GameEngine engine = getDefaultGameEngine();
        TreasureHuntAIPlayer p1 = (TreasureHuntAIPlayer)engine.getPlayers().get(0);

        Board board = engine.getBoard();
        board.markPosition(0,0, 'X');
        board.markPosition(0,1, 'X');
        board.markPosition(0,2, 'X');

        for(int i = 0; i < numTestsForRandom; i++) {
            GameAction p1Action = p1.requestAction();
            assertTrue(p1Action.getRow() > 0);
            assertTrue(p1Action.getRow() < 3);
            assertTrue(p1Action.getColumn() >= 0);
            assertTrue(p1Action.getColumn() < 3);
        }
    }

    @Test
    void requestActionForFilledColumnIsValid() {
        GameEngine engine = getDefaultGameEngine();
        TreasureHuntAIPlayer p1 = (TreasureHuntAIPlayer)engine.getPlayers().get(0);

        Board board = engine.getBoard();
        board.markPosition(0,0, 'X');
        board.markPosition(1,0, 'X');
        board.markPosition(2,0, 'X');

        for(int i = 0; i < numTestsForRandom; i++) {
            GameAction p1Action = p1.requestAction();
            assertTrue(p1Action.getRow() >= 0);
            assertTrue(p1Action.getRow() < 3);
            assertTrue(p1Action.getColumn() > 0);
            assertTrue(p1Action.getColumn() < 3);
        }
    }
}