/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.actuator;

import java.util.Objects;

/**
 * Implementation of the actuator entity.
 */
public class Actuator {
    private final ActuatorID actuatorID;
    private final ActuatorType actuatorType;

    /**
     * Default constructor.
     * @param actuatorID the actuator id.
     * @param actuatorType the actuator type.
     */
    public Actuator(final ActuatorID actuatorID, final ActuatorType actuatorType) {
        this.actuatorID = actuatorID;
        this.actuatorType = actuatorType;
    }

    /**
     * Get the actuator id.
     * @return the actuator id.
     */
    public ActuatorID getId() {
        return this.actuatorID;
    }

    /**
     * Get the actuator type.
     * @return the actuator type.
     */
    public ActuatorType getActuatorType() {
        return this.actuatorType;
    }

    @Override
    public final boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Actuator actuator = (Actuator) other;
        return this.getId().equals(actuator.actuatorID);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.getId());
    }
}
