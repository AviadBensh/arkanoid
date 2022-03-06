//31852884
package sprites;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * sprites.Collidable interface.
 *
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
      */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter the ball that made the hit with this Collidable object
     * @param collisionPoint the point of collision between ball and collidable object
     * @param currentVelocity current velocity of hitting ball
     * @return new velocity after hitting the object
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * method that gets edges of collidable object.
     * @return array of edges of collidable object
     */
    Line[] getEdges();

}
