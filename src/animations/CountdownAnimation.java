//318528874
package animations;

import biuoop.DrawSurface;
import infoholders.SpriteCollection;

import java.awt.Color;

/**
 * Countdown animation class, is the animation of the countdown until level starts playing.
 * has a background(of the level going to start), counts down (from 3), finishes when reaches 0.
 */
public class CountdownAnimation implements Animation {

    private boolean running;
    private final long numOfMillis;
    private int countFrom;
    private final int initialCount;
    private final SpriteCollection gameScreen;
    private long initiationTime;

    /**
     * construct a count down animation object from time of running
     * of the animation, number to count down from,
     * and the game's sprite.
     *
     * @param numOfSeconds time of displaying the count down animation.
     * @param countFrom    the number to count down from.
     * @param gameScreen   the game's sprites.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.running = true;
        this.numOfMillis = (long) (numOfSeconds * 1000);
        this.countFrom = countFrom;
        this.initialCount = countFrom;
        this.gameScreen = gameScreen;
        this.initiationTime = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface surface) {
        if (this.countFrom == 0) {
            this.running = false;
        }
        this.gameScreen.drawAllOn(surface);
        surface.setColor(new Color(230, 160, 0));
        surface.drawText(385, 450, Integer.toString(this.countFrom), 65);
        if (System.currentTimeMillis() - this.initiationTime > this.numOfMillis / this.initialCount) {
            this.initiationTime = System.currentTimeMillis();
            this.countFrom--;
        }
    }

   @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
