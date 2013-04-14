package pl.edu.agh.to1.dice.logic.computing;

import pl.edu.agh.to1.dice.logic.Dice;
import pl.edu.agh.to1.dice.logic.DiceBucket;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Piotr Turek
 */
public class FullComputingStrategy extends AbstractPredicateComputingStrategy implements ComputingStrategy {
    private final List<Integer> valueCount = new ArrayList<Integer>(6);

    public FullComputingStrategy() {
        super(25);
        for (int i = 0; i < 6; i++) valueCount.add(0);
    }

    @Override
    public boolean apply(DiceBucket diceBucket) {
        for (int i = 0; i < valueCount.size(); i++) valueCount.set(i, 0);
        for (Dice dice : diceBucket) {
            int v = valueCount.get(dice.getState() - 1);
            valueCount.set(dice.getState() - 1, v + 1);
        }
        return valueCount.contains(3) && valueCount.contains(2);
    }
}
