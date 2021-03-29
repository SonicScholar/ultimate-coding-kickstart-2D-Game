namespace SonicScholar.SimpleBoardGame.Framework
{
    /// <summary>
    /// PlayerBase provides storage for the name and
    /// marker of the IPlayer interface
    /// </summary>
    public abstract class PlayerBase : IPlayer
    {
        protected PlayerBase(string name, char marker)
        {
            Name = name;
            Marker = marker;
        }
        public string Name { get; }
        public char Marker { get; }
        public abstract bool IsComputer { get; }
        public abstract IGameAction RequestAction();
    }
}
