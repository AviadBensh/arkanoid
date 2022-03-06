//318528874
package animations;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * EndScreen animation is the screen shown when game is over.
 */
public class EndScreen implements Animation {
    private final boolean running;
    private final int score;
    private final String text;

    /**
     * constructor for EndScreen animation.
     * @param text text to show on screen
     * @param score final score of player to show on screen
     */
    public EndScreen(String text, int score) {
        this.running = true;
        this.score = score;
        this.text = text;
    }

    /**
     * this method gets a color and a DrawSurface
     * and draws the background on the given DrawSurface.
     *
     * @param d     the given DrawSurface.
     * @param color the given color.
     */
    public void setBackground(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    /**
     * this method draws each frame of the animation
     * of the EndScreen animation on a given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     */
    public void doOneFrame(DrawSurface surface) {
        this.setBackground(surface, new Color(143, 86, 179));
        surface.setColor(new Color(230, 160, 0));
        surface.drawText(80, 250, this.text + this.score, 50);
    }

    /**
     * returns true if EndScreen animation has to stop,false otherwise.
     *
     * @return true if EndScreen animation has to stop, false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}
