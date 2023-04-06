/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.digitaltwins.adtpresentation;

import entity.actuator.ActuatorType;

/**
 * Presentation-related stuff for Azure Digital Twins about actuators.
 */
public final class ActuatorAdtPresentation {
    /** Relationship from room to medical technology. */
    public static final String HAS_ACTUATOR_RELATIONSHIP = "rel_has_actuator";

    private static final String HEATING_TYPE = "0";
    private static final String COOLING_TYPE = "1";
    private static final String VENTILATION_TYPE = "2";
    private static final String AMBIENT_LIGHT_TYPE = "3";
    private static final String SURGICAL_LIGHT_TYPE = "4";

    private ActuatorAdtPresentation() { }

    /**
     * Convert a {@link ActuatorType} to the type used internally by the Digital Twin model
     * on Azure Digital Twins.
     * @param actuatorType the type of actuator to convert.
     * @return the converted type respect to the Actuator Digital Twin model.
     */
    public static String toActuatorDigitalTwinType(final ActuatorType actuatorType) {
        return switch (actuatorType) {
            case HEATING -> HEATING_TYPE;
            case COOLING -> COOLING_TYPE;
            case VENTILATION -> VENTILATION_TYPE;
            case AMBIENT_LIGHT -> AMBIENT_LIGHT_TYPE;
            case SURGICAL_LIGHT -> SURGICAL_LIGHT_TYPE;
        };
    }
}
