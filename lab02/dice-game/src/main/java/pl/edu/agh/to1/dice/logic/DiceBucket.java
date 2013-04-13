package pl.edu.agh.to1.dice.logic;

import java.util.ArrayList;

/**
 * Author: Piotr Turek
 */
public class DiceBucket {
    private final ArrayList<Dice> dices;
    private final ArrayList<Boolean> blocked;
    private final int bucketSize;

    public DiceBucket(int diceCount) {
        bucketSize = diceCount;
        dices = new ArrayList<Dice>(diceCount);
        blocked = new ArrayList<Boolean>(diceCount);

    }

    public Boolean isBlocked(int i) {
        if (i >= getBucketSize()) {
            throw new DiceGameException("Dice nr " + i + " does not exist.");
        }

        return blocked.get(i);
    }

    public void setBlocked(int i, boolean value) {
        if (i >= getBucketSize()) {
            throw new DiceGameException("Dice nr " + i + " does not exist.");
        }

        blocked.set(i, value);
    }

    public void unblockAll() {
        for (int i = 0; i < bucketSize; i++) {
            setBlocked(i, false);
        }
    }

    public int getBucketSize() {
        return bucketSize;
    }

    public void roll() {
        for (int i = 0; i < bucketSize; i++) {
            if (!isBlocked(i)) {
                dices.get(i).roll();
            }
        }
    }

    public void display() {

    }
}
