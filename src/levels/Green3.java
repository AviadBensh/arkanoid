//318528874
package levels;

import biuoop.GUI;
import geometry.Point;
import geometry.Rectangle;
import infoholders.SpriteCollection;
import sprites.Block;
import sprites.Background;
import sprites.Paddle;
import sprites.Ball;

import java.awt.Color;
import java.util.Random;

/**
 * Green3 class is the third level in the game's levels.
 * it has 2 balls, normal sized paddle, and downgrading rows of blocks
 */
public class Green3 extends FatherOfLevels {

    /**
     * constructor, initializing all class fields.
     * @param gui a GUI for level's runner
     */
    public Green3(GUI gui) {
        super(gui);
        initialize();
    }

    /**
     * method to initialize real attributes to each field, unique to this specific level.
     */
    private void initialize() {
        double width = this.getRunner().getGui().getDrawSurface().getWidth();
        double height = this.getRunner().getGui().getDrawSurface().getHeight();
        this.setBalls(Ball.makeBallArr(new int[]{5, 5}, new Point(width / 2.0, height - 12)));
        double dx = -5;
        double dy = -2.5;
        for (Ball b : this.getBalls()) {
            b.setVelocity(dx, dy);
            b.setColor(Color.WHITE);
            dx *= -1;
        }
        this.setPaddle(new Paddle(this.getRunner().getGui().getKeyboardSensor(), 110, 5));
        this.setBackground(new Background(new Rectangle(new Point(0, 0), width, height), new SpriteCollection()));
        this.getBackground().setColor(new Color(40, 69, 24));
        //start x-coordinate of top row of blocks, and create gradual decline of row length via for loop.
        int startOfFirstRow = 185;
        int blockWidth = 60;
        int blockHeight = 30;
        for (int y = 80; y < 230; y += blockHeight) {

            //randomize color of row
            float r, g, b;
            Random rand = new Random();
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
            Color color = new Color(r, g, b);
            for (int x = startOfFirstRow; x < 750; x += blockWidth) {
                Block block = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight));
                block.setColor(color);
                this.getBlocks().add(block);
            }
            startOfFirstRow += blockWidth;
        }
    }

    @Override
    public String levelName() {
        return "Green 3";
    }
}
