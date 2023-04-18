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
 * to the heating part.
 */
public class Heater extends AbstractActuatorInRoomArtifact implements SwitchableArtifact {
    /**
     * Initialize the heater artifact.
     * @param roomId the room id where the heater is placed.
     */
    void init(final String roomId) {
        super.init(ActuatorType.HEATING, new RoomID(roomId));
    }

    @Override
    @OPERATION
    public final void turnOn() {
        Logger.getLogger(Heater.class.toString()).info("[" + this.getRoomId().getId() + "] "
                + this.getActuatorID().getId() + " ON");
    }

    @Override
    @OPERATION
    public final void turnOff() {
        Logger.getLogger(Heater.class.toString()).info("[" + this.getRoomId().getId() + "] "
                + this.getActuatorID().getId() + " OFF");
    }
}
