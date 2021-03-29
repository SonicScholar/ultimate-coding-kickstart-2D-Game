using System;
using System.Collections.Generic;

namespace SonicScholar.SimpleBoardGame.Framework
{
    /// <summary>
    /// A simple implementation of IGameEngine that takes N players and makes each player
    /// take a turn in order until the game is over as specified by the IGameChecker
    /// </summary>
    public class GameEngine : IGameEngine
    {

        protected int _currentTurn;

        /// <summary>
        /// Creates a new GameEngine with a list of players, and a board
        /// to play on. User must set the GameChecker separately.
        /// </summary>
        /// <param name="players">A list of players who will play the game</param>
        /// <param name="board">A board to play the game on</param>
        public GameEngine(IList<IPlayer> players, IBoard board)
        {
            Players = players;
            Board = board;
        }

        private IGameChecker _gameChecker;
        public IGameChecker GameChecker
        {
            get => _gameChecker;
            set
            {
                _gameChecker = value;
                _gameChecker.GameEngine = this;
            }
        }

        public IBoard Board { get; }
        public IList<IPlayer> Players { get; }
        public void ResetGame()
        {
            Board.ResetBoard();
            _currentTurn = 0;
        }

        public void PlayGame()
        {
            do
            {
                DoTurn();
            } while (!_gameChecker.IsGameOver);
        }

        public IPlayer GetNextTurnPlayer()
        {
            int numPlayers = Players.Count;
            return Players[_currentTurn % numPlayers];
        }

        public bool IsValidGameAction(IGameAction gameAction)
        {
            //default engine behavior is that all game moves on empty spaces are
            //allowed.
            if (gameAction == null)
                return false;

            int row = gameAction.Row;
            int column = gameAction.Column;

            //Game moves outside index of board dimensions are invalid.
            if (row < 0 || row >= Board.Height)
                return false;
            else if (column < 0 || column >= Board.Width)
                return false;

            return Board.IsEmpty(row, column);
        }

        public void DoTurn()
        {
            //get the next player, and increment
            IPlayer player = GetNextTurnPlayer();
            _currentTurn++;

            IGameAction action;
            bool isValid;
            //Considering what happens here if player keeps requesting
            //invalid actions. Not a big deal if it's a human player
            //but an AI player may cause infinite loops.

            //One solution is to add a isComputer() boolean to Player
            //interface, and throw an exception if an AI player screws up.
            do
            {
                action = player.RequestAction();
                isValid = IsValidGameAction(action);
                if (player.IsComputer && !isValid)
                    throw new InvalidOperationException("AI player requested invalid action");
            } while (!isValid);

            //action is valid at this point
            Board.MarkPosition(action.Row, action.Column, action.Marker);
        }
    }
}
