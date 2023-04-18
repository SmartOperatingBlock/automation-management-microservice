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
 * CArtAgO artifact that is responsible for the communication with a surgical light inside an operating room of the
 * Operating Block.
 */
public class SurgicalLight extends AbstractActuatorInRoomArtifact implements DimmableArtifact {
    /**
     * Initialize the surgical light artifact.
     * @param roomId the room id where the surgical light is placed.
     */
    void init(final String roomId) {
        super.init(ActuatorType.SURGICAL_LIGHT, new RoomID(roomId));
    }

    @Override
    @OPERATION
    public final void setIntensity(final int luxToSet) {
        Logger.getLogger(SurgicalLight.class.toString())
                .info("[" + this.getRoomId() + "] " + this.getActuatorID().getId() + " Set " + luxToSet + " LUX");
    }
}
