package pl.edu.agh.to1.dice.logic.computing;

import pl.edu.agh.to1.dice.logic.DiceBucket;
import pl.edu.agh.to1.dice.logic.DiceCategory;

/**
 * Author: Piotr Turek
 */
public class RoundComputer {
    public int compute(DiceCategory diceCategory, DiceBucket diceBucket) {
        ComputingStrategy strategy = null;
        switch (diceCategory) {
            case ONE:
                strategy = new SumComputingStrategy(1);
                break;
            case TWO:
                strategy = new SumComputingStrategy(2);
                break;
            case THREE:
                strategy = new SumComputingStrategy(3);
                break;
            case FOUR:
                strategy = new SumComputingStrategy(4);
                break;
            case FIVE:
                strategy = new SumComputingStrategy(5);
                break;
            case SIX:
                strategy = new SumComputingStrategy(6);
                break;
            case SAME_THREE:
                strategy = new SameComputingStrategy(3);
                break;
            case SAME_FOUR:
                strategy = new SameComputingStrategy(4);
                break;
            case GENERAL:
                strategy = new GeneralComputingStrategy();
                break;
            case BIG_STREET:
                strategy = new SequenceComputingStrategy(40, 5);
                break;
            case LITTLE_STREET:
                strategy = new SequenceComputingStrategy(30, 4);
                break;
            case FULL:
                strategy = new FullComputingStrategy();
                break;
            case CHANCE:
                strategy = new ChanceComputingStrategy();
                break;
        }

        return strategy.compute(diceBucket);
    }
}
