//318528874
package infoholders;

import listeners.HitListener;

/**
 * HitNotifier interface, can add or remove listeners to an object that notifies upon being hit.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl listener to remove
     */
    void removeHitListener(HitListener hl);
}
