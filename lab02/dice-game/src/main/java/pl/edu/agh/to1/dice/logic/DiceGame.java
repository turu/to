package pl.edu.agh.to1.dice.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiceGame {

    private static final int DICE_COUNT = 5;

    private final List<Player> players = new ArrayList<Player>();
    private final ScoreBoard scoreBoard = new ScoreBoard(players);
    private final DiceBucket diceBucket = new DiceBucket(DICE_COUNT);
    private final GameController controller = new GameController(players, scoreBoard, diceBucket);

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("The Dice Game is about to start!!! Are you excited? I'm not.\n");
        System.out.println("Please enter player 1 nickname: ");
        players.get(0).setName(scanner.nextLine().trim());
        System.out.println("Please enter player 2 nickname: ");
        players.get(1).setName(scanner.nextLine().trim());

        while (!controller.isFinished()) {
            controller.nextRound();
        }

        System.out.println("The game is finished. Do you feel the thrill? I don't.\n");
        System.out.println("Player " + controller.getWinningPlayer() + " wins! How exciting!\n");

        scoreBoard.display();
    }
}
