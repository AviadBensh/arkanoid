//318528874
package geometry;

/**
 * geometry.Point has x and y coordinate.
 * can find distance between two points, if two points are equal, if point is on a line or in a rectangle.
 *
 */
public class Point {

    private final double x;
    private final double y;
    private  final double epsilon = 0.000001;

    /**
     * this is a geometry.Point constructor that initializes x and y coordinates to given x and y arguments.
     * @param x is the x coordinate of the point
     * @param y is the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * method to find distance between this and other points using pythagoras algorithm.
     * @param other is another geometry.Point
     * @return the distance
     */
    public double distance(Point other) {
        double dx = this.getX() - other.getX();
        double dy = this.getY() - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * method to check if two points are the same by checking distance between them.
     * using epsilon to cover for double-related inaccuracy
     * @param other is another point
     * @return true or false if points are equal or not
     */
    public boolean equals(Point other) {
        return this.distance(other) < epsilon;
    }

    /**
     * getter method to get x coordinate of this point.
     * @return x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * getter method to get y coordinate of this point.
     * @return y coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     *find if point is on a line.
     * @param line line to check if point is on it
     * @return true/false if point is on or not
     */
    public boolean isOn(Line line) {
        return (Math.abs(line.start().distance(this) + this.distance(line.end())
                - line.start().distance(line.end())) < epsilon);
    }

    /**
     * find if point is in a rectangle.
     * @param rect rectangle object to check if point is in it
     * @return true/false if point is in or not
     */
    public boolean isIn(Rectangle rect) {
        return this.getX() > rect.getUpperLeft().getX()
                && this.getX() < rect.getUpperLeft().getX() + rect.getWidth()
                && this.getY() > rect.getUpperLeft().getY()
                && this.getY() < rect.getUpperLeft().getY() + rect.getHeight();
    }
}
