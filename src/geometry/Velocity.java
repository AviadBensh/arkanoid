//318528874
package geometry;

/**
 * geometry.Velocity class has the change of x and y of a moving object.
 * can apply the change to an object, calculate the needed movement from angle and speed vector.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * geometry.Velocity constructor.
     * @param dx the differential between current x value of point and next x value of point
     * @param dy the differential between current y value of point and next y value of point
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getter that returns dx of velocity.
     * @return dx of velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getter that returns dy of velocity.
     * @return dy of velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * method that creates velocity from angle and speed vector.
     * @param angle the angle(direction) the ball should move at
     * @param speed speed the ball should move at
     * @return created velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double rad = angle * Math.PI / 180;
        double dx = Math.sin(rad) * speed;
        double dy = -(Math.cos(rad) * speed);
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p a point to move
     * @return new point that moved according to velocity
     */
    //
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }

    /**
     * get the speed vector of this velocity.
     * @return the speed vector of this velocity
     */
    public double getSpeed() {
        return Math.sqrt(getDx() * getDx() + getDy() * getDy());
    }
}
