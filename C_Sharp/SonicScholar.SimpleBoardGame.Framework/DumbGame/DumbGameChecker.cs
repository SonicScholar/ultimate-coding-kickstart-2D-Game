using SonicScholar.SimpleBoardGame.Framework;

namespace DumbGame
{
    public class DumbGameChecker : IGameChecker
    {
        public IGameEngine GameEngine { get; set; }

        public bool IsGameOver
        {
            get
            {
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
        }

        public IPlayer WinningPlayer => null;
    }
}