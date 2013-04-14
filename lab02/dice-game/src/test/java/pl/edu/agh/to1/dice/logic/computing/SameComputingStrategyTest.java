package pl.edu.agh.to1.dice.logic.computing;

import org.junit.Test;
import pl.edu.agh.to1.dice.logic.Dice;
import pl.edu.agh.to1.dice.logic.DiceBucket;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Author: Piotr Turek
 */
public class SameComputingStrategyTest {
    @Test
    public void shouldComputeCorrectly3() {
        //given
        Dice dice2 = mock(Dice.class);
        given(dice2.getState()).willReturn(2);
        Dice dice3 = mock(Dice.class);
        given(dice3.getState()).willReturn(3);
        List<Dice> diceList = Arrays.asList(dice2, dice3, dice2, dice3, dice3);
        DiceBucket diceBucket = mock(DiceBucket.class);
        given(diceBucket.iterator()).willReturn(diceList.iterator(), diceList.iterator());

        //when
        ComputingStrategy strategy = new SameComputingStrategy(3);
        final int result = strategy.compute(diceBucket);

        //then
        assertThat(result).isEqualTo(13);
    }

    @Test
    public void shouldComputeCorrectly4() {
        //given
        Dice dice2 = mock(Dice.class);
        given(dice2.getState()).willReturn(2);
        Dice dice6 = mock(Dice.class);
        given(dice6.getState()).willReturn(6);
        List<Dice> diceList = Arrays.asList(dice6, dice2, dice6, dice6, dice6);
        DiceBucket diceBucket = mock(DiceBucket.class);
        given(diceBucket.iterator()).willReturn(diceList.iterator(), diceList.iterator());

        //when
        ComputingStrategy strategy = new SameComputingStrategy(4);
        final int result = strategy.compute(diceBucket);

        //then
        assertThat(result).isEqualTo(26);
    }
}
