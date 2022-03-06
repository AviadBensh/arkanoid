//318528874
package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;
import infoholders.Counter;
import levels.GameLevel;

import java.awt.Color;

/**
 * ScoreIndicator class, is a sprite in the game.
 * visually it is a block with text that shows current score and is updated throughout the game.
 */
public class ScoreIndicator implements Sprite {

    private final Counter score;
    private int lives;
    private final String levelName;
    private final Rectangle rect;
    private Color color;

    /**
     * constructor, initializing score, rectangle and color to display.
     * @param score current score in game
     * @param rect rectangle that is the box to set the scoreIndicator in
     * @param levelName name of current level to display
     */
    public ScoreIndicator(Counter score, String levelName, Rectangle rect) {
        this.score = score;
        this.rect = rect;
        this.color = Color.WHITE; //default
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                rect.getWidth(), rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                rect.getWidth(), rect.getHeight());
        d.drawText((int) rect.getUpperLeft().getX() + 80,
                (int) rect.getUpperLeft().getY() + rect.getHeight() / 2 + 4,
                "Lives: 7", 14); // + this.score.getValue(), 12);
        d.drawText((int) rect.getUpperLeft().getX() + rect.getWidth() / 2,
                (int) rect.getUpperLeft().getY() + rect.getHeight() / 2 + 4,
                "Score:" + this.score.getValue(), 14);
        d.drawText((int) rect.getUpperLeft().getX() + rect.getWidth() - 180,
                (int) rect.getUpperLeft().getY() + rect.getHeight() / 2 + 4,
                "Level Name:" + this.levelName, 14);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
    }
}
