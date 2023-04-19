/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.medicaltechnology;

import entity.room.RoomID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test for {@link MedicalTechnology} entity.
 */
class MedicalTechnologyTest {
    private final MedicalTechnology medicalTechnology = new MedicalTechnology(
            new MedicalTechnologyID("medicalTechnology"),
            MedicalTechnologyType.ENDOSCOPE,
            Optional.of(new RoomID("room1")));
    private final MedicalTechnology medicalTechnologyUpdated = new MedicalTechnology(
            new MedicalTechnologyID("medicalTechnology"),
            MedicalTechnologyType.ENDOSCOPE,
            Optional.empty());
    private final MedicalTechnology differentMedicalTechnology = new MedicalTechnology(
            new MedicalTechnologyID("medicalTechnology1"),
            MedicalTechnologyType.ENDOSCOPE,
            Optional.of(new RoomID("room1")));

    @Test
    @DisplayName("A medical technology must not be equal to other medical technologies with different id other classes")
    void testMedicalTechnologyNotEqual() {
        List.of(differentMedicalTechnology, new MedicalTechnologyID("id"), 4).forEach(element ->
                assertNotEquals(medicalTechnology, element)
        );
    }

    @Test
    @DisplayName("Two medical technologies are equal only based on their ids")
    void testMedicalTechnologyEqual() {
        assertEquals(medicalTechnology, medicalTechnologyUpdated);
    }

    @Test
    @DisplayName("Two equal medical technologies must have the same hash code")
    void testMedicalTechnologyEqualHashCode() {
        assertEquals(medicalTechnology.hashCode(), medicalTechnologyUpdated.hashCode());
    }

    @Test
    @DisplayName("Two different medical technology must not have the same hash code")
    void testMedicalTechnologyNotEqualHashCode() {
        assertNotEquals(medicalTechnology.hashCode(), differentMedicalTechnology.hashCode());
    }
}
