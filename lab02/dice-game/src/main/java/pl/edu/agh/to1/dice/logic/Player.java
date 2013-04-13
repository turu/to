package pl.edu.agh.to1.dice.logic;

/**
 * Author: Piotr Turek
 */
public class Player {
    private String name = "";
    private int points = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return getName();
    }
}
