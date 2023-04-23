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
 * CArtAgO artifact that is responsible for the communication with the hvac system of the operating block respect
 * to the ventilation part.
 */
public class Ventilation extends AbstractActuatorInRoomArtifact implements DimmableArtifact {
    private DimmableActuator actuator;
    private WotInvoker wotInvoker;
    /**
     * Initialize the ventilation artifact.
     * @param roomId the room id where the ventilation is placed.
     */
    void init(final String roomId) {
        super.init(ActuatorType.VENTILATION, new RoomID(roomId));
        this.actuator = new DimmableActuator(this.getActuatorID(), ActuatorType.VENTILATION);
        this.wotInvoker = new WotInvoker();
    }

    @Override
    @OPERATION
    public final void setIntensity(final int intensityPercentage) {
        if (intensityPercentage < 0 || intensityPercentage > 100) {
            throw new IllegalArgumentException("Intensity must be an integer percentage");
        }

        Logger.getLogger(Ventilation.class.toString())
              .info("[" + this.getRoomId() + "] " + this.getActuatorID().getId() + " Set " + intensityPercentage);
        this.actuator.setIntensity(intensityPercentage, this.wotInvoker);
    }
}
