//318528874
package infoholders;

import geometry.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * LevelInformation interface has information about background, blocks, balls and paddle of level.
 */
public interface LevelInformation {

    /**
     * method that gets that array of balls in play in level.
     * @return array of balls in play in level
     */
    Ball[] getBalls();

    /**
     * method to get total number of balls in game. NOT IN USE.
     * @return total number of balls in game
     */
    int numberOfBalls();

    /**
     * method to get list of initial velocity of each ball. NOT IN USE.
     * @return list of initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * return the speed of paddle.
     * @return speed of paddle
     */
    int paddleSpeed();

    /**
     * return width of paddle.
     * @return width of paddle
     */
    int paddleWidth();

    /**
     * return the name of level.
     * @return name of level
     */
    String levelName();

    /**
     * get the background of the level as a Sprite.
     * @return background of the level as a Sprite
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return list of Blocks that make up this level
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return Number of blocks that should be removed before the level is considered to be "cleared"
     */
    int numberOfBlocksToRemove();
}
