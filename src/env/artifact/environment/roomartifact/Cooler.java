/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment.roomartifact;

import cartago.OPERATION;

import java.util.logging.Logger;

/**
 * CArtAgO artifact that is responsible for the communication with the hvac system of the operating block respect
 * to the cooling part.
 */
public class Cooler extends AbstractRoomArtifact implements SwitchableArtifact {
    @Override
    @OPERATION
    public final void turnOn() {
        Logger.getLogger(Cooler.class.toString()).info("[" + this.getRoomId() + "]" + " ON");
    }

    @Override
    @OPERATION
    public final void turnOff() {
        Logger.getLogger(Cooler.class.toString()).info("[" + this.getRoomId() + "]" + " OFF");
    }
}
