//318528874
package levels;

import biuoop.GUI;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import infoholders.SpriteCollection;
import sprites.Ball;
import sprites.Block;
import sprites.Background;
import sprites.Paddle;
import sprites.Circle;

import java.awt.Color;

/**
 * DirectHit class is the first level of the game.
 * has one block in middle of screen and the ball initiates velocity right to the block.
 */
public class DirectHit extends FatherOfLevels {

    /**
     * constructor, initiates all local fields in 'super' and then initiates unique attributes in 'initialize'.
     * @param gui a GUI for level's runner
     */
    public DirectHit(GUI gui) {
        super(gui);
        initialize(createObjects());
    }

    /**
     * method that creates all Sprites that make up the background of this level.
     * @return collection of sprites in background
     */
    private SpriteCollection createObjects() {
        double width = this.getRunner().getGui().getDrawSurface().getWidth(); //width of screen
        double height = this.getRunner().getGui().getDrawSurface().getHeight(); //height of screen
        SpriteCollection objects = new SpriteCollection();
        Circle c1 = new Circle(new Point(width / 2, height / 2 - 150), 50);
        Circle c2 = new Circle(new Point(width / 2, height / 2 - 150), 90);
        Circle c3 = new Circle(new Point(width / 2, height / 2 - 150), 130);
        c1.setColor(Color.BLUE);
        c2.setColor(Color.BLUE);
        c3.setColor(Color.BLUE);
        Line l1 = new Line(new Point(width / 2 - 150, height / 2 - 150),
                new Point(width / 2 - 20, height / 2 - 150));
        Line l2 = new Line(new Point(width / 2, height / 2 - 280),
                new Point(width / 2, height / 2 - 175));
        Line l3 = new Line(new Point(width / 2 + 150, height / 2 - 150),
                new Point(width / 2 + 20, height / 2 - 150));
        Line l4 = new Line(new Point(width / 2, height / 2 - 125),
                new Point(width / 2, height / 2 + 5));
        l1.setColor(Color.BLUE);
        l2.setColor(Color.BLUE);
        l3.setColor(Color.BLUE);
        l4.setColor(Color.BLUE);
        objects.addSprite(c1);
        objects.addSprite(c2);
        objects.addSprite(c3);
        objects.addSprite(l1);
        objects.addSprite(l2);
        objects.addSprite(l3);
        objects.addSprite(l4);
        return objects;
    }

    /**
     * method to initialize all unique level attributes.
     * @param objects collection of sprites in level's background
     */
    private void initialize(SpriteCollection objects) {
        double width = this.getRunner().getGui().getDrawSurface().getWidth();
        double height = this.getRunner().getGui().getDrawSurface().getHeight();
        this.setBalls(Ball.makeBallArr(new int[]{5}, new Point(width / 2.0, height - 12)));
        for (Ball b: this.getBalls()) {
            b.setVelocity(0, -5);
        }
        this.setPaddle(new Paddle(this.getRunner().getGui().getKeyboardSensor(), 80, 5));
        this.setBackground(new Background(new Rectangle(new Point(0, 0), width, height), objects));
        this.getBackground().setColor(new Color(137, 39, 39));
        this.getBlocks().add(new Block(new Rectangle(
                new Point(width / 2.0 - 15, height / 2.0 - 165), 30, 30)));
        for (Block bl: this.getBlocks()) {
            bl.setColor(new Color(0, 0, 0));
        }
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }
}
