//318528874
package listeners;

import infoholders.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * ScoreTrackingListener class keeps track of the score whenever score is updated.
 */
public class ScoreTrackingListener implements HitListener {

    private final Counter currentScore;

    /**
     * constructor that sets a Counter object.
     * @param scoreCounter a given counter to initialize with
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
