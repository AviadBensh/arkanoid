//318528874
package geometry;

import biuoop.DrawSurface;
import levels.GameLevel;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * geometry.Line class.
 */
public class Line implements Sprite {

    private final Point originalStart;
    private final Point originalEnd;
    private Point start;
    private Point end;
    private double slope;
    private double yAxisIntersection;
    private final double epsilon = 0.000001;
    private Color color;

    /**
     * constructor that creates a line from two geometry.Point arguments.
     *
     * @param start is the point at which the line will start
     * @param end   is the point at which the line will end
     */
    public Line(Point start, Point end) {
        this.originalStart = start;
        this.originalEnd = end;

        //make left side of segment 'start', and if it is a vertical line - make higher point 'start'
        if (start.getX() < end.getX() || (start.getX() == end.getX() && start.getY() < end.getY())) {
            this.start = start;
            this.end = end;
        } else if (start.getX() > end.getX() || (start.getX() == end.getX() && start.getY() > end.getY())) {
            this.start = end;
            this.end = start;
        }
        //set slope and y axis intersection of line
        setEquationParam();
        this.color = Color.BLACK; //default
    }

    /**
     * constructor that creates a line from 4 double arguments.
     *
     * @param x1 is the x value of the point at which the line will start
     * @param y1 is the y value of the point at which the line will start
     * @param x2 is the x value of the point at which the line will end
     * @param y2 is the y value of the point at which the line will end
     */
    public Line(double x1, double y1, double x2, double y2) {

        this.originalStart = new Point(x1, y1);
        this.originalEnd = new Point(x2, y2);
        //make left side of segment 'start', and if it is a vertical line - make higher point 'start'
        if (x1 < x2 || (x1 == x2 && y1 > y2)) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else if (x1 > x2 || (x1 == x2 && y1 < y2)) {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        }
        //set slope and y axis intersection of line
        setEquationParam();
    }

    /**
     * method that defines slope and y axis intersection of line.
     */
    private void setEquationParam() {

            //to find slope(m)==> (y1-y2)/(x1-x2)
            this.slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        //to find where line intersects y axis(b)==> y-(m*x)
        this.yAxisIntersection = this.start.getY() - (this.slope * this.start.getX());
    }

    /**
     * method the returns length of segment.
     *
     * @return the distance between start and end points, which is length of segment
     */
    public double length() {
        return (this.start).distance(this.end);
    }

    /**
     * method the returns middle point of segment.
     * adding x coordinate values of start and end and points and dividing sum by 2, same for y coordinate values.
     *
     * @return the middle point of segment
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * getter that returns start point of line.
     *
     * @return start point of line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return original start point of line.
     */
    public Point originalStart() {
        return this.originalStart;
    }

    /**
     * @return original end point of line.
     */
    public Point originalEnd() {
        return this.originalEnd;
    }

    /**
     * getter that returns end point of line.
     *
     * @return end point of line
     */
    public Point end() {
        return this.end;
    }

    /**
     * getter that returns slope of line.
     *
     * @return slope of line
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * getter that returns y value of intersection with y axis of line.
     *
     * @return y value of intersection with y axis of line
     */
    public double getYAxisIntersection() {
        return this.yAxisIntersection;
    }

    /**
     * method to find intersection of two segments, if exists, and return intersection geometry.Point.
     * -if start of one and end of other are same, that is the intersection.
     * -if lines are not parallel, and have same start or end point, that is the only intersection
     * -if one of the lines is vertical, it hax 1 x coordinate, so insert that x to (m*x+b) to find y coordinate,
     * and check if that y coordinate is in range of other segment
     * -if these cases are not true, then find x and y coordinates of intersection by equations
     *
     * @param other is another line
     * @return intersection point if exists, null if does not
     */
    private Point getIntersection(Line other) {

        if (this.start().isOn(other)) {
            return this.start();
        } else if (this.end().isOn(other)) {
            return this.end();
        } else if (other.start().isOn(this)) {
            return other.start();
        } else if (other.end().isOn(this)) {
            return other.end();
        }
        //if start and end points of two line are equal, that is their intersection
        if (this.start().equals(other.end())) {
            return this.start();
        } else if (this.end().equals(other.start())) {
            return this.end();

            //if lines intersect at both start points or end points but are not parallel, they have one intersection
        } else if (!this.parallel(other) && this.start().equals(other.start())) {
            return this.start();
        } else if (!this.parallel(other) && this.end().equals(other.end())) {
            return this.end();

            //if one line is vertical, insert x coordinate to other line's equation and see if y coordinate is
            //in range of segment
        } else if (this.vertical() && other.start().getX() <= this.start().getX()
                && other.end().getX() >= this.end().getX()) {
            if (other.getSlope() * this.start().getX() + other.getYAxisIntersection() > this.start().getY()
                    && other.getSlope() * this.start().getX() + other.getYAxisIntersection() < this.end().getY()) {
                return new Point(this.start().getX(),
                        other.getSlope() * this.start().getX() + other.getYAxisIntersection());
            }
        } else if (other.vertical() && this.start().getX() <= other.start().getX()
                && this.end().getX() >= other.end().getX()) {
            if (this.getSlope() * other.start().getX() + this.getYAxisIntersection() > other.start().getY()
                    && this.getSlope() * other.start().getX() + this.getYAxisIntersection() < other.end().getY()) {
                return new Point(other.start().getX(),
                        this.getSlope() * other.start().getX() + this.getYAxisIntersection());
            }
        }

        // to find x of intersection ==> (y2-y1)/(m1-m2)
        double xIntersection = (other.getYAxisIntersection() - this.getYAxisIntersection())
                / (this.getSlope() - other.getSlope());

        // to find y of intersection ==> m1 * x + b1
        double yIntersection = this.getSlope() * xIntersection + this.getYAxisIntersection();
        Point intersection = new Point(xIntersection, yIntersection);

        //check if distance from: start to intersection + intersection to end = length of line
        //if so, intersection is on both segments and they indeed intersect
        if (Math.abs(this.start().distance(intersection) + intersection.distance(end()) - this.length()) < epsilon
                && Math.abs(other.start().distance(intersection) + intersection.distance(other.end())
                - other.length()) < epsilon) {
            return intersection;
        }
        return null;
    }

