using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SonicScholar.SimpleBoardGame.Framework;

namespace TicTacToe
{
    public class NInARowGameChecker : IGameChecker
    {
        /**
         * You need N in a row to win
         */
        public readonly int _numToWin;
        public int NumToWin => _numToWin;

        /*

         */
        /// <summary>
        /// List of vectors (directions) to check when doing the N-in-a-row check.
        /// The 1st value in the tuple represents the change in the row.
        /// The 2nd value in the tuple represents the change in the column.
        /// </summary>
        /// <remarks>
        /// For example, if _numToWin == 4, the vector is (1,0) and we're checking
        /// space(0,0)... the following 4 spaces on the board will be checked to
        ///  see if the markers are identical.          <br></br>
        ///  1: (0,0) row 0, col 0                      <br></br>
        ///  2: (1,0) row 1, col 0                      <br></br>
        ///  3: (2,0) row 2, col 0                      <br></br>
        ///  4: (3,0) row 3, col 0                      <br></br>
        ///  
        /// i.e.                                        <br></br>
        /// vector of (1,0) checks a column for N consecutive markers<br></br>
        /// vector of(0,1) checks a row for N consecutive markers<br></br>
        /// vector of(1,1) checks diagonally down-right for N consecutive markers.<br></br>
        /// vector of(1,-1) checks diagonally down-left for N consecutive markers.
        /// </remarks>
        private readonly List<Tuple<int, int>> _directionalVectors;

        public NInARowGameChecker(int numToWin)
        {
            _numToWin = numToWin;

            //initialize list of tuples for directions to check
            //initial capacity 4 because vectors (right, down-right, down, down-left)
            _directionalVectors = new List<Tuple<int, int>>(4);
            _directionalVectors.Add(new Tuple<int, int>(0, 1));  //right
            _directionalVectors.Add(new Tuple<int, int>(1, 1));   //down-right
            _directionalVectors.Add(new Tuple<int, int>(1, 0));   //down
            _directionalVectors.Add(new Tuple<int, int>(1, -1)); //down-left
        }


        public IGameEngine GameEngine { get; set; }

        /// <summary>
        /// Checks to see if a given space on the board is connected to N
        /// consecutive markers in the right, right-down, down, and down-left vectors.
        /// </summary>
        /// <param name="row">the row of the first space to check</param>
        /// <param name="col">the column of the first space to check</param>
        /// <returns>Character matching player marker if the current space is connected
        ///  to N-in-a-row, null otherwise.</returns>
        private char? CheckSpaceForNConsecutive(int row, int col)
        {
            var board = GameEngine.Board;

            //if the space we're checking isn't valid or is empty, it's
            //obviously not connected to N-in-a-row in ANY vector
            if (!board.IsValidSpace(row, col) || board.IsEmpty(row, col))
                return null;

            //the marker on the space to check. N consecutive markers along
            //the current vector have to match.
            char testMarker = board.GetMarkerAtPosition(row, col);

            //check each directional vector
            foreach (var vector in _directionalVectors)
            {
                int rowDelta = vector.Item1; //the amount to change the row by
                int colDelta = vector.Item2; //the amount to change the column by

                //start at i=1 because we already checked the validity of the
                //first space above.
                for (int i = 1; i < _numToWin; i++)
                {
                    int rowToCheck = row + (i * rowDelta);
                    int columnToCheck = col + (i * colDelta);

                    if (!board.IsValidSpace(rowToCheck, columnToCheck) ||
                            board.IsEmpty(rowToCheck, columnToCheck))
                        break;

                    char currentMarker = board.GetMarkerAtPosition(rowToCheck, columnToCheck);

                    //this space + current vector does not have N in a row
                    //break out of this loop and check the next vector
                    if (testMarker != currentMarker)
                        break;

                    //if we're on the last marker and it matches, then we found a winner!
                    if (i == _numToWin - 1)
                        return testMarker;
                }
            }

            //we checked everything and didn't find a match
            return null;
        }

        public bool IsGameOver
        {
            get
            {

                //the game is over if any of the spaces on the board are part of
                //n-in-a-row
                var board = GameEngine.Board;
                for (int row = 0; row < board.Height; row++)
                {
                    for (int col = 0; col < board.Width; col++)
                    {
                        if (CheckSpaceForNConsecutive(row, col) != null)
                            return true;
                    }
                }

                //...or if all the spaces are filled out
                return AllSpacesOccupied();
            }
        }

        private bool AllSpacesOccupied()
        {
            //check all the spaces, return false if any are
            var board = GameEngine.Board;
            for (int row = 0; row < board.Height; row++)
            {
                for (int col = 0; col < board.Width; col++)
                {
                    if (board.IsEmpty(row, col))
                        return false;
                }
            }
            return true;
        }

        public IPlayer WinningPlayer
        {
            get
            {
                var board = GameEngine.Board;
                for (int row = 0; row < board.Height; row++)
                {
                    for (int col = 0; col < board.Width; col++)
                    {
                        char? marker = CheckSpaceForNConsecutive(row, col);
                        if (marker != null)
                            return GetPlayer((char)marker);
                    }
                }
                return null;
            }
        }

        /**
         * helper function to find the player that matches the character marker
         * @param marker char that represents a player's marker
         * @return first player object from the game engine with a matching marker
         */
        private IPlayer GetPlayer(char marker)
        {
            foreach (var player in GameEngine.Players)
            {
                if (player.Marker == marker)
                    return player;
            }
            return null;
        }
    }
}
