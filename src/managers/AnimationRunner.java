//318528874
package managers;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import animations.Animation;

/**
 * Animation runner class is an object responsible for managing the animation currently running.
 * running it and stopping, controls gui the animation runs on and how long it runs for.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * constructor of all fields.
     * default values.
     */
    public AnimationRunner() {
        this.gui = new GUI("arkanoid", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * constructor receiving GUI from caller.
     * @param gui gui for to play animation on
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * geter for gui on which animation is running.
     * @return GUI on which animation is running
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * method that runs the given animation as long as animation doesn't need to stop.
     * @param animation animation wanted to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
