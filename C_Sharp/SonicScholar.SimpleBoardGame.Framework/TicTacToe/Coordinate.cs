using System;

namespace TicTacToe
{
    /// <summary>
    /// This is a class to keep track of a row and column
    /// for a board space
    /// </summary>
    public class Coordinate : Tuple<int, int>
    {

        public int Row => this.Item1;
        public int Column => this.Item2;

        public Coordinate(int row, int column)
        : base(row, column)
        {
        }

    }
}