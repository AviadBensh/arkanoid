//318528874
package managers;

import animations.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class is a decorator for animation that relies on orders from keyboard.
 */
public class KeyPressStoppableAnimation implements Animation {

    private final KeyboardSensor ks;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed;

    /**
     * constructor of all fields.
     * @param sensor keyboard
     * @param key string that represents button on keyboard.
     * @param animation animation that is being decorated
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.ks.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
        if (!this.isAlreadyPressed) {
            this.animation.doOneFrame(d);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.ks.isPressed(key);

    }
}
