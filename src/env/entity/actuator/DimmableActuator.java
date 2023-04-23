/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.actuator;

import entity.actuator.invoker.DimmableInvoker;

/**
 * An {@link Actuator} that can be dimmed.
 */
public class DimmableActuator extends Actuator {
    /**
     * Default constructor.
     * @param actuatorID the actuator id.
     * @param actuatorType the actuator type.
     */
    public DimmableActuator(final ActuatorID actuatorID, final ActuatorType actuatorType) {
        super(actuatorID, actuatorType);
    }

    /**
     * Set the intensity of the actuator.
     * @param intensity the intensity.
     * @param invoker the invoker to call
     */
    public void setIntensity(final int intensity, final DimmableInvoker invoker) {
        invoker.setIntensity(this.getId(), intensity);
    }
}
