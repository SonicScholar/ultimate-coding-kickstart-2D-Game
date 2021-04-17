namespace SonicScholar.SimpleBoardGame.Framework
{
    /// <summary>
    ///  A simple 2 dimensional, width x height, rectangular board that uses
    /// characters as markers that occupy space on the board.
    /// </summary>
    public interface IBoard
    {
        /// <summary>
        /// Gets the width of the board
        /// </summary>
        int Width { get; }
        /// <summary>
        /// Gets the height of the board
        /// </summary>
        int Height { get; }
        /// <summary>
        /// Resets the board to whatever represents the default
        /// state for this type of board
        /// </summary>
        void ResetBoard();
        /// <summary>
        /// Marks a position on the board with the specified character
        /// value. If an existing marker is in this space it will be
        /// overwritten
        /// </summary>
        /// <param name="row">The row value of the position to be marked</param>
        /// <param name="column">The column value of the position to be marked</param>
        /// <param name="marker">The character value of the position to be marked</param>
        void MarkPosition(int row, int column, char marker);
        /// <summary>
        /// Clears a marker at the specified position, resetting
        /// it back to the default value.
        /// </summary>
        /// <param name="row">The row value of the position to clear</param>
        /// <param name="column">The column value of the position to clear</param>
        void ClearMarker(int row, int column);
        /// <summary>
        /// Gets whether or not a space on the board is considered
        /// empty at the position specified.
        /// </summary>
        /// <param name="row">The row value of the position to check</param>
        /// <param name="column">The column value of the position to check</param>
        /// <returns>`true` if the position is empty</returns>
        bool IsEmpty(int row, int column);
        /// <summary>
        /// Gets the character value of the specified position on the board.
        /// The character value may represent a real marker or the default
        /// character for an unoccupied space.
        /// </summary>
        /// <param name="row">The row value of the position to get</param>
        /// <param name="column">The column value of the position to get</param>
        /// <returns>the char marker value</returns>
        char GetMarkerAtPosition(int row, int column);

        /// <summary>
        /// Identifies whether the input position is a valid space on
        /// the board.
        /// </summary>
        /// <param name="row">position row</param>
        /// <param name="col">position column</param>
        /// <returns>true if the space exists on the board, false if the position
        /// is outside the boundary of the board's given dimensions.</returns>
        bool IsValidSpace(int row, int col);
    }
}
