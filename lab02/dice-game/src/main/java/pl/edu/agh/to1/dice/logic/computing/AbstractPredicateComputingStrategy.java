package pl.edu.agh.to1.dice.logic.computing;

import pl.edu.agh.to1.dice.logic.DiceBucket;

/**
 * Author: Piotr Turek
 */
public abstract class AbstractPredicateComputingStrategy implements ComputingStrategy {
    private final int reward;

    public AbstractPredicateComputingStrategy(int reward) {
        this.reward = reward;
    }

    @Override
    public int compute(DiceBucket diceBucket) {
        if (apply(diceBucket)) {
            return reward;
        }
        return 0;
    }

    public abstract boolean apply(DiceBucket diceBucket);
}
