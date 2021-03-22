# Ultimate Coding Kickstart 2D-Game

## Overview

### Introduction
Many games take the form of a simple 2D game space with markers placed down by different players. Using Object Oriented Programming, we can design reusable components which could be used in turn based games like Tic-Tac-Toe, Connect 4, or Othello (a.k.a. Reversi)

With a little bit of extension, this framework could also support turn based games where pieces move on the board (like checkers, chess, or Stratego)

### Programming Assignment

For this programming assignment, we're going to use this framework to practice using Object Oriented Programming principles. We'll see how to use interfaces and parent classes to reduce code duplication and write code abstractly for many simple turn-based 2 player games.

-----------

## Class Decomposition

### Board

This is a 2D grid that represents the space for the game to take place. It's a rectangle of size Width by Height.
The spaces are denoted by a 2D array of characters. This could be 'X' or 'O' for Tic-Tac-Toe, or 'R' or 'B' (Red/Black) for Connect 4, or ' ' (space) in case the tile is empty. The board should have methods for clearing it, checking if a space is occupied, or placing a marker down in a certain position.

### IGameAction

A game action is a representation of a move or part of a move that a player might make in the course of his/her turn.
The default GameAction class represents putting a marker down on a specific tile. The action may or may not be valid for the particular game being played, but validation of correct actions is not this class' responsibility.

### IGameChecker

The IGameChecker interface's primary purpose is to provide functionality that can check if the game is over or not. If the game is over it can provide which player is the winner of the game.

### IPlayer/PlayerBase

The IPlayer interface exposes 3 things for a player. Name, Default Marker, and a method to request the next game action from a player. Since Name and Default Marker are easy to implement, they are captured in an abstract class, PlayerBase (or AbstractPlayer in Java). The GetAction is left abstract for a subclass to implement. This allows for easy extension of human or AI player classes.

### IGameEngine

This interface represents the guts of the business logic required to play the game. It can be assigned a custom IGameChecker which will presumably be used as the business logic for determining when a game is finished. It also exposes methods to start and reset a game. It should also keep track of the state of turns for a player so it always knows whose turn is next. A critical piece is that it can validate game actions requested from players to make sure that those actions are valid before allowing the turn to continue. Finally DoTurn() represents the main business logic for requesting an action from a player, validating and re-requesting if necessary, then carrying out the requested turn by changing the state of the board appropriately.

### SimpleTurnBasedGameEngine

As the name suggests, this is a simple engine that will give one turn per player. After each turn the game will be checked for being finished. If more complex business logic is needed to give a player more than one turn, or to reverse the turn order, a different class must be created which implements the IGameEngine interface.

