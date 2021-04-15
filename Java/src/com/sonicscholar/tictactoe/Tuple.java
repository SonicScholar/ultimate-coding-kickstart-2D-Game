package com.sonicscholar.tictactoe;

/**
 * Generic utility class for storing 2 objects
 * @param <T1>
 * @param <T2>
 */
public class Tuple<T1, T2> {
    public final T1 value1;
    public final T2 value2;

    public Tuple(T1 t1, T2 t2){
        value1 = t1;
        value2 = t2;
    }

    @Override
    public String toString() {
        return "(" + value1.toString() + ", " + value2.toString() + ")";
    }
}
