//318528874
package infoholders;

/**
 * Counter class keeps track of the score of the game.
 * can increase/decrease score and return current score
 */
public class Counter {
    private int num;

    /**
     * constructor, setting score at 0.
     */
    public Counter() {
        this.num = 0;
    }

    /**
     * add number to current count.
     * @param number amount to add to counter
     */
    public void increase(int number) {
        this.num += number;
    }

    /**
     * subtract number from current count.
     * @param number amount to subtract
     */
    public void decrease(int number) {
        this.num -= number;
    }

    /**
     * get current count.
     * @return current amount
     */
    public int getValue() {
        return this.num;
    }
}
