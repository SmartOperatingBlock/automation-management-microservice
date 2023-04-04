/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.room;

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
}
