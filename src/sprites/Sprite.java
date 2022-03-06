//318528874
package sprites;
import biuoop.DrawSurface;
import levels.GameLevel;

import java.awt.Color;

/**
 * sprites.Sprite interface holds any objects that are included and seen on the playing board.
 * currently implemented by sprites.Ball, sprites.Block, geometry.Rectangle.
 * a sprite is able to draw itself, get notified time has passed, set its' color and add itself to game.
 */
public interface Sprite {

    int WIDTH = 800;
    int HEIGHT = 600;

    /**
     * draw the sprites.Sprite object on surface d.
     * @param d surface to draw sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * notify all sprites that time has passed.
     */
    void timePassed();

    /**
     * set the color of the sprite.
     * @param newColor wanted color
     */
    void setColor(Color newColor);

    /**
     * add this sprite to game.
     * @param g game to add to
     */
    void addToGame(GameLevel g);

    /**
     * remove this ball from given game.
     * @param g game to remove ball from
     */
    void removeFromGame(GameLevel g);
}
