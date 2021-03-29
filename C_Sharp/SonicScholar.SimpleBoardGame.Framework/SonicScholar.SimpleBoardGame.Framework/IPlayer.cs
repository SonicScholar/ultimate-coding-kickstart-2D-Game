namespace SonicScholar.SimpleBoardGame.Framework
{
    /// <summary>
    /// Player represents an object that can interact
    /// with a game.
    /// </summary>
    public interface IPlayer
    {
        /// <summary>
        /// Gets the name of the IPlayer object
        /// </summary>
        /// <returns>the name of the IPlayer object</returns>
        string Name { get; }

        /// <summary>
        /// Gets the marker of the IPlayer object
        /// </summary>
        /// <returns>the marker of the IPlayer object</returns>
        char Marker { get; }

        /// <summary>
        /// Gets whether the Player object is AI
        /// </summary>
        /// <returns>true if the player object is AI</returns>
        bool IsComputer { get; }

        /// <summary>
        /// Requests an action from the IPlayer object.
        /// The implementing Player is responsible for
        /// keeping track of game state.
        /// </summary>
        /// <returns>An IGameAction of the player's desired next action</returns>
        IGameAction RequestAction();

    }
}