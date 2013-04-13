package pl.edu.agh.to1.dice.logic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Author: Piotr Turek
 */
public class DiceBucket implements Iterable<Dice> {
    private final ArrayList<Dice> dices;
    private final int bucketSize;

    public DiceBucket(int diceCount) {
        bucketSize = diceCount;
        dices = new ArrayList<Dice>(diceCount);

    }

    public Boolean isBlocked(int i) {
        if (i >= getBucketSize()) {
            throw new DiceGameException("Dice nr " + i + " does not exist.");
        }

        return dices.get(i).isBlocked();
    }

    public void setBlocked(int i, boolean value) {
        if (i >= getBucketSize()) {
            throw new DiceGameException("Dice nr " + i + " does not exist.");
        }

        final Dice dice = dices.get(i);

        if (value) dice.block();
        else dice.unblock();
    }

    public void unblockAll() {
        for (Dice dice : dices) {
            dice.unblock();
        }
    }

    public int getBucketSize() {
        return bucketSize;
    }

    public void roll() {
        for (Dice dice : dices) {
            if (!dice.isBlocked()) {
                dice.roll();
            }
        }
    }

    public void display() {
        System.out.print("Dice arrangement: ");
        for (Dice dice : dices) {
            if (dice.isBlocked()) System.out.print("{" + dice.getState() + "}, ");
            else System.out.print(dice.getState() + ", ");
            System.out.print('\n');
        }
    }

    @Override
    public Iterator<Dice> iterator() {
        return dices.iterator();
    }
}
