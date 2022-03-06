//318528874
package sprites;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import infoholders.CollisionInfo;
import infoholders.GameEnvironment;
import levels.GameLevel;

import java.awt.Color;
import java.util.Random;

/**
 * sprites.Ball class.
 * a ball has center point and radius, color and velocity, and lives in a certain environment.
 * a ball can move from one point to another and create an array of balls.
 * ball implements all sprites.Sprite methods.
 *
 */
public class Ball implements Sprite {

    private Point center;
    private final int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment env;

    /**
     * sprites.Ball constructor.
     *
     * @param center point that is the center of the ball
     * @param r      radius of the ball
     * @param color  color of ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0); //default
    }

    /**
     * setter that sets velocity of this ball.
     *
     * @param v a given velocity object
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setter that sets velocity of this ball.
     * use two double values two create velocity object and set ball velocity with above method
     *
     * @param dx a value that will be the difference between two x values of points when ball moves
     * @param dy a value that will be the difference between two y values of points when ball moves
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.setVelocity(v);
    }

    /**
     * method that sets environment.
     *
     * @param environment environment to assign to ball
     */
    public void setEnv(GameEnvironment environment) {
        this.env = environment;
    }

    /**
     * getter that gets velocity of this ball.
     *
     * @return velocity of ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getter that returns x value of center of ball.
     *
     * @return x value of center of ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getter that returns y value of center of ball.
     *
     * @return y value of center of ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getter that returns radius of ball.
     *
     * @return radius of ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * getter that returns center point of ball.
     *
     * @return center point of ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * getter that returns color of ball.
     *
     * @return color of ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * getter that returns infoHolders.GameEnvironment of ball.
     *
     * @return infoHolders.GameEnvironment of ball
     */
    public GameEnvironment getEnv() {
        return env;
    }

    /**
     * method that moves the ball according to velocity.
     * if the ball runs into a sprites.Collidable object, another method is called on to change ball's velocity
     * accordingly.
     */
    public void moveOneStep() {

        //the line the ball will follow from where it is located now to where it will end up after next step
        Line trajectory = new Line(this.getCenter(), new Point(this.getVelocity().applyToPoint(center).getX(),
                this.getVelocity().applyToPoint(center).getY()));

        //info receives the point and object of collision if such exists
        CollisionInfo info = getEnv().getClosestCollision(trajectory);

        //if there is a collision and it is on the ball's next step (trajectory)
        if (info != null && info.collisionPoint().isOn(trajectory)) {
             this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity());
        }
        //if ball enters block
        for (Collidable c : this.getEnv().getCollidables()) {
            Line[] edges = c.getEdges();
            for (Line edge : edges) {
                if (new Point(this.getVelocity().applyToPoint(center).getX(),
                        this.getVelocity().applyToPoint(center).getY()).isIn(c.getCollisionRectangle())) {
                        this.velocity = new Velocity(-this.getVelocity().getDx(), -this.getVelocity().getDy());
                }
            }
        }
        //move ball to next position
        center = new Point(this.getVelocity().applyToPoint(center).getX(),
                this.getVelocity().applyToPoint(center).getY());
    }

    /**
     * moves center of ball 10 coordinates up.
     */
    private void reCenter() {
        this.center = new Point(this.getCenter().getX(), this.getCenter().getY() - 10);
    }

    /**
     * method that makes an array of balls.
     * each ball in array has set size that is given in sizeArr argument, then random starting point and velocity
     * are set. all balls by default are black
     *
     * @param start   start point of frame of ball
     * @param sizeArr array of ball radius sizes
     * @return array of balls
     */
    public static Ball[] makeBallArr(int[] sizeArr, Point start) {
        Ball[] ballsArr = new Ball[sizeArr.length];
        Random rand = new Random();
        double startX, startY, angle, speed;

        //set random attributes and create ball in ball array
        for (int i = 0; i < sizeArr.length; i++) {
            startX = rand.nextDouble() + start.getX();
            startY = rand.nextDouble() + start.getY();
            double lowDegree = 270;
            double highDegree = 450;
            angle = rand.nextDouble() * (highDegree - lowDegree) + lowDegree;
            speed = rand.nextDouble() * sizeArr[i];
            if (speed < 5) {
                speed = 5;
            }
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ballsArr[i] = new Ball(new Point(startX, startY), sizeArr[i], Color.BLACK);
            ballsArr[i].setVelocity(v);
        }
        return ballsArr;
    }

    @Override
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.getCenter().getX(), (int) this.getCenter().getY(), getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
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
