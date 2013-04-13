package pl.edu.agh.to1.dice.logic.computing;

import pl.edu.agh.to1.dice.logic.Dice;
import pl.edu.agh.to1.dice.logic.DiceBucket;

/**
 * Author: Piotr Turek
 */
public class GeneralComputingStrategy extends AbstractPredicateComputingStrategy implements ComputingStrategy {

    public GeneralComputingStrategy() {
        super(50);
    }

    @Override
    public boolean apply(DiceBucket diceBucket) {
        int prevState = 0;
        for (Dice dice : diceBucket) {
            if (prevState != 0 && dice.getState() != prevState) return false;
            prevState = dice.getState();
        }
        return true;
    }
}
