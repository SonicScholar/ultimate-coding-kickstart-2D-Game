using System;
using SonicScholar.SimpleBoardGame.Framework;

namespace TreasureGame
{
    public class TreasureHuntPlayer : PlayerBase
    {

        public TreasureHuntPlayer(string name, char marker, IBoard board) : base(name, marker)
        {
            Board = board;
        }

        /// <summary>
        /// User inputs a number from 1 to however many spaces are on the board
        /// </summary>
        /// <param name="userInput">int from 1 to num spaces on the board</param>
        /// <param name="width">width of the board</param>
        /// <returns>Coordinate with Row and Column calculated from user input</returns>
        public static Coordinate GetCoordinateFromUserInput(int userInput, int width)
        {
            int result = userInput-1;

            int row = result / width;
            int col = result % width;

            return new Coordinate(row, col);
        }

        internal IBoard Board { get; }

        public override bool IsComputer => false;


        public override IGameAction RequestAction()
        {

            Console.WriteLine(Name + ", please select a number from 1-" + Board.Width * Board.Height);

            for (int row = 0; row < Board.Height; row++)
            {
                for (int col = 0; col < Board.Width; col++)
                {
                    int space = (row * Board.Width + col + 1);
                    Console.Write(space);
                }
                Console.WriteLine();
            }

            string line = Console.ReadLine();

            bool successFullyParsed = false;
            int result=0;
            while (!successFullyParsed)
            {
                successFullyParsed = int.TryParse(line, out result);
            }

            Coordinate coordinate = GetCoordinateFromUserInput(result, Board.Width);

            GameAction gameAction = new GameAction(coordinate.Row, coordinate.Column, Marker);
            return gameAction;
        }
    }
}