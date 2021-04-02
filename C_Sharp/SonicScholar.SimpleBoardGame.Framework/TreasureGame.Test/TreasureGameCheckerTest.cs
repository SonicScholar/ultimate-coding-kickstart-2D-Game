using System;
using System.Collections.Generic;
using NUnit.Framework;
using SonicScholar.SimpleBoardGame.Framework;

namespace TreasureGame.Test
{
    [TestFixture]
    public class TreasureGameCheckerTest
    {
        private const int numTestsToRun = 100;

        static IGameEngine GetDefaultGameEngine()
        {
            Board board = new Board(3, 3);
            TreasureHuntAIPlayer p1 = new TreasureHuntAIPlayer("Wall-E", 'X', board);
            TreasureHuntAIPlayer p2 = new TreasureHuntAIPlayer("Chappie", 'O', board);
            List<IPlayer> players = new List<IPlayer>();
            players.Add(p1);
            players.Add(p2);

            return new GameEngine(players, board);
        }

        [Test]
        public void TestTreasureLocationIsNullByDefault()
        {
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            Assert.IsNull(gameChecker.TreasureLocation);
        }

        [Test]
        public void TestTreasureLocationIsValidAfterSettingGameEngine()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            IBoard board = gameEngine.Board;
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            gameChecker.GameEngine = gameEngine;

            for (int i = 0; i < numTestsToRun; i++)
            {
                Coordinate treasureLocation = gameChecker.TreasureLocation;
                Assert.NotNull(treasureLocation);

                Assert.GreaterOrEqual(treasureLocation.Row, 0);
                Assert.GreaterOrEqual(treasureLocation.Column, 0);

                Assert.Less(treasureLocation.Row, board.Height);
                Assert.Less(treasureLocation.Column, board.Width); 
            }
        }

        [Test]
        public void TestGameIsNotOverOnEmptyBoard()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            IBoard board = gameEngine.Board;
            board.ResetBoard();
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            gameChecker.GameEngine = gameEngine;

            Assert.False(gameChecker.IsGameOver);
        }

        [Test]
        public void TestGameIsNotOverWhenEverythingOnBoardIsMarkedExceptTreasureLocation()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            IBoard board = gameEngine.Board;
            board.ResetBoard();
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            gameChecker.GameEngine = gameEngine;

            Coordinate treasureLocation = gameChecker.TreasureLocation;

            for (int row = 0; row < board.Height; row++)
            {
                for (int col = 0; col < board.Width; col++)
                {
                    if(treasureLocation.Row == row && treasureLocation.Column == col)
                        continue;
                    board.MarkPosition(row, col, 'A');
                }
            }

            Assert.False(gameChecker.IsGameOver);
        }

        [Test]
        public void TestGameIsNotOverWhenTreasureLocationIsMarked()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            IBoard board = gameEngine.Board;
            board.ResetBoard();
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            gameChecker.GameEngine = gameEngine;

            Coordinate treasureLocation = gameChecker.TreasureLocation;
            board.MarkPosition(treasureLocation.Row, treasureLocation.Column, 'A');

            Assert.True(gameChecker.IsGameOver);
        }

        [Test]
        public void TestWinningPlayerThrowsExceptionWhenGameNotOver()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            IBoard board = gameEngine.Board;
            board.ResetBoard();
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            gameChecker.GameEngine = gameEngine;

            Assert.Throws<InvalidOperationException>( () =>
            {
                IPlayer p = gameChecker.WinningPlayer;
            });
        }

        [Test]
        public void TestingWinningPlayer1()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            IBoard board = gameEngine.Board;
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            gameChecker.GameEngine = gameEngine;

            IPlayer p1 = gameEngine.Players[0];
            Coordinate treasureLocation = gameChecker.TreasureLocation;
            board.MarkPosition(treasureLocation.Row, treasureLocation.Column, p1.Marker);

            IPlayer winner = gameChecker.WinningPlayer;
            Assert.AreEqual(p1, winner);
        }

        [Test]
        public void TestingWinningPlayer2()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            IBoard board = gameEngine.Board;
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            gameChecker.GameEngine = gameEngine;

            IPlayer p2 = gameEngine.Players[1];
            Coordinate treasureLocation = gameChecker.TreasureLocation;
            board.MarkPosition(treasureLocation.Row, treasureLocation.Column, p2.Marker);

            IPlayer winner = gameChecker.WinningPlayer;
            Assert.AreEqual(p2, winner);
        }

        [Test]
        public void TestingNoWinningPlayer()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            IBoard board = gameEngine.Board;
            TreasureGameChecker gameChecker = new TreasureGameChecker();
            gameChecker.GameEngine = gameEngine;

            Coordinate treasureLocation = gameChecker.TreasureLocation;
            board.MarkPosition(treasureLocation.Row, treasureLocation.Column, 'T');

            IPlayer winner = gameChecker.WinningPlayer;
            Assert.IsNull(winner);
        }
    }
}