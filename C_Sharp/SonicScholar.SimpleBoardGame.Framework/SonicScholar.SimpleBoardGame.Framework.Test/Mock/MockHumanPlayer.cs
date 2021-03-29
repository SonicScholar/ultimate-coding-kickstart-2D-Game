namespace SonicScholar.SimpleBoardGame.Framework.Test.Mock
{
    class MockHumanPlayer : MockPlayer
    {
        private int _counter;

        public MockHumanPlayer(string name, char marker, IBoard board)
        : base(name, marker, board)
        {
        }

        /// <summary>
        /// This is a computer player pretending to be a human!
        /// (For the sake of unit testing)
        /// </summary>
        public override bool IsComputer => false;

        public override IGameAction RequestAction()
        {
            _counter++;
            //returns valid game actions every 3rd request
            bool returnValidAction = _counter % 3 == 0;

            if (returnValidAction)
                return base.RequestAction();
            return null;
        }
    }
}
