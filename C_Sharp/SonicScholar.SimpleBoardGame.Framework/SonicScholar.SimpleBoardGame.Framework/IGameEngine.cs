using System.Collections.Generic;

namespace SonicScholar.SimpleBoardGame.Framework
{

    /// <summary>
    /// The GameEngine interface is responsible for keeping track
    /// of all the game state, and running the game until its
    /// completion.
    /// </summary>
    public interface IGameEngine
    {
        /// <summary>
        /// Gets or sets the GameChecker used by this engine.
        /// </summary>
        IGameChecker GameChecker { get; set; }


        /// <summary>
        /// Gets the Board used by the GameEngine
        /// </summary>
        IBoard Board { get; }


        /// <summary>
        /// Gets a list of players currently registered by the engine
        /// </summary>
        IList<IPlayer> Players { get; }

        /// <summary>
        /// Resets the entire state of the game engine back to its
        /// initial state just before playGame() is called.
        /// </summary>
        void ResetGame();

        /// <summary>
        /// Starts the game and returns when the game is finished
        /// </summary>
        void PlayGame();


        /// <summary>
        /// Gets the player whose next turn it is
        /// </summary>
        /// <returns>the player whose next turn it is</returns>
        IPlayer GetNextTurnPlayer();

        /// <summary>
        /// Validates whether or not a given GameAction is valid
        /// for the specific game being played
        /// </summary>
        /// <param name="gameAction">the GameActionToValidate</param>
        /// <returns>`true` if the action is valid. `false` if the
        /// action is invalid.</returns>
        bool IsValidGameAction(IGameAction gameAction);

        /// <summary>
        /// Completes one turn of the game
        /// </summary>
        void DoTurn();
    }
}