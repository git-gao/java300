package com.java.interview.containers;

public class Ant2 extends Ant {

    public Ant2(int n) {
        super(n);
    }

    public int hashCode() {
        return number;
    }

    public boolean equals(Object o) {
        return o instanceof Ant2 && (number == ((Ant2) o).number);
    }
}
