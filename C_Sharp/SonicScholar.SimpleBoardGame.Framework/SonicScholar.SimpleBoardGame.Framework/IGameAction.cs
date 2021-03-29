namespace SonicScholar.SimpleBoardGame.Framework
{
    /// <summary>
    /// An GameAction represents a move that can be made as
    /// part of a turn. It simply represents an action of
    /// putting a marker on a specific space (row, column)
    /// of the game board.
    /// </summary>
    public interface IGameAction
    {
        /// <summary>
        /// Gets the row to place the marker
        /// </summary>
        int Row { get; }
        /// <summary>
        /// Gets the column to place the marker
        /// </summary>
        int Column { get; }
        /// <summary>
        /// Gets the marker to place on the board
        /// </summary>
        char Marker { get; }
    }
}
