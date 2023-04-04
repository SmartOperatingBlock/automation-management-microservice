/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.medicaltechnology;

import entity.room.RoomID;

import java.util.Objects;
import java.util.Optional;

/**
 * Describe a medical technology used inside the operating block.
 */
public class MedicalTechnology {
    private final MedicalTechnologyID id;
    private final MedicalTechnologyType type;
    private final Optional<RoomID> roomID;

    /**
     * Default constructor.
     * @param id the id.
     * @param type the type.
     * @param roomID the room id.
     */
    public MedicalTechnology(final MedicalTechnologyID id, final MedicalTechnologyType type, final Optional<RoomID> roomID) {
        this.id = id;
        this.type = type;
        this.roomID = roomID;
    }

    /**
     * Constructor of a medical technology that is not placed inside any room.
     * @param id the id.
     * @param type the type.
     */
    public MedicalTechnology(final MedicalTechnologyID id, final MedicalTechnologyType type) {
        this(id, type, Optional.empty());
    }

    /**
     * Get the id of the medical technology.
     * @return the id
     */
    public MedicalTechnologyID getId() {
        return this.id;
    }

    /**
     * Get the type of the medical technology.
     * @return the type
     */
    public MedicalTechnologyType getType() {
        return this.type;
    }

    /**
     * Get the room where the medical technology is currently placed.
     * @return the room id
     */
    public Optional<RoomID> getRoomID() {
        return this.roomID;
    }

    @Override
    public final boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        final MedicalTechnology that = (MedicalTechnology) other;
        return this.getId().equals(that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.getId());
    }
}
