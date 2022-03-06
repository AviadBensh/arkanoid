//318528874
package animations;

import biuoop.DrawSurface;

/**
 * PauseScreen class is the animation shown when player pauses the game.
 */
public class PauseScreen implements Animation {
    private final boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
