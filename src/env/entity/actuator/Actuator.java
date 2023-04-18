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

    /**
     * Default constructor.
     * @param actuatorID the actuator id.
     */
    public Actuator(final ActuatorID actuatorID) {
        this.actuatorID = actuatorID;
    }

    /**
     * Get the actuator id.
     * @return the actuator id.
     */
    public final ActuatorID getId() {
        return this.actuatorID;
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
