using System;

namespace SonicScholar.SimpleBoardGame.Framework
{
    /// <summary>
    /// Default implementation of the IBoard interface
    /// </summary>
    public class Board : IBoard
    {
        public const char EmptySpace = ' ';
        /// <summary>
        /// Creates a Board object with the specified width and height
        /// </summary>
        /// <param name="width"></param>
        /// <param name="height"></param>
        public Board(int width, int height)
        {
            Width = width;
            Height = height;
            Field = new char[height, width];
            ResetBoard();
        }
        public int Width { get; }
        public int Height { get; }
        private char[,] Field { get; }


        public void ResetBoard()
        {
            for (int row = 0; row < Height; row++)
            {
                for (int col = 0; col < Width; col++)
                {
                    ClearMarker(row, col);
                }
            }
        }

        public void MarkPosition(int row, int column, char marker)
        {
            //handle accidentally putting ' ' for value
            if (marker == EmptySpace)
            {
                string message = "Cannot manually mark a position using" +
                                 " the default empty marker. Use clearPosition() instead.";
                throw new ArgumentException(message);
            }
            Field[row, column] = marker;
        }

        public void ClearMarker(int row, int column)
        {
            Field[row, column] = EmptySpace;
        }

        public bool IsEmpty(int row, int column)
        {
            return Field[row, column] == EmptySpace;
        }

        public char GetMarkerAtPosition(int row, int column)
        {
            return Field[row, column];
        }
    }
}
