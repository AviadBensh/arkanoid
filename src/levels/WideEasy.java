//318528874
package levels;

import biuoop.GUI;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import infoholders.SpriteCollection;
import sprites.Background;
import sprites.Paddle;
import sprites.Ball;
import sprites.Block;


import java.awt.Color;

/**
 * Wide easy class is the second level of game.
 * has one full row of blocks, a large paddle and many balls
 */
public class WideEasy extends FatherOfLevels {

    /**
     * constructor, initiates all local fields in 'super' and then initiates unique attributes in 'initialize'.
     * @param gui a GUI for level's runner
     */
    public WideEasy(GUI gui) {
        super(gui);
        initialize(createObjects());
    }

    /**
     * method that creates all Sprites that make up the background of this level.
     * @return collection of sprites in background
     */
    private SpriteCollection createObjects() {
        double width = this.getRunner().getGui().getDrawSurface().getWidth();
        double height = this.getRunner().getGui().getDrawSurface().getHeight();
        SpriteCollection objects = new SpriteCollection();
        for (int i = 0; i < width; i += 15) {
            Line l = new Line(new Point(80, 80), new Point(i, height / 2 - 100));
            l.setColor(Color.YELLOW);
            objects.addSprite(l);
        }
        Ball b1 = new Ball(new Point(80, 80), 60, new Color(201, 98, 11));
        Ball b2 = new Ball(new Point(80, 80), 50, new Color(219, 154, 73));
        Ball b3 = new Ball(new Point(80, 80), 40, Color.YELLOW);
        objects.addSprite(b1);
        objects.addSprite(b2);
        objects.addSprite(b3);
        return objects;
    }

    /**
     * method to initialize all unique level attributes.
     * @param objects collection of sprites in level's background
     */
    private void initialize(SpriteCollection objects) {
        double width = this.getRunner().getGui().getDrawSurface().getWidth();
        double height = this.getRunner().getGui().getDrawSurface().getHeight();
        this.setBalls(Ball.makeBallArr(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                new Point(width / 2.0, height - 12)));
        double dx = -10;
        double dy = -5;
        for (Ball b : this.getBalls()) {
            b.setVelocity(dx, dy);
            dx += 2;
        }
        this.setPaddle(new Paddle(this.getRunner().getGui().getKeyboardSensor(), (int) (width - 80), 5));
        this.setBackground(new Background(new Rectangle(new Point(0, 0), width, height), objects));
        Point center = new Point(width / 2.0, height / 2.0);
        this.getBackground().setColor(new Color(137, 39, 39));
        for (int i = 15; i < width - 15; i++) {
            Block bl = new Block(new Rectangle(new Point(i, center.getY() - 100), 70, 20));
            bl.setColor(new Color(80, 194, 40));
            this.getBlocks().add(bl);
            i += 69;
        }
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }
}
