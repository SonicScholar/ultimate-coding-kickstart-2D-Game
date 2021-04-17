using System;
using System.Collections.Generic;
using SonicScholar.SimpleBoardGame.Framework;

namespace TicTacToe
{
    
    public class Program
    {
        [STAThread]
        public static void Main(string[] args)
        {
            var board = new Board(3, 3);

            //set up the players
            Console.WriteLine("Welcome to Tic Tac Toe!");
            Console.WriteLine("Please Enter Player 1's name (X):");

            String playerName = Console.ReadLine();
            var player1 = new TicTacToePlayer(playerName, 'X', board);

            Console.WriteLine("Please Enter Player 2's name (O):");
            playerName = Console.ReadLine();
            var player2 = new TicTacToePlayer(playerName, 'O', board);
            List<IPlayer> players = new List<IPlayer>();
            players.Add(player1);
            players.Add(player2);

            //set up turn based engine and play
            IGameEngine engine = new GameEngine(players, board);
            engine.GameChecker = new NInARowGameChecker(3);
            engine.PlayGame();

            var winner = engine.GameChecker.WinningPlayer;
            Console.WriteLine("The winning player was: " + (winner == null ? "nobody" : winner.Name) + "!");

            //press enter key to exit the program
            Console.ReadLine();
        }
    }
}