using System.ComponentModel;
using NUnit.Framework;
using SonicScholar.SimpleBoardGame.Framework;

namespace TreasureGame.Test
{
    [TestFixture]
    public class TreasureHuntAIPlayerTest
    {
        private const int numTestsToRun = 100;

        [Test]
        public void TestAIPlayerIsComputer()
        {
            TreasureHuntAIPlayer player = new TreasureHuntAIPlayer("Wall-E", 'X', null);
            Assert.True(player.IsComputer);
        }

        [Test]
        public void TestAIPlayerRequestActionOnEmptyBoard()
        {
            Board board = new Board(3, 4);
            TreasureHuntAIPlayer player = new TreasureHuntAIPlayer("Wall-E", 'X', board);


            for (int i = 0; i < numTestsToRun; i++)
            {
                var action = player.RequestAction();

                Assert.GreaterOrEqual(action.Row, 0);
                Assert.GreaterOrEqual(action.Column, 0);

                Assert.Less(action.Row, board.Height);
                Assert.Less(action.Column, board.Width);

            }

        }

        [Test]
        public void TestAIPlayerRequestActionOnBoardWithFilledUpRow()
        {
            Board board = new Board(3, 4);
            
            //fill up the 1st row
            for(int col=0; col < board.Width; col++)
                board.MarkPosition(0, col, 'A');

            TreasureHuntAIPlayer player = new TreasureHuntAIPlayer("Wall-E", 'X', board);


            for (int i = 0; i < numTestsToRun; i++)
            {
                var action = player.RequestAction();

                Assert.Greater(action.Row, 0);
                Assert.GreaterOrEqual(action.Column, 0);

                Assert.Less(action.Row, board.Height);
                Assert.Less(action.Column, board.Width);

            }

        }

        [Test]
        public void TestAIPlayerRequestActionOnBoardWithFilledUpColumn()
        {
            Board board = new Board(3, 4);
            TreasureHuntAIPlayer player = new TreasureHuntAIPlayer("Wall-E", 'X', board);

            //fill up the first column
            for (int row = 0; row < board.Height; row++)
                board.MarkPosition(row, 0, 'A');

            for (int i = 0; i < numTestsToRun; i++)
            {
                var action = player.RequestAction();

                Assert.GreaterOrEqual(action.Row, 0);
                Assert.Greater(action.Column, 0);

                Assert.Less(action.Row, board.Height);
                Assert.Less(action.Column, board.Width);

            }

        }

    }
}