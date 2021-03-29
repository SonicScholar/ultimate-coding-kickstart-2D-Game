namespace SonicScholar.SimpleBoardGame.Framework.Test.Mock
{
    /// <summary>
    /// This class simulates a poorly implemented AI
    /// player that does not return valid game actions.
    /// It's used for testing that proper exceptions are
    /// thrown so the programmer is made aware.
    /// </summary>
    class MockPoorlyImplementedAiPlayer : MockPlayer
    {
        public MockPoorlyImplementedAiPlayer(string name, char marker, IBoard board)
            : base(name, marker, board)
        {
        }

        public override bool IsComputer => true;

        /// <summary>
        /// Requests an action from a poorly implemented AI player
        /// </summary>
        /// <returns>null</returns>
        public override IGameAction RequestAction()
        {
            return null;
        }
    }
}
