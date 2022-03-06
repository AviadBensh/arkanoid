//318528874
package sprites;

import geometry.Point;
import biuoop.DrawSurface;
import levels.GameLevel;

import java.awt.Color;

/**
 * Circle class is a sprite that may appear in the game.
 */
public class Circle implements Sprite {
    private Color color;
    private int radius;
    private Point center;

    /**
     * constructor.
     * @param center center point of circle
     * @param r radius of circle
     */
    public Circle(Point center, int r) {
        this.center = center;
        this.radius = r;
        this.color = Color.BLACK; //default
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
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
    }

    @Override
    public void removeFromGame(GameLevel g) {
    }
}
