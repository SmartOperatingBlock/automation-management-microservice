/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.actuator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for the {@link ActuatorID} value object.
 */
class ActuatorIDTest {
    private final ActuatorID actuatorID = new ActuatorID("id1");
    private final ActuatorID actuatorIDDifferentRef = new ActuatorID("id1");
    private final ActuatorID differentActuatorID = new ActuatorID("id2");

    @Test
    @DisplayName("Actuator ID must not be empty")
    void testActuatorIDEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new ActuatorID(""));
    }

    @Test
    @DisplayName("two ActuatorID must be equal on the base of their fields "
                 + "(being a value object), not on the base of their reference.")
    void testActuatorIDEquals() {
        assertEquals(actuatorID, actuatorIDDifferentRef);
    }

    @Test
    @DisplayName("two equal Actuator ID must have the same hash code")
    void testActuatorIDEqualHashCode() {
        assertEquals(actuatorID.hashCode(), actuatorIDDifferentRef.hashCode());
    }

    @Test
    @DisplayName("two ActuatorID with different ids must not be equal")
    void testActuatorIDNotEqual() {
        assertNotEquals(actuatorID, differentActuatorID);
    }
}
