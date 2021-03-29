using System.Collections.Generic;

namespace SonicScholar.SimpleBoardGame.Framework.Test.Mock
{
    class MockGameChecker : IGameChecker
    {
        public IGameEngine GameEngine { get; set; }

        public bool IsGameOver
        {
            get
            {
                //game is over when all spaces filled up
                IBoard board = GameEngine.Board;
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
        }

        public IPlayer WinningPlayer
        {
            get
            {
                //lookup table of players and marker, for quick reference
                //also lookup table of player and marker count
                var players = GameEngine.Players;
                Dictionary<char, IPlayer> characterPlayerMap = new Dictionary<char, IPlayer>();
                Dictionary<IPlayer, int> playerMarkerCountMap = new Dictionary<IPlayer, int>();
                foreach (var p in players)
                {
                    characterPlayerMap[p.Marker] = p;
                    playerMarkerCountMap[p] = 0;
                }

                //count up all the markers on the board
                IBoard board = GameEngine.Board;
                for (int row = 0; row < board.Height; row++)
                {
                    for (int col = 0; col < board.Width; col++)
                    {
                        if (!board.IsEmpty(row, col))
                        {
                            //get the player who belongs to that marker
                            char marker = board.GetMarkerAtPosition(row, col);
                            IPlayer p = characterPlayerMap[marker];

                            //increment the count for that player
                            int count = playerMarkerCountMap[p];
                            playerMarkerCountMap[p] = count + 1;
                        }
                    }
                }

                //see which one had the max
                int max = -1;
                IPlayer winner = null;
                foreach (IPlayer p in players)
                {
                    int markerCount = playerMarkerCountMap[p];
                    if (markerCount > max)
                    {
                        max = markerCount;
                        winner = p;
                    }
                    else if (markerCount == max)
                    {
                        //no winner in case of a tie (equal number of squares)
                        winner = null;
                    }
                }
                return winner;
            }
        }
    }
}
