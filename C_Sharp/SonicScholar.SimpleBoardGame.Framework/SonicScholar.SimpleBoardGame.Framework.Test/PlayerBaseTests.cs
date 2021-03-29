using NUnit.Framework;
using SonicScholar.SimpleBoardGame.Framework.Test.Mock;

namespace SonicScholar.SimpleBoardGame.Framework.Test
{
    [TestFixture]
    public class PlayerBaseTests
    {
        [Test]
        public void TestName()
        {
            PlayerBase p = new MockPlayer("Jimmy", 'X', null);
            Assert.AreEqual("Jimmy", p.Name);
        }

        [Test]
        public void TestMarker()
        {
            PlayerBase p = new MockPlayer("Jimmy", 'X', null);
            Assert.AreEqual('X', p.Marker);
        }

        [Test]
        public void TestMockPlayerIsComputer()
        {
            PlayerBase p = new MockPlayer("Jimmy", 'X', null);
            Assert.True(p.IsComputer);
        }

        [Test]
        public void TestMockHumanPlayerIsNotComputer()
        {
            PlayerBase p = new MockHumanPlayer("Jimmy", 'X', null);
            Assert.False(p.IsComputer);
        }

        [Test]
        public void RequestActionOnEmptyBoardIsFirstSpotAvailable()
        {
            IBoard board = new Board(1, 1);
            PlayerBase p = new MockPlayer("Hank", 'X', board);
            IGameAction action = p.RequestAction();

            Assert.AreEqual(0, action.Row);
            Assert.AreEqual(0, action.Column);
            Assert.AreEqual('X', action.Marker);
        }

        [Test]
        public void RequestActionOnFullBoardIsNull()
        {
            IBoard board = new Board(1, 1);
            PlayerBase p = new MockPlayer("Hank", 'X', board);
            board.MarkPosition(0, 0, 'O');
            IGameAction action = p.RequestAction();
            Assert.Null(action);
        }
    }
}
