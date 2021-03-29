using NUnit.Framework;
using SonicScholar.SimpleBoardGame.Framework.Test.Mock;
using System;
using System.Collections.Generic;

namespace SonicScholar.SimpleBoardGame.Framework.Test
{
    [TestFixture]
    public class GameEngineTests
    {
        static IGameEngine GetDefaultGameEngine(int boardSize)
        {
            IBoard board = new Board(boardSize, boardSize);

            //create 2 players
            IList<IPlayer> players = new List<IPlayer>();
            IPlayer player1 = new MockHumanPlayer("Bob", 'X', board);
            IPlayer player2 = new MockPlayer("Sue", 'O', board);
            players.Add(player1);
            players.Add(player2);

            GameEngine engine = new GameEngine(players, board)
            {
                GameChecker = new MockGameChecker()
            };
            return engine;
        }

        [Test]
        public void TestGetAndSetGameChecker()
        {
            IGameChecker gameChecker = new MockGameChecker();
            IGameEngine engine = new GameEngine(null, null);
            Assert.Null(engine.GameChecker);
            engine.GameChecker = gameChecker;
            Assert.AreEqual(gameChecker, engine.GameChecker);
        }

        [Test]
        public void TestGetBoard()
        {
            IGameEngine engine = GetDefaultGameEngine(2);
            IBoard board = engine.Board;
            Assert.AreEqual(2, board.Height);
            Assert.AreEqual(2, board.Width);

            engine = GetDefaultGameEngine(3);
            board = engine.Board;
            Assert.AreEqual(3, board.Height);
            Assert.AreEqual(3, board.Width);
        }

        [Test]
        public void GetPlayers()
        {
            IGameEngine engine = GetDefaultGameEngine(1);
            IList<IPlayer> players = engine.Players;
            Assert.AreEqual(2, players.Count);
            IPlayer player1 = players[0];
            IPlayer player2 = players[1];
            Assert.AreEqual("Bob", player1.Name);
            Assert.AreEqual("Sue", player2.Name);
        }

        [Test]
        public void ResetGame()
        {
            //set up the game, do 1 turn, reset the Game, and verify initial state
            IGameEngine engine = GetDefaultGameEngine(2);
            IBoard board = engine.Board;
            IList<IPlayer> players = engine.Players;
            Assert.AreEqual(2, players.Count);
            IPlayer player1 = players[0];

            engine.DoTurn();
            Assert.False(board.IsEmpty(0, 0));

            engine.ResetGame();
            //board should be clear
            Assert.True(board.IsEmpty(0, 0));
            //player turn should be reset
            //game should not be finished
            Assert.AreEqual(player1, engine.GetNextTurnPlayer());
            Assert.False(engine.GameChecker.IsGameOver);
        }

        [Test]
        public void PlayGame2X2()
        {
            IGameEngine engine = GetDefaultGameEngine(2);
            engine.PlayGame();
            IGameChecker gameChecker = engine.GameChecker;
            Assert.True(gameChecker.IsGameOver);
            Assert.Null(gameChecker.WinningPlayer);
        }

        [Test]
        public void PlayGame3X3()
        {
            IGameEngine engine = GetDefaultGameEngine(3);
            engine.PlayGame();
            IGameChecker gameChecker = engine.GameChecker;
            Assert.True(gameChecker.IsGameOver);

            //player 1 should be the winner since he went first and filled up
            //5 out of the 9 spaces.
            IPlayer player1 = engine.Players[0];
            Assert.AreEqual(player1, gameChecker.WinningPlayer);
        }

        [Test]
        public void GetNextTurnPlayer()
        {
            IGameEngine engine = GetDefaultGameEngine(2);
            IList<IPlayer> players = engine.Players;
            IPlayer player1 = players[0];
            IPlayer player2 = players[1];
            IPlayer nextPlayer = engine.GetNextTurnPlayer();
            Assert.AreEqual(player1, nextPlayer);

            engine.DoTurn();
            nextPlayer = engine.GetNextTurnPlayer();
            Assert.AreEqual(player2, nextPlayer);
        }

        [Test]
        public void gameActionOnEmptySpaceIsValid()
        {
            IGameEngine engine = GetDefaultGameEngine(2);
            IList<IPlayer> players = engine.Players;
            IPlayer player2 = players[1];
            Assert.True(player2 is MockPlayer);
            Assert.False(player2 is MockHumanPlayer);
            IGameAction action = player2.RequestAction();

            bool isValid = engine.IsValidGameAction(action);
            Assert.True(isValid);
        }

        [Test]
        public void gameActionOnOccupiedSpaceIsNotValid()
        {
            IGameEngine engine = GetDefaultGameEngine(2);
            IList<IPlayer> players = engine.Players;
            IPlayer player2 = players[1];
            IGameAction action = player2.RequestAction();

            //should request an action from player 1 and do that action
            engine.DoTurn();

            //the original requested action should no longer be valid
            bool isValid = engine.IsValidGameAction(action);
            Assert.False(isValid);
        }
        [Test]
        public void gameActionOnOutOfBoundsRowAndColumnIsNotValid()
        {
            IGameEngine engine = GetDefaultGameEngine(2);
            GameAction action1 = new GameAction(-1, 0, 'X');
            GameAction action2 = new GameAction(2, 0, 'X');
            GameAction action3 = new GameAction(0, -1, 'X');
            GameAction action4 = new GameAction(0, 2, 'X');

            //the original requested action should no longer be valid
            bool isValid = engine.IsValidGameAction(action1);
            Assert.False(isValid);

            isValid = engine.IsValidGameAction(action2);
            Assert.False(isValid);
            isValid = engine.IsValidGameAction(action3);
            Assert.False(isValid);
            isValid = engine.IsValidGameAction(action4);
            Assert.False(isValid);
        }

        [Test]
        public void DoTurn()
        {
            IGameEngine engine = GetDefaultGameEngine(2);
            IBoard board = engine.Board;
            IList<IPlayer> players = engine.Players;
            IPlayer player2 = players[1];

            Assert.True(board.IsEmpty(0, 0));
            //should request an action from player 1 and do that action at 0,0
            engine.DoTurn();
            Assert.False(board.IsEmpty(0, 0));

            IPlayer nextPlayer = engine.GetNextTurnPlayer();
            Assert.AreEqual(player2, nextPlayer);
        }

        [Test]
        public void PoorlyImplementedAiPlayerCausesException()
        {
            IGameEngine engine = GetDefaultGameEngine(2);
            IList<IPlayer> players = engine.Players;
            //replace a player with a poorly implemented AI
            var badAiPlayer = new MockPoorlyImplementedAiPlayer("Collin", 'T', null);
            players[0] = badAiPlayer;
            Assert.Throws<InvalidOperationException>(engine.DoTurn);
        }
    }
}