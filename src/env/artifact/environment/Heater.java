/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment;

import cartago.Artifact;
import cartago.OPERATION;

import java.util.logging.Logger;

/**
 * CArtAgO artifact that is responsible for the communication with the hvac system of the operating block respect
 * to the heating part.
 */
public class Heater extends Artifact {
    private String roomId;

    /**
     * Initialize the heater artifact.
     * @param roomId the id of the room where the heater is placed
     */
    void init(final String roomId) {
        this.roomId = roomId;
    }

    /**
     * Turn on the heating system.
     */
    @OPERATION
    void turnOn() {
        Logger.getLogger(Heater.class.toString()).info("[" + this.roomId + "]" + " ON");
    }

    /**
     * Turn off the heating system.
     */
    @OPERATION
    void turnOff() {
        Logger.getLogger(Heater.class.toString()).info("[" + this.roomId + "]" + " OFF");
    }
}
