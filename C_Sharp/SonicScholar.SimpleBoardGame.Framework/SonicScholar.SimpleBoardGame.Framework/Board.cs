using System;
using System.Text;

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

        /// <summary>
        /// Helper method used by ToString() to get a string of
        /// characters that matches the board's ASCII representation
        /// width.
        /// </summary>
        /// <param name="c">The character to string together</param>
        /// <returns>String of 'c' length Width*2+1</returns>
        private string GetStringRowOfCharacters(char c)
        {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 2 * Width + 1; i++)
            {
                stringBuilder.Append(c);
            }

            return stringBuilder.ToString();
        }

        /// <summary>
        /// Helper method used to get a row of characters that
        /// represent the state of the board on a given row.
        /// </summary>
        /// <param name="row">The row number of the board</param>
        /// <returns>String representing this row</returns>
        private string GetStringRowOfBoard(int row)
        {
            StringBuilder stringBuilder = new StringBuilder();
            //print all the columns in this row
            for (int col = 0; col < Width; col++)
            {
                char marker = GetMarkerAtPosition(row, col);
                stringBuilder.Append("|" + marker);
            }
            //append closing |
            stringBuilder.Append("|");
            return stringBuilder.ToString();
        }

        /// <summary>
        /// Creates an ASCII representation of the board's state
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            StringBuilder stringBuilder = new StringBuilder();
            //print top row
            stringBuilder.AppendLine(GetStringRowOfCharacters('='));

            //print all the rows
            for (int row = 0; row < Height; row++)
            {
                stringBuilder.AppendLine(GetStringRowOfBoard(row));

                //if it's the last row, print ===='s
                //else print ----'s
                stringBuilder.AppendLine(row == Height - 1
                    ? GetStringRowOfCharacters('=')
                    : GetStringRowOfCharacters('-'));
            }

            return stringBuilder.ToString();
        }
    }
}