    /**
     * method that checks if lines intersect. if they are equal or overlap it counts as intersecting.
     * using getIntersection method to find if intersection point exists
     *
     * @param other another line
     * @return true or false depending if lines intersect or not
     */
    public boolean isIntersecting(Line other) {

        //if both segments are exactly the same line or overlap
        if (this.equals(other) || this.overlap(other)) {
            return true;

            //if start of one and end of other are equal
        } else if (this.start().distance(other.end()) < epsilon || this.end().distance(other.start()) < epsilon
                || this.start().distance(other.start()) < epsilon || this.end().distance(other.end()) < epsilon) {
            return true;
        }
        return this.getIntersection(other) != null;
    }

    /**
     * method that returns intersection point if exists.
     *
     * @param other another line
     * @return intersection point if exist, null if not
     */
    public Point intersectionWith(Line other) {

        //if null was returned from getIntersection there is no intersection point.
        //if lines are equal there is no 1 intersection point to return
        if (!this.isIntersecting(other) || this.equals(other)) {
            return null;
        } else {
            return this.getIntersection(other);
        }
    }

    /**
     * method that checks if two lines are the same line.
     *
     * @param other another line
     * @return true or false depending if lines are equal or not
     */
    public boolean equals(Line other) {
        if (this.start().equals(other.start())) {
            return this.end().equals(other.end());
        }
        return false;
    }

    /**
     * method that checks if lines overlap.
     *
     * @param other other line
     * @return true/false if overlap or not
     */
    private boolean overlap(Line other) {

        //vertical lines overlap if x coordinate of start and end of segment are equal
        if (Math.abs(this.start().getX() - this.end().getX()) < epsilon
                && Math.abs(other.start().getX() - other.end().getX()) < epsilon
                && Math.abs(this.start().getX() - other.start().getX()) < epsilon) {
            return (other.start().getY() < this.start().getY() && other.start().getY() > this.end().getY())
                    || (other.end().getY() < this.start().getY() && other.end().getY() > this.end().getY());

            //if slopes and y axis intersection of lines are equal, check if start or end of one segment lies between
            //other lines start and end
        } else if (Math.abs(this.getSlope() - other.getSlope()) < epsilon
                && Math.abs(this.getYAxisIntersection() - other.getYAxisIntersection()) < epsilon) {
            return (other.start().getX() > this.start().getX() && other.start().getX() < this.end().getX())
                    || (other.end().getX() > this.start().getX() && other.end().getX() < this.end().getX());
        }
        return false;
    }

    /**
     * method that checks if lines are parallel by checking if slopes are equal.
     *
     * @param other another line
     * @return true/false if lines are parallel or not
     */
    private boolean parallel(Line other) {
        if (this.equals(other) || this.overlap(other)) {
            return true;
        }
        return Math.abs(this.getSlope() - other.getSlope()) < epsilon;
    }

    /**
     * method to check if a line is vertical by checking if x coordinate of start and end are equal.
     *
     * @return true/false if vertical or not
     */
    public boolean vertical() {
        return Math.abs(this.start().getX() - this.end().getX()) < epsilon;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect geometry.Rectangle object to check if line intersects
     * @return the point of intersection with the rectangle closest to original start of line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double distanceA, distanceB;
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.size() != 0) {
            distanceA = this.originalStart().distance(rect.intersectionPoints(this).get(0));
            if (intersectionPoints.size() == 2) {
                distanceB = this.originalStart().distance(rect.intersectionPoints(this).get(1));
                if (distanceB <= distanceA) {
                    return rect.intersectionPoints(this).get(1);
                }
            }
            return rect.intersectionPoints(this).get(0);
        }
        return null;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start().getX(), (int) this.start().getY(),
                (int) this.end().getX(), (int) this.end().getY());
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
