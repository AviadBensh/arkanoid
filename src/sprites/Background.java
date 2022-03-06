//318528874
package sprites;

import biuoop.DrawSurface;
import infoholders.SpriteCollection;
import levels.GameLevel;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Background class is a sprite in the game that may or may not contain numerous objects to display on it.
 */
public class Background implements Sprite {

    private final Rectangle rect;
    private final SpriteCollection objects;

    /**
     * constructor, initializing fields.
     * @param rect dimensions of background
     * @param objects objects to show on background
     */
    public Background(Rectangle rect, SpriteCollection objects) {
        this.rect = rect;
        this.objects = objects;
    }

    /**
     * getter for rectangle that background is in.
     * @return rectangle that background is in
     */
    public Rectangle getRect() {
        return this.rect;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rect.drawOn(d);
        this.objects.drawAllOn(d);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void setColor(Color newColor) {
        this.rect.setColor(newColor);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
