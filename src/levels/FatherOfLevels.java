//318528874
package levels;

import biuoop.GUI;
import geometry.Velocity;
import infoholders.LevelInformation;
import managers.AnimationRunner;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.Background;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Father of levels class consists of all common code to all levels.
 * all levels inherit from this class.
 */
public class FatherOfLevels implements LevelInformation {

    private final AnimationRunner runner;
    private Ball[] ballsArray;
    private Paddle paddle;
    private Background background;
    private final List<Block> blocksArray;

    /**
     * constructor, default initialization of fields.
     * @param gui a GUI to send to level's runner
     */
    public FatherOfLevels(GUI gui) {
        this.runner = new AnimationRunner(gui);
        this.ballsArray = new Ball[]{};
        this.paddle = null;
        this.background = null;
        this.blocksArray = new ArrayList<>();
    }

    /**
     * getter for Animation runner of level.
     * @return Animation runner of level
     */
    protected AnimationRunner getRunner() {
        return this.runner;
    }

    /**
     * setter for balls of level.
     * @param array balls to initiate for level
     */
    protected void setBalls(Ball[] array) {
        this.ballsArray = array;
    }

    /**
     * setter for paddle of level.
     * @param p paddle to initiate for level
     */
    protected void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * getter for paddle of level.
     * @return paddle of level
     */
    protected Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * setter for background of level.
     * @param bg background to initiate for level
     */
    protected void setBackground(Background bg) {
        this.background = bg;
    }

    /**
     * getter for blocks of level.
     * @return blocks of level
     */
    protected List<Block> getBlocks() {
        return this.blocksArray;
    }

    @Override
    public Ball[] getBalls() {
        return this.ballsArray;
    }

    @Override
    public int numberOfBalls() {
        return this.ballsArray.length;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (Ball b : this.ballsArray) {
            velocities.add(b.getVelocity());
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddle.getSpeed();
    }

    @Override
    public int paddleWidth() {
        return this.paddle.getCollisionRectangle().getWidth();
    }

    @Override
    public String levelName() {
        return null;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocksArray;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
