package pl.edu.agh.to1.dice.logic;

/**
 * Author: Piotr Turek
 */
public enum DiceCategory {
    ONE("jedynki"),
    TWO("dwojki"),
    THREE("trojki"),
    FOUR("czworki"),
    FIVE("piatki"),
    SIX("szostki"),
    SAME_THREE("3 jednakowe"),
    SAME_FOUR("4 jednakowe"),
    FULL("ful"),
    LITTLE_STREET("maly strit"),
    BIG_STREET("duzy strit"),
    GENERAL("general"),
    CHANCE("szansa");

    private final String description;

    private DiceCategory(String desc) {
        description = desc;
    }

    public String getDescription() {
        return description;
    }
}
