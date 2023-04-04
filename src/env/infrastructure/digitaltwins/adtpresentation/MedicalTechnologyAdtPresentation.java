/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.digitaltwins.adtpresentation;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import entity.medicaltechnology.MedicalTechnology;
import entity.medicaltechnology.MedicalTechnologyID;
import entity.medicaltechnology.MedicalTechnologyType;

import java.util.Optional;

/**
 * Presentation-related stuff for Azure Digital Twins about medical technologies.
 */
public final class MedicalTechnologyAdtPresentation {
    /** Relationship from room to medical technology. */
    public static final String IS_LOCATED_IN_OPERATING_ROOM_RELATIONSHIP = "rel_is_located";

    private static final String TYPE_PROPERTY = "type";
    private static final String TYPE_ENDOSCOPE = "0";
    private static final String TYPE_XRAY = "1";

    private MedicalTechnologyAdtPresentation() { }

    /**
     * Convert a {@link BasicDigitalTwin} to a {@link MedicalTechnology}.
     * @param digitalTwin the digital twin to convert
     * @return an optional with the medical technology if the conversion is possible, an empty optional otherwise
     */
    public static Optional<MedicalTechnology> toMedicalTechnology(final BasicDigitalTwin digitalTwin) {
        if (digitalTwin.getContents().containsKey(TYPE_PROPERTY)) {
            final Optional<MedicalTechnologyType> medicalTechnologyType =
                    toMedicalTechnologyType((String) digitalTwin.getContents().get(TYPE_PROPERTY));
            return medicalTechnologyType.map(type -> new MedicalTechnology(new MedicalTechnologyID(digitalTwin.getId()), type));
        }
        return Optional.empty();
    }

    private static Optional<MedicalTechnologyType> toMedicalTechnologyType(final String medicalTechnologyType) {
        return switch (medicalTechnologyType) {
            case TYPE_ENDOSCOPE -> Optional.of(MedicalTechnologyType.ENDOSCOPE);
            case TYPE_XRAY -> Optional.of(MedicalTechnologyType.XRAY);
            default -> Optional.empty();
        };
    }
}
