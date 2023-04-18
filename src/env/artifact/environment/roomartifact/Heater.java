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
import entity.actuator.SwitchableActuator;
import entity.room.RoomID;
import infrastructure.wot.WotInvoker;

import java.util.logging.Logger;

/**
 * CArtAgO artifact that is responsible for the communication with the hvac system of the operating block respect
 * to the heating part.
 */
public class Heater extends AbstractActuatorInRoomArtifact implements SwitchableArtifact {
    private SwitchableActuator actuator;
    private WotInvoker wotInvoker;
    /**
     * Initialize the heater artifact.
     * @param roomId the room id where the heater is placed.
     */
    void init(final String roomId) {
        super.init(ActuatorType.HEATING, new RoomID(roomId));
        this.actuator = new SwitchableActuator(this.getActuatorID());
        this.wotInvoker = new WotInvoker();
    }

    @Override
    @OPERATION
    public final void turnOn() {
        Logger.getLogger(Heater.class.toString()).info("[" + this.getRoomId().getId() + "] "
                + this.getActuatorID().getId() + " ON");
        actuator.turnOn(this.wotInvoker);
    }

    @Override
    @OPERATION
    public final void turnOff() {
        Logger.getLogger(Heater.class.toString()).info("[" + this.getRoomId().getId() + "] "
                + this.getActuatorID().getId() + " OFF");
        actuator.turnOff(this.wotInvoker);
    }
}
