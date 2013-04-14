package pl.edu.agh.to1.dice.logic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Author: Piotr Turek
 */
public class ScoreBoardTest {
    @Test
    public void shouldUpdatePlayersPointsAfterUpperMoveWithBonus() {
        //given
        Player player = new Player();
        List<Player> playerList = Arrays.asList(player);
        ScoreBoard board = new ScoreBoard(playerList);

        //when
        board.update(player, DiceCategory.FOUR, 63);

        //then
        assertThat(player.getPoints()).isEqualTo(35 + 63);
    }

    @Test
    public void shouldUpdatePlayersPointsAfterBottomMove() {
        //given
        Player player = new Player();
        List<Player> playerList = Arrays.asList(player);
        ScoreBoard board = new ScoreBoard(playerList);

        //when
        board.update(player, DiceCategory.GENERAL, 50);

        //then
        assertThat(player.getPoints()).isEqualTo(50);
    }
}
