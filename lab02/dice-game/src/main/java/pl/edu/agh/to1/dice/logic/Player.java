package pl.edu.agh.to1.dice.logic;

/**
 * Author: Piotr Turek
 */
public class Player {
    private static int playerCount = 0;

    private final int playerID = ++playerCount;
    private int points = 0;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPlayerID() {
        return playerID;
    }

    @Override
    public String toString() {
        return "#" + getPlayerID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (playerID != player.playerID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return playerID;
    }
}
