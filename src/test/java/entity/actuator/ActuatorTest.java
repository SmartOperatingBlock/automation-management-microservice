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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test for {@link Actuator} entity.
 */
class ActuatorTest {
    private final Actuator actuator = new Actuator(new ActuatorID("actuator1"), ActuatorType.HEATING);
    private final Actuator actuatorDifferentRef = new Actuator(new ActuatorID("actuator1"), ActuatorType.COOLING);
    private final Actuator differentActuator = new Actuator(new ActuatorID("actuator2"), ActuatorType.HEATING);

    @Test
    @DisplayName("An actuator must not be equal to other actuators with different id other classes")
    void testActuatorNotEqual() {
        List.of(differentActuator, new ActuatorID("id"), 4).forEach(element -> assertNotEquals(actuator, element));
    }

    @Test
    @DisplayName("Two actuators are equal only based on their ids")
    void testActuatorEqual() {
        assertEquals(actuator, actuatorDifferentRef);
    }

    @Test
    @DisplayName("Two equal actuators must have the same hash code")
    void testActuatorEqualHashCode() {
        assertEquals(actuator.hashCode(), actuatorDifferentRef.hashCode());
    }

    @Test
    @DisplayName("Two different actuators must not have the same hash code")
    void testActuatorNotEqualHashCode() {
        assertNotEquals(actuator.hashCode(), differentActuator.hashCode());
    }
}
