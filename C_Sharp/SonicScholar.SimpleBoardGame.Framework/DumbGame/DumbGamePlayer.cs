using System;
using SonicScholar.SimpleBoardGame.Framework;

namespace DumbGame
{
    public class DumbGamePlayer : PlayerBase
    {
        public DumbGamePlayer(string name, char marker) : base(name, marker)
        {
        }

        public override bool IsComputer => false;
        public override IGameAction RequestAction()
        {
            Console.WriteLine(Name+ ", select a space (1-9):");
            Console.WriteLine("1 2 3");
            Console.WriteLine("4 5 6");
            Console.WriteLine("7 8 9");

            bool validNumber;
            GameAction gameAction;
            do
            {
                var key = Console.ReadKey(true);
                //creative way to convert char to string for parsing
                string input = new string(key.KeyChar,1);
                validNumber = int.TryParse(input, out int result);

                int row = (result - 1) / 3;
                int col = (result - 1) % 3;
                gameAction = new GameAction(row, col, Marker);
            } while (!validNumber);

            return gameAction;
        }
    }
}