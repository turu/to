package pl.edu.agh.to1.dice.logic;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Piotr Turek
 */
public class ScoreBoard {
    private final static int BONUS_TRIGGER = 63;
    private final static int BONUS = 35;

    private final Map<Player, Map<DiceCategory, Integer>> scores = new HashMap<Player, Map<DiceCategory, Integer>>();
    private final Map<Player, Integer> bonuses = new HashMap<Player, Integer>();
    private final Map<Player, Integer> upperSum = new HashMap<Player, Integer>();
    private final Map<Player, Integer> bottomSum = new HashMap<Player, Integer>();
    private final List<Player> players;

    public ScoreBoard(List<Player> players) {
        this.players = players;
        for (Player p : players) {
            Map<DiceCategory, Integer> playerScore = new EnumMap<DiceCategory, Integer>(DiceCategory.class);
            for (DiceCategory cat : DiceCategory.values()) {
                playerScore.put(cat, -1);
            }
            scores.put(p, playerScore);
            bonuses.put(p, 0);
            upperSum.put(p, 0);
            bottomSum.put(p, 0);
        }
    }

    public void display() {
        System.out.printf("%15s", "");
        for (Player p : players) {
            System.out.printf(" | %-2s", p);
        }
        System.out.printf("%n");
        linebreak();

        displaySubtable(0, DiceCategory.SIX.ordinal());
        linebreak();
        System.out.printf("%15s", "premia");
        displaySummary(bonuses);
        System.out.printf("%15s", "suma");
        displaySummary(upperSum);
        linebreak();
        displaySubtable(DiceCategory.SIX.ordinal()+1, DiceCategory.values().length-1);
        linebreak();
        System.out.printf("%15s", "suma");
        displaySummary(bottomSum);
        System.out.printf("%15s", "razem");
        for (Player p : players) {
            System.out.printf(" | %-2s", upperSum.get(p) + bottomSum.get(p));
        }
        System.out.printf("%n%n");
    }

    private void linebreak() {
        System.out.printf("%15s", "------------");
        for (Player p : players) {
            System.out.printf("%5s", "-----");
        }
        System.out.printf("%5s%n", "-----");
    }

    private void displaySummary(Map<Player, Integer> pointsMap) {
        for (Player p : players) {
            System.out.printf(" | %-2s", pointsMap.get(p));
        }
        System.out.printf("%n");
    }

    private void displaySubtable(int l, int r) {
        final DiceCategory[] diceCategories = DiceCategory.values();
        for (int i = l; i <= r; i++) {
            final DiceCategory diceCategory = diceCategories[i];
            System.out.printf("%15s", diceCategory.getDescription());
            for (Player p : players) {
                final Integer curVal = scores.get(p).get(diceCategory);
                String strVal = "";
                if (curVal != -1) {
                    strVal = curVal.toString();
                }
                System.out.printf(" | %-2s", strVal);
            }
            System.out.printf("%n");
        }
    }

    public void update(Player player, DiceCategory diceCategory, int points) {
        final Map<DiceCategory, Integer> playerScore = scores.get(player);
        final Integer curVal = playerScore.get(diceCategory);
        if (curVal != -1) {
            throw new DiceGameException("Illegal move for player " + player + " and category " +
                    diceCategory.getDescription() + "!");
        }

        playerScore.put(diceCategory, points);
        Integer sumVal;
        if (diceCategory.ordinal() <= DiceCategory.SIX.ordinal()) {
            sumVal = upperSum.get(player);
            upperSum.put(player, sumVal + points);
            if (sumVal + points >= BONUS_TRIGGER) {
                bonuses.put(player, BONUS);
            }
        } else {
            sumVal = bottomSum.get(player);
            bottomSum.put(player, sumVal + points);
        }
        player.setPoints(upperSum.get(player) + bottomSum.get(player) + bonuses.get(player));
    }

    public boolean isIllegalMove(Player player, DiceCategory diceCategory) {
        final Map<DiceCategory, Integer> playerScore = scores.get(player);
        final Integer curVal = playerScore.get(diceCategory);
        return curVal != -1;
    }
}
