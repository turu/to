package pl.edu.agh.to1.dice.logic;

/**
 * Author: Piotr Turek
 */
public class DiceGameException extends RuntimeException {
    protected DiceGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DiceGameException(Throwable cause) {
        super(cause);
    }

    public DiceGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiceGameException() {
        super();
    }

    public DiceGameException(String s) {
        super(s);
    }
}
