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
public class GeneralComputingStrategyTest {
    @Test
    public void shouldReturn50() {
        //given
        Dice dice = mock(Dice.class);
        given(dice.getState()).willReturn(5);
        List<Dice> diceList = Arrays.asList(dice, dice, dice, dice, dice);
        DiceBucket diceBucket = mock(DiceBucket.class);
        given(diceBucket.iterator()).willReturn(diceList.iterator());

        //when
        ComputingStrategy strategy = new GeneralComputingStrategy();
        final int result = strategy.compute(diceBucket);

        //then
        verify(diceBucket).iterator();
        assertThat(result).isEqualTo(50);
    }

    @Test
    public void shouldReturn0() {
        //given
        Dice dice = mock(Dice.class);
        given(dice.getState()).willReturn(5);
        Dice dice1 = mock(Dice.class);
        given(dice1.getState()).willReturn(1);
        List<Dice> diceList = Arrays.asList(dice, dice, dice, dice, dice1);
        DiceBucket diceBucket = mock(DiceBucket.class);
        given(diceBucket.iterator()).willReturn(diceList.iterator());

        //when
        ComputingStrategy strategy = new GeneralComputingStrategy();
        final int result = strategy.compute(diceBucket);

        //then
        verify(diceBucket).iterator();
        assertThat(result).isEqualTo(0);
    }
}
