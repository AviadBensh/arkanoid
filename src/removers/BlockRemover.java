//318528874
package removers;

import infoholders.Counter;
import listeners.HitListener;
import levels.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * BlockRemover class is a HitListener.
 * can get information on hits that occur in gameLevel and remove blocks from gameLevel accordingly
 */
public class BlockRemover implements HitListener {

    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * constructor, initializing gameLevel in progress and amount of blocks in gameLevel.
     * @param gameLevel gameLevel in progress
     * @param removedBlocks blocks in gameLevel
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
    this.gameLevel = gameLevel;
    this.remainingBlocks = removedBlocks;
    }

    /**
     * return blocks left in gameLevel.
     * @return amount of blocks still in gameLevel
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * return gameLevel currently in progress.
     * @return the gameLevel currently in progress
     */
    public GameLevel getGame() {
        return this.gameLevel;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.getGame().removeCollidable(beingHit);
        this.getGame().removeSprite(beingHit);
        beingHit.removeFromGame(this.getGame());
        this.getRemainingBlocks().decrease(1);
    }
}
