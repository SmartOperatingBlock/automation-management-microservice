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
 * to the cooling part.
 */
public class Cooler extends Artifact {
    private String roomId;

    /**
     * Initialize the cooler artifact.
     * @param roomId the id of the room where the cooler is placed
     */
    void init(final String roomId) {
        this.roomId = roomId;
    }

    /**
     * Turn on the cooling system.
     */
    @OPERATION
    void turnOn() {
        Logger.getLogger(Cooler.class.toString()).info("[" + this.roomId + "]" + " ON");
    }

    /**
     * Turn off the cooling system.
     */
    @OPERATION
    void turnOff() {
        Logger.getLogger(Cooler.class.toString()).info("[" + this.roomId + "]" + " OFF");
    }
}
