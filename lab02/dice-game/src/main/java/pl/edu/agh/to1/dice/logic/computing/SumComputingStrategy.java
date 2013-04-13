package pl.edu.agh.to1.dice.logic.computing;

import pl.edu.agh.to1.dice.logic.Dice;
import pl.edu.agh.to1.dice.logic.DiceBucket;

/**
 * Author: Piotr Turek
 */
public class SumComputingStrategy implements ComputingStrategy {
    private final int value;

    public SumComputingStrategy(int value) {
        this.value = value;
    }

    @Override
    public int compute(DiceBucket diceBucket) {
        int res = 0;
        for (Dice dice : diceBucket) {
            if (dice.getState() == value) {
                res += value;
            }
        }

        return res;
    }
}
