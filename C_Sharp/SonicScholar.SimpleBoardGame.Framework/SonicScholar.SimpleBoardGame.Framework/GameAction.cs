namespace SonicScholar.SimpleBoardGame.Framework
{
    /// <summary>
    /// The default implementation of IGameAction
    /// </summary>
    public class GameAction : IGameAction
    {
        public GameAction(int row, int column, char marker)
        {
            Row = row;
            Column = column;
            Marker = marker;
        }
        public int Row { get; }
        public int Column { get; }
        public char Marker { get; }
    }
}
