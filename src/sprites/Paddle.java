//318528874

package sprites;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import levels.GameLevel;
import rectangleenum.EDGE;

import java.awt.Color;

/**
 * sprites.Paddle class.
 * a paddle can move left and right, and implements all sprites.Collidable and sprites.Sprite methods.
 */
public class Paddle implements Collidable, Sprite {

    private final KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private final int speed;


    /**
     * sprites.Paddle constructor.
     *
     * @param sensor a keyboard object
     * @param width width of paddle
     * @param speed speed of paddle
     */
    public Paddle(KeyboardSensor sensor, int width, int speed) {
        this.keyboard = sensor;
        this.color = Color.BLACK; //default
        this.speed = speed;
        this.rect = new Rectangle(new Point((400 - width / 2), 590), width, 10);
    }

    /**
     * getter for speed of paddle.
     * @return speed of paddle
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * move the paddle to the left 10 coordinates.
     */
    public void moveLeft() {
        Point newUpperLeft = new Point(this.rect.getUpperLeft().getX() - this.getSpeed(), this.rect.getY());
        this.rect = new Rectangle(newUpperLeft, this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * move the paddle to the right 10 coordinates.
     */
    public void moveRight() {
        Point newUpperLeft = new Point(this.rect.getUpperLeft().getX() + this.getSpeed(), this.rect.getY());
        this.rect = new Rectangle(newUpperLeft, this.rect.getWidth(), this.rect.getHeight());
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                rect.getWidth(), rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                rect.getWidth(), rect.getHeight());
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && this.rect.getUpperLeft().getX() > 0) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.rect.getUpperLeft().getX() + this.rect.getWidth() < Sprite.WIDTH) {
            moveRight();
        }
    }

    @Override
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //if collision is exactly on corner, return 180 degrees
        for (Point p : getCollisionRectangle().getCorners()) {
            if (collisionPoint.equals(p)) {
                return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            }
        }
        Line[] edges = this.getCollisionRectangle().getEdges();
        double segment = this.rect.getWidth() / 5.0;
        for (Line edge : edges) {
            if (collisionPoint.isOn(edge)) {
                if (edge.equals(edges[EDGE.TOP.ordinal()])) {

                    //paddle divided to 5 segments that influence velocity differently.
                    if (edge.start().distance(collisionPoint) <= segment) {
                        return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                    } else if (edge.start().distance(collisionPoint) <= 2 * segment) {
                        return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                    } else if (edge.start().distance(collisionPoint) <= 3 * segment) {
                        return Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
                    } else if (edge.start().distance(collisionPoint) <= 4 * segment) {
                        return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                    } else if (edge.start().distance(collisionPoint) <= 5 * segment) {
                        return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                    }
                } else if ((edge.equals(edges[EDGE.LEFT.ordinal()]) && currentVelocity.getDx() >= 0)
                        || (edge.equals(edges[EDGE.RIGHT.ordinal()]) && currentVelocity.getDx() <= 0)) {
                    return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                }
            }
        }
        return currentVelocity;
    }

    @Override
    public Line[] getEdges() {
        return this.rect.getEdges();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
    }
}
