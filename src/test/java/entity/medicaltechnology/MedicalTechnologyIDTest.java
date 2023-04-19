/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.medicaltechnology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for the {@link MedicalTechnologyID} value object.
 */
class MedicalTechnologyIDTest {
    private final MedicalTechnologyID medicalTechnologyID = new MedicalTechnologyID("id1");
    private final MedicalTechnologyID medicalTechnologyIDDifferentRef = new MedicalTechnologyID("id1");
    private final MedicalTechnologyID differentMedicalTechnologyID = new MedicalTechnologyID("id2");

    @Test
    @DisplayName("Medical Technology ID must not be empty")
    void testMedicalTechnologyIDEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new MedicalTechnologyID(""));
    }

    @Test
    @DisplayName("two MedicalTechnologyID must be equal on the base of their fields "
                 + "(being a value object), not on the base of their reference")
    void testMedicalTechnologyIDEquals() {
        assertEquals(medicalTechnologyID, medicalTechnologyIDDifferentRef);
    }

    @Test
    @DisplayName("two equal MedicalTechnologyID must have the same hash code")
    void testMedicalTechnologyIDEqualHashCode() {
        assertEquals(medicalTechnologyID.hashCode(), medicalTechnologyIDDifferentRef.hashCode());
    }

    @Test
    @DisplayName("two MedicalTechnologyID with different ids must not be equal.")
    void testMedicalTechnologyIDNotEqual() {
        assertNotEquals(medicalTechnologyID, differentMedicalTechnologyID);
    }
}
