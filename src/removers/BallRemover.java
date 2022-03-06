//318528874
package removers;

import infoholders.Counter;
import listeners.HitListener;
import levels.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * BallRemover class is a HitListener.
 * can get information on hits that occur and remove balls from gameLevel accordingly
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * constructor, initializing gameLevel and amount of balls in gameLevel.
     * @param gameLevel the gameLevel in progress
     * @param remainingBalls amount of balls in current gameLevel
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
        this.gameLevel = gameLevel;
    }

    /**
     * returns amount of balls currently in gameLevel.
     * @return amount of balls currently in gameLevel
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * return the gameLevel in progress.
     * @return the gameLevel in progress
     */
    public GameLevel getGame() {
        return gameLevel;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.getGame().removeSprite(hitter);
        hitter.removeFromGame(this.getGame());
        this.getRemainingBalls().decrease(1);
    }
}
