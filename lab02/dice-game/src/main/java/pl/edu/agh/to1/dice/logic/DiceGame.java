package pl.edu.agh.to1.dice.logic;

import pl.edu.agh.to1.dice.logic.computing.RoundComputer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiceGame {

    private static final int DICE_COUNT = 5;
    private static final int PLAYER_COUNT = 2;
    private static final int MAX_ROLLS_ALLOWED = 3;

    private final List<Player> players = new ArrayList<Player>(PLAYER_COUNT);
    private final DiceBucket diceBucket = new DiceBucket(DICE_COUNT);
    private final RoundComputer roundComputer = new RoundComputer();
    private final ScoreBoard scoreBoard;
    private int roundsPlayed = 0;

    public DiceGame() {
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players.add(new Player());
        }
        scoreBoard = new ScoreBoard(players);
    }

    public void play() {
        System.out.println("The Dice Game is about to start!!! Are you excited? I'm not.\n");

        while (!isFinished()) {
            nextRound();
        }

        System.out.println("The game is finished. Do you feel the thrill? I don't.\n");
        System.out.println("Player " + getWinningPlayer() + " wins! How exciting!\n");

        scoreBoard.display();
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
        return roundsPlayed >= DiceCategory.values().length;
    }

    public void nextRound() {
        if (isFinished()) {
            throw new DiceGameException("The game has already ended. No more moves to make!");
        }

        System.out.println("=================================================================================");
        System.out.printf("                                                                  >>> Round %d <<<%n%n",
                roundsPlayed + 1);

        for (Player p : players) {
            System.out.printf(">>> Player %s moves. <<<\n\n", p);
            scoreBoard.display();
            final int result = makeAMove(p);
            System.out.println("Player's " + p + " result in this round is " + result + "\n");
            scoreBoard.display();
            System.out.printf("%n%n");
        }
        System.out.println("=================================================================================\n\n");

        roundsPlayed++;
    }

    private int makeAMove(Player player) {
        Scanner scanner = new Scanner(System.in);

        makeDiceArrangement(scanner);
        System.out.print("\nFinal dice arrangement: ");
        diceBucket.display();
        System.out.printf("%n%n");

        DiceCategory diceCategory;
        do {
            diceCategory = chooseCategory(scanner);
        } while (scoreBoard.isIllegalMove(player, diceCategory));

        final int result = computeResult(diceCategory);

        scoreBoard.update(player, diceCategory, result);

        return result;
    }

    private int computeResult(DiceCategory diceCategory) {
        return roundComputer.compute(diceCategory, diceBucket);
    }

    private DiceCategory chooseCategory(Scanner scanner) {
        System.out.println("Choose category: 1 | 2 | ... | 6 | 3ki | 4ki | ful | ms | ds | g | sz");
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

    private void makeDiceArrangement(Scanner scanner) {
        int rollsLeft = MAX_ROLLS_ALLOWED;
        while (rollsLeft > 0) {
            diceBucket.roll();
            System.out.print("Current arrangement of dice: ");
            diceBucket.display();
            System.out.printf("%n");
            if (rollsLeft == 1) break;
            System.out.println("Accept this arrangement? [y/n] ");

            if (scanner.nextLine().trim().equals("n")) {
                System.out.print("Enter which dices to block (numbers separated by spaces), f.e.: 1 2 3 4\t> ");
                final String line = scanner.nextLine().trim();
                if (line.length() == 0) {
                    rollsLeft--;
                    continue;
                }
                final String[] split = line.split("\\s+");
                int i = 0;
                for (String s : split) {
                    i = Integer.parseInt(s) - 1;
                    if (i < 0 || i >= diceBucket.getBucketSize()) {
                        System.out.println("No such dice, i = " + i + "\n");
                    } else {
                        diceBucket.setBlocked(i, true);
                    }
                }

                rollsLeft--;
            } else {
                rollsLeft = 0;
            }
        }

        diceBucket.unblockAll();
    }

}
