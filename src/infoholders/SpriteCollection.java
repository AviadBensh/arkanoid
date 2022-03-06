//318528874
package infoholders;
import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.ArrayList;

/**
 * this class holds an array list of all sprites in game and can operate them all at once.
 */
public class SpriteCollection {

    private final ArrayList<Sprite> sprites;

    /**
     * constructor initializing the list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * add a sprite to the sprite collection's list.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * remove given sprite from the sprite collection's list.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * getter for list of sprites.
     * @return  the array list of sprites
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * call timePassed method of all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn method of all sprites.
     * @param d surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: sprites) {
            s.drawOn(d);
        }
    }
}
