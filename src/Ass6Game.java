//318528874
import biuoop.GUI;
import levels.DirectHit;
import levels.WideEasy;
import levels.Green3;
import levels.FinalFour;
import managers.AnimationRunner;
import managers.GameFlow;
import infoholders.LevelInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Ass5Game class manages the initialization and start of the game.
 *
 * @author Aviad Ben-Shoshan
 * @since 25-04-2021
 */
public class Ass6Game {

    /**
     * main method that creates, initializes and runs the game.
     * @param args command-line arguments (none expected)
     */
    public static void main(String[] args) {

        int flag = 0;
        GUI runnerGui = new GUI("arkanoid", 800, 600);

        //map a string representation of number to each level
        Map<String, LevelInformation> mapLevels = new TreeMap<>();
        mapLevels.put("1", new DirectHit(runnerGui));
        mapLevels.put("2", new WideEasy(runnerGui));
        mapLevels.put("3", new Green3(runnerGui));
        mapLevels.put("4", new FinalFour(runnerGui));

        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit(runnerGui));
        levels.add(new WideEasy(runnerGui));
        levels.add(new Green3(runnerGui));
        levels.add(new FinalFour(runnerGui));

        // iterate through args and run levels according to map keys. skip any argument that doesn't match a level
        for (String s: args) {
            if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")) {
                levels.add(mapLevels.get(s));
                flag = 1;
            }
        }
        if (flag == 0) {
            levels.add(mapLevels.get("1"));
            levels.add(mapLevels.get("2"));
            levels.add(mapLevels.get("3"));
            levels.add(mapLevels.get("4"));
        }
        AnimationRunner runner = new AnimationRunner(runnerGui);
        GameFlow gf = new GameFlow(runner, runner.getGui().getKeyboardSensor());
        gf.runLevels(levels);
    }
}
