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
 * Final four class is the fourth level in the game's levels.
 * it has 3 balls, normal sized paddle, and several full rows of blocks
 */
public class FinalFour extends FatherOfLevels {

    /**
     * constructor, initiates all local fields in 'super' and then initiates unique attributes in 'initialize'.
     * @param gui a GUI for level's runner
     */
    public FinalFour(GUI gui) {
        super(gui);
        initialize();
    }
    /**
     * method to initialize real attributes to each field, unique to this specific level.
     */
    private void initialize() {
        double width = this.getRunner().getGui().getDrawSurface().getWidth();
        double height = this.getRunner().getGui().getDrawSurface().getHeight();
        this.setBalls(Ball.makeBallArr(new int[]{5, 5, 5}, new Point(width / 2.0, height - 12)));
        double dx = 5;
        double dy = -4;
        for (Ball b : this.getBalls()) {
            b.setVelocity(dx, dy);
            dx -= 4;
        }
        this.setPaddle(new Paddle(this.getRunner().getGui().getKeyboardSensor(), 80, 7));
        this.setBackground(new Background(new Rectangle(new Point(0, 0), width, height), new SpriteCollection()));
        this.getBackground().setColor(new Color(32, 106, 179, 255));

        int blockWidth = 70;
        int blockHeight = 25;
        for (int y = 80; y < 210; y += blockHeight) {

            //randomize color of row
            float r, g, b;
            Random rand = new Random();
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
            Color color = new Color(r, g, b);
            for (int x = 15; x < 750; x += blockWidth) {
                Block block = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight));
                block.setColor(color);
                this.getBlocks().add(block);
            }
        }
    }

    @Override
    public String levelName() {
        return "Final Four";
    }
}
