//318528874
package geometry;
import biuoop.DrawSurface;
import rectangleenum.EDGE;
import rectangleenum.CORNER;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * geometry.Rectangle class.
 * the class holds a geometry.Rectangle object that has start point, width, height and color fields, as well as
 * a geometry.Point array for its' corners and geometry.Line array for its' edges.
 * the class consists of set and get methods as well as a method that enables frame to draw itself on a surface
 *
 * @author  Aviad Benshoshan
 * @version 1.0
 * @since   2021-04-08
 *
 */
public class Rectangle {

    private final int width;
    private final int height;
    private Color color;
    private final Point[] corners = new Point[4];
    private final Line[] edges = new Line[4];

    /**
     * Frame constructor.
     * @param upperLeft top left corner of frame
     * @param width width of frame
     * @param height height of frame
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = (int) width;
        this.height = (int) height;
        setCorners(upperLeft);
        setEdges();
        this.color = Color.WHITE; //default
    }

    /**
     * make array of rectangle's corners, by using upper left point given, and width/height fields.
     * @param upperLeft upper left point of rectangle
     */
    private void setCorners(Point upperLeft) {
        double endX = upperLeft.getX() + getWidth();
        double endY = upperLeft.getY() + getHeight();
        this.corners[CORNER.UPPER_LEFT.ordinal()] = upperLeft;
        this.corners[CORNER.UPPER_RIGHT.ordinal()] = new Point(endX, upperLeft.getY());
        this.corners[CORNER.BOTTOM_RIGHT.ordinal()] = new Point(endX, endY);
        this.corners[CORNER.BOTTOM_LEFT.ordinal()] = new Point(upperLeft.getX(), endY);
    }

    /**
     * make array of rectangle's edges by using the corners array.
     */
    private void setEdges() {
        this.edges[EDGE.TOP.ordinal()] = new Line(this.corners[CORNER.UPPER_LEFT.ordinal()],
                this.corners[CORNER.UPPER_RIGHT.ordinal()]);
        this.edges[EDGE.RIGHT.ordinal()] = new Line(this.corners[CORNER.UPPER_RIGHT.ordinal()],
                this.corners[CORNER.BOTTOM_RIGHT.ordinal()]);
        this.edges[EDGE.BOTTOM.ordinal()] = new Line(this.corners[CORNER.BOTTOM_RIGHT.ordinal()],
                this.corners[CORNER.BOTTOM_LEFT.ordinal()]);
        this.edges[EDGE.LEFT.ordinal()] = new Line(this.corners[CORNER.BOTTOM_LEFT.ordinal()],
                this.corners[CORNER.UPPER_LEFT.ordinal()]);
    }

    /**
     * set a new color for the geometry.Rectangle.
     * @param newColor the color to set for the rectangle
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * find the intersection point of a line with this geometry.Rectangle.
     * a line crosses a rectangle a maximum of 2 times.
     * @param line geometry.Line object to check intersection with the geometry.Rectangle
     * @return List (dynamic since there can either be 0, 1, 2 intersections) of intersection
     */
    public List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersections = new ArrayList<>();
        setEdges();
        for (Line edge: edges) {
            if (edge.intersectionWith(line) != null) {
                Point p = edge.intersectionWith(line);
                intersections.add(p);
            }
        }
        return intersections;
    }

    /**
     * getter that returns width of frame.
     * @return width of frame
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getter that returns height of frame.
     * @return height of frame
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * getter that returns start(top left corner) point of frame.
     * @return start(top left corner) point of frame
     */
    public Point getUpperLeft() {
        return this.corners[CORNER.UPPER_LEFT.ordinal()];
    }

    /**
     * getter that returns x value of start(bottom left corner) of frame.
     * @return x value of start(bottom left corner) of frame
     */
    public int getX() {
        return (int) this.getUpperLeft().getX();
    }

    /**
     * getter that returns y value of start(bottom left corner) of frame.
     * @return y value of start(bottom left corner) of frame
     */
    public int getY() {
        return (int) this.getUpperLeft().getY();
    }

    /**
     * @return the array of corners of this geometry.Rectangle.
     */
    public Point[] getCorners() {
        return this.corners;
    }

    /**
     * @return the array of edges of this geometry.Rectangle.
     */
    public Line[] getEdges() {
        return this.edges;
    }

    /**
     * getter that returns color of frame.
     * @return color of frame
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * method that draws frame on given surface.
     * @param surface is the surface on which to draw the frame
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

}

