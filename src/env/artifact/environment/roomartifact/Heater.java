/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment.roomartifact;

import cartago.OPERATION;
import entity.actuator.ActuatorID;
import entity.actuator.ActuatorType;
import entity.room.RoomID;
import infrastructure.digitaltwins.DigitalTwinManager;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * CArtAgO artifact that is responsible for the communication with the hvac system of the operating block respect
 * to the heating part.
 */
public class Heater extends AbstractRoomArtifact implements SwitchableArtifact {
    private ActuatorID heaterId;

    @Override
    protected final void init(final String roomId) {
        super.init(roomId);
        final Optional<ActuatorID> actuatorID =
                new DigitalTwinManager().findActuatorInRoom(ActuatorType.HEATING, new RoomID(roomId));
        if (actuatorID.isPresent()) {
            this.heaterId = actuatorID.get();
        } else {
            throw new IllegalArgumentException("No Heater available in room: " + roomId);
        }
    }

    @Override
    @OPERATION
    public final void turnOn() {
        Logger.getLogger(Heater.class.toString()).info("[" + this.getRoomId() + "] " + this.heaterId.getId() + " ON");
    }

    @Override
    @OPERATION
    public final void turnOff() {
        Logger.getLogger(Heater.class.toString()).info("[" + this.getRoomId() + "] " + this.heaterId.getId() + " OFF");
    }
}
