//318528874
package animations;

import biuoop.DrawSurface;

/**
 * Animation interface for any animation screen.
 * know how to play one frame.
 * knows when it should stop running.
 */
public interface Animation {

    /**
     * playing one frame of the animation.
     * @param d surface to play animation on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * return if animation should stop or not.
     * @return true/false if animation should stop or not.
     */
    boolean shouldStop();
}
