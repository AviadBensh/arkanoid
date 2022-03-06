//318528874
package listeners;

import sprites.Block;
import sprites.Ball;

/**
 * HitListener interface consists of a method that informs of a hit that takes place.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit Block being hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
