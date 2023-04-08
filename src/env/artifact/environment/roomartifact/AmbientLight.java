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
 * CArtAgO artifact that is responsible for the communication with an ambient light inside a room of the
 * Operating Block.
 */
public class AmbientLight extends AbstractRoomArtifact implements DimmableArtifact {
    private ActuatorID ambientLightId;

    @Override
    protected final void init(final String roomId) {
        super.init(roomId);
        final Optional<ActuatorID> actuatorID =
                new DigitalTwinManager().findActuatorInRoom(ActuatorType.AMBIENT_LIGHT, new RoomID(roomId));
        if (actuatorID.isPresent()) {
            this.ambientLightId = actuatorID.get();
        } else {
            throw new IllegalArgumentException("No Ambient Light available in room: " + roomId);
        }
    }

    @Override
    @OPERATION
    public final void setIntensity(final int luxToSet) {
        Logger.getLogger(AmbientLight.class.toString())
              .info("[" + this.getRoomId() + "] " + this.ambientLightId.getId() + " Set " + luxToSet + " LUX");
    }
}
