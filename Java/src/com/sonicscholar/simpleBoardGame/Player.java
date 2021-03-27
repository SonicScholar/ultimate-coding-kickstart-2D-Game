package com.sonicscholar.simpleBoardGame;

public interface Player {
    String getName();
    char getMarker();
    boolean isComputer();
    GameAction requestAction();

}
