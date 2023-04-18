/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment.roomartifact;

import cartago.Artifact;
import entity.actuator.ActuatorID;
import entity.actuator.ActuatorType;
import entity.room.RoomID;
import infrastructure.digitaltwins.DigitalTwinManager;

import java.util.Optional;

/**
 * Abstract class that represent the base class for a CArtAgO boundary artifacts for an actuator placed
 * inside a Room within the Operating Block.
 */
public abstract class AbstractActuatorInRoomArtifact extends Artifact {
    private RoomID roomId;
    private ActuatorID actuatorID;

    /**
     * Initialize the artifact.
     * This method is called by Jason agents.
     * @param actuatorType the type of the actuator.
     * @param roomId the id of the room where the artifact is placed.
     */
    protected void init(final ActuatorType actuatorType, final RoomID roomId) {
        this.roomId = roomId;
        final Optional<ActuatorID> resultID = new DigitalTwinManager().findActuatorInRoom(actuatorType, roomId);
        if (resultID.isPresent()) {
            this.actuatorID = resultID.get();
        } else {
            throw new IllegalArgumentException("No actuator of type " + actuatorType + " available in room: " + roomId);
        }
    }

    /**
     * Obtain the roomId where the artifact is placed.
     * @return the room id.
     */
    public RoomID getRoomId() {
        return this.roomId;
    }

    /**
     * Obtain the actuatorID of the actuator.
     * @return the actuator id.
     */
    public ActuatorID getActuatorID() {
        return this.actuatorID;
    }
}
