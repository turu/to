package pl.edu.agh.to1.dice.logic;

import java.util.List;
import java.util.Scanner;

/**
 * Author: Piotr Turek
 */
public class GameController {
    private static final int MAX_ROLLS_ALLOWED = 3;

    private final List<Player> players;
    private final ScoreBoard scoreBoard;
    private final DiceBucket diceBucket;
    private int roundsPlayed = 0;

    public GameController(List<Player> players, ScoreBoard scoreBoard, DiceBucket diceBucket) {
        this.players = players;
        this.scoreBoard = scoreBoard;
        this.diceBucket = diceBucket;
    }

    public Player getWinningPlayer() {
        Player best = players.get(0);

        for (Player p : players) {
            if (p.getPoints() > best.getPoints()) {
                best = p;
            }
        }

        return best;
    }

    public boolean isFinished() {
        return roundsPlayed < 13;
    }

    public void nextRound() {
        if (isFinished()) {
            throw new DiceGameException("The game has already ended. No more moves to make!");
        }

        for (Player p : players) {
            scoreBoard.display();
            final int result = makeAMove(p);
            System.out.println("Player's " + p.getName() + " result in this round is " + result);
        }

        roundsPlayed++;
    }

    private int makeAMove(Player player) {
        Scanner scanner = new Scanner(System.in);

        getDiceArrangement(scanner);

        final DiceCategory diceCategory = chooseCategory(scanner);

        return computeResult(diceCategory);

    }

    private int computeResult(DiceCategory diceCategory) {

    }

    private DiceCategory chooseCategory(Scanner scanner) {
        System.out.println("Choose category: 1 | 2 | ... | 6 | 3ki | 4ki | ful | ms | ds | g | sz\n");
        final String cat = scanner.nextLine().trim();
        if (cat.equals("1")) {
            return DiceCategory.ONE;
        } else if (cat.equals("2")) {
            return DiceCategory.TWO;
        } else if (cat.equals("3")) {
            return DiceCategory.THREE;
        } else if (cat.equals("4")) {
            return DiceCategory.FOUR;
        } else if (cat.equals("5")) {
            return DiceCategory.FIVE;
        } else if (cat.equals("6")) {
            return DiceCategory.SIX;
        } else if (cat.equals("3ki")) {
            return DiceCategory.SAME_THREE;
        } else if (cat.equals("4ki")) {
            return DiceCategory.SAME_FOUR;
        } else if (cat.equals("ful")) {
            return DiceCategory.FULL;
        } else if (cat.equals("ms")) {
            return DiceCategory.LITTLE_STREET;
        } else if (cat.equals("ds")) {
            return DiceCategory.BIG_STREET;
        } else if (cat.equals("g")) {
            return DiceCategory.GENERAL;
        } else if (cat.equals("sz")) {
            return DiceCategory.CHANCE;
        }

        return DiceCategory.ONE;
    }

    private void getDiceArrangement(Scanner scanner) {
        int rollsLeft = MAX_ROLLS_ALLOWED;
        while (rollsLeft > 0) {
            diceBucket.roll();
            System.out.println("Current arrangement of dice:\n");
            diceBucket.display();
            System.out.println("Accept arrangement? [y/n] ");

            if (!scanner.nextBoolean()) {
                System.out.println("Enter which dices to block (numbers separated by spaces), f.e.: 1 2 3 4\n");
                final String[] split = scanner.nextLine().trim().split("\\w+");
                int i = 0;
                for (String s : split) {
                    i = Integer.parseInt(s);
                    if (i < 0 || i >= diceBucket.getBucketSize()) {
                        System.out.println("No such dice, i = " + i + "\n");
                    } else {
                        diceBucket.setBlocked(i, true);
                    }
                }

                diceBucket.display();
                rollsLeft--;
            } else {
                rollsLeft = 0;
            }
        }
    }

}
