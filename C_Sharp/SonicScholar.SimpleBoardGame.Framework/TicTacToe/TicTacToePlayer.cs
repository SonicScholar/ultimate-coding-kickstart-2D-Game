using System;
using SonicScholar.SimpleBoardGame.Framework;

namespace TicTacToe
{
    public class TicTacToePlayer : PlayerBase
    {

    private readonly Board _board;

    public TicTacToePlayer(string name, char marker, Board board)
    :base(name, marker)
    {
        _board = board;
    }

    public static Coordinate getCoordinateFromUserInput(int userInput, int width)
    {
        userInput--;

        int row = userInput / width;
        int col = userInput % width;

        return new Coordinate(row, col);
    }

    public override bool IsComputer => false;

    public override IGameAction RequestAction()
    {

        int totalSpaces = _board.Width * _board.Height;
        Console.WriteLine(Name + ", please select a space: 1-" + totalSpaces);
        for (int row = 0; row < _board.Height; row++)
        {
            for (int col = 0; col < _board.Width; col++)
            {
                Console.Write(row * _board.Width + col + 1);
            }
            Console.WriteLine();
        }


        int userInput = int.Parse(Console.ReadLine());
        Coordinate c = getCoordinateFromUserInput(userInput, _board.Width);
        return new GameAction(c.Row, c.Column, Marker);
    }
    }
}