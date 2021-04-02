using System;
using SonicScholar.SimpleBoardGame.Framework;

namespace TreasureGame
{
    public class TreasureGameChecker : IGameChecker
    {
        private Coordinate _treasureLocation;
        public TreasureGameChecker()
        {
        }

        internal void AssignTreasureLocation()
        {
            Random random = new Random();
            IBoard board = GameEngine.Board;

            int row = random.Next(board.Height);
            int col = random.Next(board.Width);

            _treasureLocation = new Coordinate(row, col);
        }

        public Coordinate TreasureLocation => _treasureLocation;

        private IGameEngine _gameEngine;
        public IGameEngine GameEngine
        {
            get => _gameEngine;
            set
            {
                _gameEngine = value;
                AssignTreasureLocation();
            }
        }

        public bool IsGameOver
        {
            get
            {
                IBoard board = GameEngine.Board;
                return !board.IsEmpty(_treasureLocation.Row, _treasureLocation.Column);
            }
        }

        public IPlayer WinningPlayer
        {
            get
            {
                if(!IsGameOver)
                    throw new InvalidOperationException();

                char winningMarker = GameEngine.Board.GetMarkerAtPosition(_treasureLocation.Row, _treasureLocation.Column);
                foreach (var player in GameEngine.Players)
                {
                    if (player.Marker == winningMarker)
                        return player;
                }

                return null;
            }
        }
    }
}