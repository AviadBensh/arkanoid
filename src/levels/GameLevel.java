//318528874
package levels;

import animations.Animation;
import animations.CountdownAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Rectangle;
import infoholders.Counter;
import infoholders.GameEnvironment;
import infoholders.SpriteCollection;
import listeners.ScoreTrackingListener;
import managers.AnimationRunner;
import infoholders.LevelInformation;
import managers.KeyPressStoppableAnimation;
import removers.BallRemover;
import removers.BlockRemover;
import sprites.Block;
import sprites.Ball;
import sprites.Paddle;
import sprites.Sprite;
import sprites.Collidable;
import sprites.ScoreIndicator;

import java.awt.Color;

/**
 * levels.GameLevel class.
 * responsible for initialization and running of game
 */
public class GameLevel implements Animation {

    private final GameEnvironment environment;
    private final SpriteCollection sprites;
    private final AnimationRunner runner;
    private final Counter score;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final KeyboardSensor keyboard;
    private final int frameWidth;
    private final int frameHeight;
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private final ScoreIndicator scoreIndicator;
    private final CountdownAnimation countdown;
    private boolean running;
    private final LevelInformation currentLevel;

    /**
     * levels.GameLevel constructor.
     * setting default variables in all fields
     * @param level level currently running
     * @param ks keyboard associated with game
     * @param runner runner of the game
     * @param score counter of score
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner runner, Counter score) {
        this.runner = runner;
        this.keyboard = ks;
        this.score = score;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.frameWidth = this.runner.getGui().getDrawSurface().getWidth();
        this.frameHeight = this.runner.getGui().getDrawSurface().getHeight();
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.ballRemover = new BallRemover(this, this.remainingBalls);
        this.scoreIndicator = (new ScoreIndicator(this.score, level.levelName(),
                new Rectangle(new Point(0, 0), frameWidth, 15)));
        this.countdown = new CountdownAnimation(2, 3, this.sprites);
        this.currentLevel = level;
    }

    /**
     * returns true/false if there are more block to remove.
     * @return true/false if there are more block to remove
     */
    public boolean blocksFinished() {
        return this.remainingBlocks.getValue() == 0;
    }

    /**
     * returns true/false if there are still balls in play.
     * @return true/false if there are still balls in play
     */
    public boolean noBalls() {
        return this.remainingBalls.getValue() == 0;
    }
    /**
     * @return the game's environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @return game's sprite collection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * method to add a collidable object to environment.
     * @param c collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * remove collidable object from environment.
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * method to add a sprite object to list of sprites.
     * @param s sprite object to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove sprite object from list of sprites.
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle) and add them to the game.
     */
    public void initialize() {

        this.addSprite(this.currentLevel.getBackground());
        Paddle paddle = new Paddle(this.runner.getGui().getKeyboardSensor(),
                this.currentLevel.paddleWidth(), this.currentLevel.paddleSpeed());
        paddle.setColor(Color.ORANGE);
        Block top = new Block(new Rectangle(new Point(0, 15), frameWidth - 15, 15));
        Block right = new Block(new Rectangle(new Point(frameWidth - 15, 15), 15, frameHeight - 15));
        Block left = new Block(new Rectangle(new Point(0, 30), 15, frameHeight - 15));
        top.setColor(Color.DARK_GRAY);
        right.setColor(Color.DARK_GRAY);
        left.setColor(Color.DARK_GRAY);
        paddle.addToGame(this);
        top.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
        Block deathRegion = new Block(new Rectangle(new Point(0, frameHeight), frameWidth, 15));
        deathRegion.addToGame(this);
        deathRegion.addHitListener(this.ballRemover);

        for (Block bl: this.currentLevel.blocks()) {
            bl.addToGame(this);
            remainingBlocks.increase(1);
            bl.addHitListener(blockRemover);
            bl.addHitListener(new ScoreTrackingListener(this.score));
        }
        for (Ball b : this.currentLevel.getBalls()) {
            b.setEnv(getEnvironment());
            b.addToGame(this);
            remainingBalls.increase(1);
        }
        scoreIndicator.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.running = true;

        //use runner to run countdown animation
        this.runner.run(this.countdown);

        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis(); // timing
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();

            if (this.blockRemover.getRemainingBlocks().getValue() == 0) {
                this.score.increase(100);
                this.running = false;
            } else if (this.ballRemover.getRemainingBalls().getValue() == 0) {
                this.running = false;
            }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
    }
}
