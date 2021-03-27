package com.sonicscholar.simpleBoardGame;

/**
 * AbstractPlayer provides storage for the name and marker
 * of the Player interface.
 */
public abstract class AbstractPlayer implements Player {

    protected final String _name;
    protected final char _marker;
    public AbstractPlayer(String name, char marker) {
        _name = name;
        _marker = marker;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public char getMarker() {
        return _marker;
    }

}
