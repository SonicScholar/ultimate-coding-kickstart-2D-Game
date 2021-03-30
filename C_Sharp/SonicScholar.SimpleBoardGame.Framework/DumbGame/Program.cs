using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SonicScholar.SimpleBoardGame.Framework;

namespace DumbGame
{
    class Program
    {
        static void Main(string[] args)
        {
            DumbGamePlayer player1 = new DumbGamePlayer("Collin", 'X');
            DumbGamePlayer player2 = new DumbGamePlayer("Jim", 'O');
            List<IPlayer> players = new List<IPlayer>()
            {
                player1, player2
            };
            GameEngine engine = new GameEngine(players, new Board(3, 3));
            engine.GameChecker = new DumbGameChecker();

            engine.PlayGame();

            var winner = engine.GameChecker.WinningPlayer;
            Console.WriteLine("The winning player was: " + (winner == null ? "nobody" : winner.Name) + "!");
            Console.ReadLine();
        }
    }
}
