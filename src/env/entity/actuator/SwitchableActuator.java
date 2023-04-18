/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.actuator;

import entity.actuator.invoker.SwitchableInvoker;

/**
 * An {@link Actuator} that can be switched on and off.
 */
public class SwitchableActuator extends Actuator {
    /**
     * Default constructor.
     * @param actuatorID the actuator id.
     */
    public SwitchableActuator(final ActuatorID actuatorID) {
        super(actuatorID);
    }

    /**
     * Turn on the actuator.
     * @param invoker the invoker to call.
     */
    public void turnOn(final SwitchableInvoker invoker) {
        invoker.turnOn();
    }

    /**
     * Turn off the actuator.
     * @param invoker the invoker to call.
     */
    public void turnOff(final SwitchableInvoker invoker) {
        invoker.turnOff();
    }
}
