package pl.edu.agh.to1.dice.logic.computing;

import pl.edu.agh.to1.dice.logic.Dice;
import pl.edu.agh.to1.dice.logic.DiceBucket;

/**
 * Author: Piotr Turek
 */
public class ChanceComputingStrategy implements ComputingStrategy {
    @Override
    public int compute(DiceBucket diceBucket) {
        int res = 0;
        for (Dice dice : diceBucket) {
            res += dice.getState();
        }
        return res;
    }
}
