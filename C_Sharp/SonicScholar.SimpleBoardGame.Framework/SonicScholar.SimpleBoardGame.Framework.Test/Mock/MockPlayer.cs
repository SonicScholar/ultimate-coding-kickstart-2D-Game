namespace SonicScholar.SimpleBoardGame.Framework.Test.Mock
{
    class MockPlayer : PlayerBase
    {

        public MockPlayer(string name, char marker, IBoard board)
            : base(name, marker)
        {
            Board = board;
        }

        protected IBoard Board { get; set; }

        public override bool IsComputer => true;

        /// <summary>
        /// Dummy player just picks the first available space on the board going by
        /// row first, then column
        /// </summary>
        /// <returns>An IGameAction representing the player's next desired move</returns>
        public override IGameAction RequestAction()
        {
            for (int row = 0; row < Board.Height; row++)
            {
                for (int col = 0; col < Board.Width; col++)
                {
                    if (Board.IsEmpty(row, col))
                        return new GameAction(row, col, Marker);
                }
            }
            return null;
        }
    }
}
