package pl.edu.agh.to1.dice.logic.computing;

import pl.edu.agh.to1.dice.logic.Dice;
import pl.edu.agh.to1.dice.logic.DiceBucket;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Piotr Turek
 */
public class SameComputingStrategy implements ComputingStrategy {
    private final int minCount;
    private final List<Integer> valueCount = new ArrayList<Integer>(6);

    public SameComputingStrategy(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public int compute(DiceBucket diceBucket) {
        if (!apply(diceBucket))
            return 0;
        int res = 0;
        for (Dice dice : diceBucket) {
            res += dice.getState();
        }
        return res;
    }

    private boolean apply(DiceBucket diceBucket) {
        for (int i = 0; i < valueCount.size(); i++) valueCount.set(i, 0);
        for (Dice dice : diceBucket) {
            int v = valueCount.get(dice.getState() - 1);
            valueCount.set(dice.getState() - 1, v + 1);
        }

        for (Integer c : valueCount) {
            if (c >= minCount) return true;
        }

        return false;
    }
}
