//318528874
package managers;

import animations.EndScreen;
import biuoop.KeyboardSensor;
import infoholders.Counter;
import infoholders.LevelInformation;
import levels.GameLevel;

import java.util.List;

/**
 * GameFlow class is responsible for managing between the different animations and levels that the game goes through.
 */
public class GameFlow {

    private final AnimationRunner runner;
    private final KeyboardSensor ks;
    private final Counter score;

    /**
     * constructor, initializing values to all fields.
     * @param ar given runner to assign to local runner field
     * @param ks given keyboard to assign to local keyboard field
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.ks = ks;
        this.score = new Counter();
    }

    /**
     * method that runs all levels given in argument and manages the play in each level.
     * @param levels levels to run throughout game
     */
    public void runLevels(List<LevelInformation> levels) {
        int maxScore = 0;
        for (LevelInformation levelInfo : levels) {
            maxScore += levelInfo.numberOfBlocksToRemove() * 5 + 100; // 5 points for block, 100 for clearing level
        }
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.runner, this.score);
            level.initialize();

            while (!level.blocksFinished() && !level.noBalls()) {
                level.run();
            }

            if (level.noBalls()) {
                this.runner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                        new EndScreen("game over. your score is: ", this.score.getValue())));
                break;
            }
        }
        if (this.score.getValue() == maxScore) {
            this.runner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                    new EndScreen("you win! your score is: ", this.score.getValue())));
        }
        this.runner.getGui().close();
    }
}
