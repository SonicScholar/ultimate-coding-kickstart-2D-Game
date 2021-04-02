using System;
using System.Collections.Generic;
using SonicScholar.SimpleBoardGame.Framework;

namespace TreasureGame
{
    public class TreasureHuntAIPlayer : PlayerBase
    {
        
        public TreasureHuntAIPlayer(string name, char marker, IBoard board) : base(name, marker)
        {
            Board = board;
        }

        internal IBoard Board { get; set; }

        public override bool IsComputer => true;
        
        public override IGameAction RequestAction()
        {
            List<Coordinate> emptySpaces = new List<Coordinate>();
            //tally up empty spaces
            for (int row = 0; row < Board.Height; row++)
            {
                for (int col = 0; col < Board.Width; col++)
                {
                    if (Board.IsEmpty(row, col))
                    {
                        Coordinate emptySpace = new Coordinate(row, col);
                        emptySpaces.Add(emptySpace);
                    }
                }
            }

            //randomly select space
            Random random = new Random();
            int randomIndex = random.Next(emptySpaces.Count);
            Coordinate desiredCoordinate = emptySpaces[randomIndex];

            return new GameAction(desiredCoordinate.Row, desiredCoordinate.Column, Marker);
        }
    }
}