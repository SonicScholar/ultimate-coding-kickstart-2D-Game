using NUnit.Framework;
using SonicScholar.SimpleBoardGame.Framework.Test.Mock;
using System.Collections.Generic;

namespace SonicScholar.SimpleBoardGame.Framework.Test
{
    [TestFixture]
    public class MockGameCheckerTests
    {
        private const int BoardSize = 2;

        static IGameEngine GetDefaultGameEngine()
        {
            IBoard board = new Board(BoardSize, BoardSize);

            //create 2 players
            IList<IPlayer> players = new List<IPlayer>();
            var player1 = new MockPlayer("Bob", 'X', board);
            var player2 = new MockPlayer("Sue", 'O', board);
            players.Add(player1);
            players.Add(player2);


            return new GameEngine(players, board);
        }

        [Test]
        public void TestGameEngineIsNullByDefault()
        {
            MockGameChecker gameChecker = new MockGameChecker();
            Assert.Null(gameChecker.GameEngine);
        }

        [Test]
        public void TestSetGameEngine()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            MockGameChecker gameChecker = new MockGameChecker
            {
                GameEngine = gameEngine
            };

            object actual = gameChecker.GameEngine;
            Assert.NotNull(actual);
            Assert.AreEqual(gameEngine, actual);
        }

        [Test]
        public void TestIsGameOverOnDefaultBoardIsFalse()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            MockGameChecker gameChecker = new MockGameChecker
            {
                GameEngine = gameEngine
            };

            bool isGameOver = gameChecker.IsGameOver;
            Assert.False(isGameOver);
        }

        [Test]
        public void TestIsGameOverOnPartiallyFilledBoardIsFalse()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            MockGameChecker gameChecker = new MockGameChecker { GameEngine = gameEngine };
            IPlayer player1 = gameEngine.Players[0];
            char player1Marker = player1.Marker;

            IBoard board = gameEngine.Board;
            board.MarkPosition(0, 0, player1Marker);

            bool isGameOver = gameChecker.IsGameOver;
            Assert.False(isGameOver);
        }

        [Test]
        public void TestIsGameOverOnFilledUpBoardIsTrue()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            MockGameChecker gameChecker = new MockGameChecker {GameEngine = gameEngine};
            IPlayer player1 = gameEngine.Players[0];
            char player1Marker = player1.Marker;

            IBoard board = gameEngine.Board;
            board.MarkPosition(0, 0, player1Marker);
            board.MarkPosition(0, 1, player1Marker);
            board.MarkPosition(1, 0, player1Marker);
            board.MarkPosition(1, 1, player1Marker);

            bool isGameOver = gameChecker.IsGameOver;
            Assert.True(isGameOver);
        }

        [Test]
        public void TestWinningPlayer()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            MockGameChecker gameChecker = new MockGameChecker {GameEngine = (gameEngine)};
            IPlayer player2 = gameEngine.Players[1];
            char player2Marker = player2.Marker;

            IBoard board = gameEngine.Board;
            board.MarkPosition(0, 0, player2Marker);
            board.MarkPosition(0, 1, player2Marker);
            board.MarkPosition(1, 0, player2Marker);
            board.MarkPosition(1, 1, player2Marker);

            bool isGameOver = gameChecker.IsGameOver;
            Assert.True(isGameOver);
            IPlayer winner = gameChecker.WinningPlayer;
            Assert.NotNull(winner);
            Assert.AreEqual(player2, winner);
        }

        [Test]
        public void GetWinnerOfTieGameIsNull()
        {
            IGameEngine gameEngine = GetDefaultGameEngine();
            MockGameChecker gameChecker = new MockGameChecker {GameEngine = (gameEngine)};

            IPlayer player1 = gameEngine.Players[0];
            char player1Marker = player1.Marker;
            IPlayer player2 = gameEngine.Players[1];
            char player2Marker = player2.Marker;

            IBoard board = gameEngine.Board;
            board.MarkPosition(0, 0, player1Marker);
            board.MarkPosition(0, 1, player2Marker);
            board.MarkPosition(1, 0, player1Marker);
            board.MarkPosition(1, 1, player2Marker);

            bool isGameOver = gameChecker.IsGameOver;
            Assert.True(isGameOver);
            IPlayer winner = gameChecker.WinningPlayer;
            Assert.Null(winner);
            Assert.AreNotEqual(null, player1);
            Assert.AreNotEqual(null, player2);
        }
    }
}