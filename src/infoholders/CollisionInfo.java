//318528874
package infoholders;

import geometry.Point;
import sprites.Collidable;

/**
 * Collision Info class.
 *a collisionInfo object hold fields of the point of collision and object which ball collides with.
 *
 */
public class CollisionInfo {

    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * collisionInfo constructor.
     * @param collision point of collision
     * @param c object ball collides with
     */
    public CollisionInfo(Point collision, Collidable c) {
        this.collisionPoint = collision;
        this.collisionObject = c;
    }

    /**
     * getter for point at which the collision occurs.
     * @return point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * getter for the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
