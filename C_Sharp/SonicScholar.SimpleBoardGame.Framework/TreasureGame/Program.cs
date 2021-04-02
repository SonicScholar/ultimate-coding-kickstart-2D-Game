using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SonicScholar.SimpleBoardGame.Framework;

namespace TreasureGame
{
    class Program
    {
        static void Main(string[] args)
        {
            Board board = new Board(3, 3);
            //TreasureHuntAIPlayer p1 = new TreasureHuntAIPlayer("Wall-E", 'X', board);
            TreasureHuntAIPlayer p2 = new TreasureHuntAIPlayer("Chappie", 'O', board);

            TreasureHuntPlayer p1 = new TreasureHuntPlayer("Collin", 'X', board);
            //TreasureHuntPlayer p2 = new TreasureHuntPlayer("Mark", 'O', board);

            List<IPlayer> players = new List<IPlayer>();
            players.Add(p1);
            players.Add(p2);

            IGameEngine gameEngine = new GameEngine(players, board);
            IGameChecker gameChecker = new TreasureGameChecker();
            gameEngine.GameChecker = gameChecker;

            gameEngine.PlayGame();

            IPlayer winner = gameChecker.WinningPlayer;

            string winnerName = winner == null ? "Nobody" : winner.Name;
            Console.WriteLine(winnerName + " was the winner!");

            Console.ReadKey();
        }
    }
}
