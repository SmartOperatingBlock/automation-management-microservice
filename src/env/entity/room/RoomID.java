/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.room;

import java.util.Objects;

/**
 * Room identifier.
 */
public class RoomID {
    private final String id;

    /**
     * Default constructor.
     * @param id the id.
     */
    public RoomID(final String id) {
        this.id = id;
    }

    /**
     * Get the id.
     * @return the id.
     */
    public String getId() {
        return this.id;
    }

    @Override
    public final boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        final RoomID that = (RoomID) other;
        return this.getId().equals(that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.getId());
    }
}
