//318528874
package infoholders;

import geometry.Line;
import sprites.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * infoHolders.GameEnvironment class.
 * holds all sprites.Collidable objects in game, and finds information about intersections with ant of those objects.
 */
public class GameEnvironment {

    private final ArrayList<Collidable> collidables;

    /**
     * infoHolders.GameEnvironment constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     * @param c a collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * remove the given collidable from the environment.
     * @param c collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
    /**
     * getter to get list of collidables in environment.
     * @return list of collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Assume an object moving from line.originalStart() to line.originalEnd().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the line on which the ball will move
     * @return a collisionInfo object holding the point of collision and object ball will collide with
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        for (Collidable c : collidables) {
            for (Line edge : c.getEdges()) {
                if (trajectory.isIntersecting(edge)
                        && trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()).isOn(edge)) {
                    return new CollisionInfo(trajectory.intersectionWith(edge), c);
                }
            }
        }
        return null;
    }

}
