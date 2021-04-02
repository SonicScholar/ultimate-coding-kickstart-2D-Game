using System;
using NUnit.Framework;
using SonicScholar.SimpleBoardGame.Framework;

namespace TreasureGame.Test
{
    [TestFixture]
    public class TreasureHuntPlayerTest
    {
        [Test]
        public void TestTreasureHuntPlayerProperties()
        {
            Board board = new Board(6, 9);
            TreasureHuntPlayer player = new TreasureHuntPlayer("Collin", 'X', board);

            Assert.AreEqual(board, player.Board);

            Assert.False(player.IsComputer);
        }

        [Test]
        public void TestGetCoordinateFromUserInputValidRows()
        {
            int[] rowNumbers = new[] {1, 2, 3};

            foreach (var num in rowNumbers)
            {
                Coordinate coordinate = TreasureHuntPlayer.GetCoordinateFromUserInput(num, 3);
                int expected = 0;
                Assert.AreEqual(expected, coordinate.Row);
            }

            rowNumbers = new[] { 4, 5, 6 };
            foreach (var num in rowNumbers)
            {
                Coordinate coordinate = TreasureHuntPlayer.GetCoordinateFromUserInput(num, 3);
                int expected = 1;
                Assert.AreEqual(expected, coordinate.Row);
            }

            rowNumbers = new[] { 7, 8, 9 };
            foreach (var num in rowNumbers)
            {
                Coordinate coordinate = TreasureHuntPlayer.GetCoordinateFromUserInput(num, 3);
                int expected = 2;
                Assert.AreEqual(expected, coordinate.Row);
            }
        }

        [Test]
        public void TestGetCoordinateFromUserInputValidColumns()
        {
            int[] columnNumbers = new[] { 1, 4, 7 };

            foreach (var num in columnNumbers)
            {
                Coordinate coordinate = TreasureHuntPlayer.GetCoordinateFromUserInput(num, 3);
                int expected = 0;
                Assert.AreEqual(expected, coordinate.Column);
            }

            columnNumbers = new[] { 2, 5, 8 };
            foreach (var num in columnNumbers)
            {
                Coordinate coordinate = TreasureHuntPlayer.GetCoordinateFromUserInput(num, 3);
                int expected = 1;
                Assert.AreEqual(expected, coordinate.Column);
            }

            columnNumbers = new[] { 3, 6, 9 };
            foreach (var num in columnNumbers)
            {
                Coordinate coordinate = TreasureHuntPlayer.GetCoordinateFromUserInput(num, 3);
                int expected = 2;
                Assert.AreEqual(expected, coordinate.Column);
            }
        }
    }
}
