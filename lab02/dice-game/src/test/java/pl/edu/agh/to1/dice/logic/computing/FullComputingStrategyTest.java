package pl.edu.agh.to1.dice.logic.computing;

import org.junit.Test;
import pl.edu.agh.to1.dice.logic.Dice;
import pl.edu.agh.to1.dice.logic.DiceBucket;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

/**
 * Author: Piotr Turek
 */
public class FullComputingStrategyTest {
    @Test
    public void shouldCorrectlyComputePoints() {
        //given
        Dice dice2 = mock(Dice.class);
        given(dice2.getState()).willReturn(2);
        Dice dice3 = mock(Dice.class);
        given(dice3.getState()).willReturn(3);
        List<Dice> diceList = Arrays.asList(dice2, dice3, dice2, dice3, dice3);
        DiceBucket diceBucket = mock(DiceBucket.class);
        given(diceBucket.iterator()).willReturn(diceList.iterator());

        //when
        ComputingStrategy strategy = new FullComputingStrategy();
        final int result = strategy.compute(diceBucket);

        //then
        verify(diceBucket).iterator();
        assertThat(result).isEqualTo(25);
    }

    @Test
    public void shouldReturnZero() {
        //given
        Dice dice = mock(Dice.class);
        given(dice.getState()).willReturn(5);
        List<Dice> diceList = Arrays.asList(dice, dice, dice, dice, dice);
        DiceBucket diceBucket = mock(DiceBucket.class);
        given(diceBucket.iterator()).willReturn(diceList.iterator());

        //when
        ComputingStrategy strategy = new FullComputingStrategy();
        final int result = strategy.compute(diceBucket);

        //then
        verify(diceBucket).iterator();
        assertThat(result).isEqualTo(0);
    }
}
