using NUnit.Framework;
using System;

namespace SonicScholar.SimpleBoardGame.Framework.Test
{
    [TestFixture]
    public class BoardTests
    {
        private const int DefaultWidth = 3;
        private const int DefaultHeight = 4;

        private static IBoard GetDefaultTestBoard()
        {
            return new Board(DefaultWidth, DefaultHeight);
        }

        [Test]
        public void TestWidth()
        {
            int width = 3;
            IBoard board = GetDefaultTestBoard();
            int actual = board.Width;
            Assert.AreEqual(width, actual);
        }

        [Test]
        public void TestHeight()
        {
            int height = 4;
            IBoard board = GetDefaultTestBoard();
            int actual = board.Height;
            Assert.AreEqual(height, actual);
        }

        [Test]
        public void TestResetBoard()
        {
            IBoard board = GetDefaultTestBoard();
            for (int row = 0; row < DefaultHeight; row++)
            {
                for (int col = 0; col < DefaultWidth; col++)
                {
                    board.MarkPosition(row, col, 'X');
                }
            }

            board.ResetBoard();
            for (int row = 0; row < DefaultHeight; row++)
            {
                for (int col = 0; col < DefaultWidth; col++)
                {
                    bool isEmpty = board.IsEmpty(row, col);
                    Assert.True(isEmpty);
                }
            }
        }

        [Test]
        public void TestMarkPosition()
        {
            //mark 2 positions with X and O in opposite corners and check their values
            IBoard board = GetDefaultTestBoard();
            char marker = 'X';
            board.MarkPosition(0, 0, marker);
            marker = 'O';
            board.MarkPosition(DefaultHeight - 1, DefaultWidth - 1, marker);

            Assert.AreEqual('X', board.GetMarkerAtPosition(0, 0));
            Assert.AreEqual('O', board.GetMarkerAtPosition(DefaultHeight - 1, DefaultWidth - 1));
        }

        [Test]
        public void MarkPositionWithDefaultMarkerThrowsArgumentException()
        {
            IBoard board = GetDefaultTestBoard();
            char emptyMarker = Board.EmptySpace;
            string expectedErrorMessage = "Cannot manually mark a position using" +
                                          " the default empty marker. Use clearPosition() instead.";
            Assert.Throws<ArgumentException>(() => board.MarkPosition(0, 0, emptyMarker),
                expectedErrorMessage);
        }

        [Test]
        public void TestClearMarker()
        {
            IBoard board = GetDefaultTestBoard();
            char marker = 'X';
            board.MarkPosition(0, 0, marker);
            board.ClearMarker(0, 0);
            Assert.True(board.IsEmpty(0, 0));
        }

        [Test]
        public void TestIsEmpty()
        {
            IBoard board = GetDefaultTestBoard();
            //check positions are empty
            Assert.True(board.IsEmpty(0, 0));
            Assert.True(board.IsEmpty(DefaultHeight - 1, DefaultWidth - 1));

            //mark these two positions
            char marker = 'X';
            board.MarkPosition(0, 0, marker);
            board.MarkPosition(DefaultHeight - 1, DefaultWidth - 1, marker);

            //check that they are now occupied
            Assert.False(board.IsEmpty(0, 0));
            Assert.False(board.IsEmpty(DefaultHeight - 1, DefaultWidth - 1));
        }
    }
}