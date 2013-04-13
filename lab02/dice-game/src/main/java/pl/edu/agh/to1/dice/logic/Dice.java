package pl.edu.agh.to1.dice.logic;

import java.util.Random;

/**
 * Author: Piotr Turek
 */
public class Dice {
    private int state = 1;
    private final Random generator = new Random();
    private boolean blocked = false;

    public int getState() {
        return state;
    }

    public int roll() {
        state = generator.nextInt(5) + 1;
        return state;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }
}