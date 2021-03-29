namespace SonicScholar.SimpleBoardGame.Framework
{
    /// <summary>
    /// An interface for determining when the game is over
    /// and who the winner is. Implementations determine how
    /// this business logic works.
    /// </summary>
    public interface IGameChecker
    {
        /// <summary>
        /// Gets or sets the GameEngine object assigned to this checker
        /// </summary>
        IGameEngine GameEngine { get; set; }

        /// <summary>
        /// Analyzes the board and determines whether or not the game
        /// is over according to the rules specified for this game.
        /// </summary>
        bool IsGameOver { get; }

        /// <summary>
        /// If the game is over, returns which player is the winner.
        /// If there is no winner, returns null. If the game is not
        /// over, throws an exception stating the game is still in
        /// progress
        /// </summary>
        IPlayer WinningPlayer { get; }

    }
}
