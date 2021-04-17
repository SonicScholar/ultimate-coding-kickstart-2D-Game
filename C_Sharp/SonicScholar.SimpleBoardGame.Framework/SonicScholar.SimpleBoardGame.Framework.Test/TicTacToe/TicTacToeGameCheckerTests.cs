using System.Collections.Generic;
using NUnit.Framework;
using SonicScholar.SimpleBoardGame.Framework.Test.Mock;
using TicTacToe;

namespace SonicScholar.SimpleBoardGame.Framework.Test.TicTacToe
{
    [TestFixture]
    public class TicTacToeGameCheckerTests
    {
        static readonly int ticTacToeBoardSize = 3;
        static readonly int numToWin = 3;
        static GameEngine GetDefaultGameEngine()
        {
            var board = new Board(ticTacToeBoardSize, ticTacToeBoardSize);

            //create 2 players
            List<IPlayer> players = new List<IPlayer>();
            var player1 = new MockPlayer("Bob", 'X', board);
            var player2 = new MockPlayer("Sue", 'O', board);
            players.Add(player1);
            players.Add(player2);

            var gameEngine = new GameEngine(players, board);
            gameEngine.GameChecker = new NInARowGameChecker(numToWin);
            return gameEngine;
        }

        [Test]
        public void TestGameIsNotOverOnEmptyBoard()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;

            Assert.False(gameChecker.IsGameOver);
        }

        [Test]
        public void TestGameIsNotOverOnPartiallyFilledBoard()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            // X O X
            // X X O
            // O   O
            board.MarkPosition(0, 0, 'X');
            board.MarkPosition(0, 1, 'O');
            board.MarkPosition(0, 2, 'X');

            board.MarkPosition(1, 0, 'X');
            board.MarkPosition(1, 1, 'X');
            board.MarkPosition(1, 2, 'O');

            board.MarkPosition(2, 0, 'O');
            board.MarkPosition(2, 2, 'O');

            Assert.False(gameChecker.IsGameOver);
        }

        [Test]
        public void TestGameIsOverWhenAllSpacesAreOccupied()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            // X O X
            // X X O
            // O X O
            board.MarkPosition(0, 0, 'X');
            board.MarkPosition(0, 1, 'O');
            board.MarkPosition(0, 2, 'X');

            board.MarkPosition(1, 0, 'X');
            board.MarkPosition(1, 1, 'X');
            board.MarkPosition(1, 2, 'O');

            board.MarkPosition(2, 0, 'O');
            board.MarkPosition(2, 1, 'X');
            board.MarkPosition(2, 2, 'O');

            Assert.True(gameChecker.IsGameOver);
            Assert.Null(gameChecker.WinningPlayer);
        }

        [Test]
        public void TestGameIsOverWhen3ConsecutiveInRow()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            // X X X
            //
            //
            board.MarkPosition(0, 0, 'X');
            board.MarkPosition(0, 1, 'X');
            board.MarkPosition(0, 2, 'X');

            Assert.True(gameChecker.IsGameOver);
        }

        [Test]
        public void TestGameIsOverWhen3ConsecutiveInColumn()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            // X
            // X
            // X
            board.MarkPosition(0, 0, 'X');
            board.MarkPosition(1, 0, 'X');
            board.MarkPosition(2, 0, 'X');

            Assert.True(gameChecker.IsGameOver);
        }

        [Test]
        public void TestGameIsOverWhen3ConsecutiveInDiagonal()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            // X
            //   X
            //     X
            board.MarkPosition(0, 0, 'X');
            board.MarkPosition(1, 1, 'X');
            board.MarkPosition(2, 2, 'X');

            Assert.True(gameChecker.IsGameOver);
        }

        [Test]
        public void TestGetWinnerWhen3ConsecutiveInRow()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            var p1 = gameEngine.Players[0];
            // X X X
            //
            //
            board.MarkPosition(0, 0, p1.Marker);
            board.MarkPosition(0, 1, p1.Marker);
            board.MarkPosition(0, 2, p1.Marker);

            Assert.True(gameChecker.IsGameOver);
            var winner = gameChecker.WinningPlayer;
            Assert.AreEqual(p1, winner);
        }

        [Test]
        public void TestGetWinnerWhen3ConsecutiveInColumn()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            var p2 = gameEngine.Players[1];
            // O
            // O
            // O
            board.MarkPosition(0, 0, p2.Marker);
            board.MarkPosition(1, 0, p2.Marker);
            board.MarkPosition(2, 0, p2.Marker);

            Assert.True(gameChecker.IsGameOver);
            var winner = gameChecker.WinningPlayer;
            Assert.AreEqual(p2, winner);
        }

        [Test]
        public void TestGetWinnerWhen3ConsecutiveInDiagonal()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            var p1 = gameEngine.Players[0];
            // X
            //   X
            //     X
            board.MarkPosition(0, 0, p1.Marker);
            board.MarkPosition(1, 1, p1.Marker);
            board.MarkPosition(2, 2, p1.Marker);

            Assert.True(gameChecker.IsGameOver);
            var winner = gameChecker.WinningPlayer;
            Assert.AreEqual(p1, winner);
        }

        //added this unit test during implementation of check3InADiagonal
        //because I wanted a test that checked different slope directions
        [Test]
        public void TestGetWinnerWhen3ConsecutiveInDiagonal2()
        {
            var gameEngine = GetDefaultGameEngine();
            var gameChecker = gameEngine.GameChecker;
            var board = gameEngine.Board;

            var p1 = gameEngine.Players[0];
            //     X
            //   X
            // X
            board.MarkPosition(0, 2, p1.Marker);
            board.MarkPosition(1, 1, p1.Marker);
            board.MarkPosition(2, 0, p1.Marker);

            Assert.True(gameChecker.IsGameOver);
            var winner = gameChecker.WinningPlayer;
            Assert.AreEqual(p1, winner);
        }
    }
}