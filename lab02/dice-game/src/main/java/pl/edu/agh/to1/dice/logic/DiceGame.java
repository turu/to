package pl.edu.agh.to1.dice.logic;

import pl.edu.agh.to1.dice.logic.computing.RoundComputer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiceGame {

    private static final int DICE_COUNT = 5;
    private static final int PLAYER_COUNT = 2;

    private final List<Player> players = new ArrayList<Player>(PLAYER_COUNT);
    private final DiceBucket diceBucket = new DiceBucket(DICE_COUNT);
    private final ScoreBoard scoreBoard;
    private final GameController controller;

    public DiceGame() {
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players.add(new Player());
        }
        scoreBoard = new ScoreBoard(players);
        controller = new GameController(players, scoreBoard, diceBucket, new RoundComputer());
    }

    public void play() {
        System.out.println("The Dice Game is about to start!!! Are you excited? I'm not.\n");

        while (!controller.isFinished()) {
            controller.nextRound();
        }

        System.out.println("The game is finished. Do you feel the thrill? I don't.\n");
        System.out.println("Player " + controller.getWinningPlayer() + " wins! How exciting!\n");

        scoreBoard.display();
    }
}
