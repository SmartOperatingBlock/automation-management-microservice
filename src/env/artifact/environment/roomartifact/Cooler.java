/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment.roomartifact;

import cartago.OPERATION;
import entity.actuator.ActuatorType;
import entity.room.RoomID;

import java.util.logging.Logger;

/**
 * CArtAgO artifact that is responsible for the communication with the hvac system of the operating block respect
 * to the cooling part.
 */
public class Cooler extends AbstractActuatorInRoomArtifact implements SwitchableArtifact {
    /**
     * Initialize the cooler artifact.
     * @param roomId the room id where the cooler is placed.
     */
    void init(final String roomId) {
        super.init(ActuatorType.COOLING, new RoomID(roomId));
    }

    @Override
    @OPERATION
    public final void turnOn() {
        Logger.getLogger(Cooler.class.toString()).info("[" + this.getRoomId() + "] " + this.getActuatorID().getId() + " ON");
    }

    @Override
    @OPERATION
    public final void turnOff() {
        Logger.getLogger(Cooler.class.toString()).info("[" + this.getRoomId() + "] " + this.getActuatorID().getId() + " OFF");
    }
}
