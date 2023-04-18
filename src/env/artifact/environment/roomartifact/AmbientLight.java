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
import entity.actuator.DimmableActuator;
import entity.room.RoomID;
import infrastructure.wot.WotInvoker;

import java.util.logging.Logger;

/**
 * CArtAgO artifact that is responsible for the communication with an ambient light inside a room of the
 * Operating Block.
 */
public class AmbientLight extends AbstractActuatorInRoomArtifact implements DimmableArtifact {
    private DimmableActuator actuator;
    private WotInvoker wotInvoker;
    /**
     * Initialize the ambient light artifact.
     * @param roomId the room id where the ambient light is placed.
     */
    void init(final String roomId) {
        super.init(ActuatorType.AMBIENT_LIGHT, new RoomID(roomId));
        this.actuator = new DimmableActuator(this.getActuatorID());
        this.wotInvoker = new WotInvoker();
    }

    @Override
    @OPERATION
    public final void setIntensity(final int luxToSet) {
        Logger.getLogger(AmbientLight.class.toString())
              .info("[" + this.getRoomId().getId() + "] " + this.getActuatorID().getId() + " Set " + luxToSet + " LUX");
        this.actuator.setIntensity(luxToSet, this.wotInvoker);
    }
}
