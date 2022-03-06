//318528874
package sprites;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import infoholders.HitNotifier;
import listeners.HitListener;
import levels.GameLevel;
import rectangleenum.EDGE;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * sprites.Block class.
 * implements all methods of sprites.Collidable and sprite interfaces
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private final List<HitListener> hitListeners;
    private final Rectangle rect;
    private Color color;

    /**
     * sprites.Block constructor.
     * @param rect geometry.Rectangle object to assign to 'rect' field
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.color = Color.BLACK; //default
        this.hitListeners = new ArrayList<>();
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

        //if collision is exactly on corner flip dx and dy of velocity
        for (Point p: getCollisionRectangle().getCorners()) {
            if (collisionPoint.equals(p)) {
                this.notifyHit(hitter);
                return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            }
        }
        //create array of collision object edges and check which edge the ball hit.
        //change velocity accordingly.
        Line[] edges = this.getCollisionRectangle().getEdges();
        for (Line edge: edges) {
            if (collisionPoint.isOn(edge)) {
                if (edge.equals(edges[EDGE.LEFT.ordinal()]) || edge.equals(edges[EDGE.RIGHT.ordinal()])) {
                    this.notifyHit(hitter);
                    return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                } else if (edge.equals(edges[EDGE.TOP.ordinal()]) || edge.equals(edges[EDGE.BOTTOM.ordinal()])) {
                    this.notifyHit(hitter);
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
            }
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    @Override
    public Line[] getEdges() {
        return this.rect.getEdges();
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                rect.getWidth(), rect.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                rect.getWidth(), rect.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notify al listeners of this block about hit that occurred.
     * @param hitter ball that hit this block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
