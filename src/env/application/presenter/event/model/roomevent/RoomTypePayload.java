/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.roomevent;

/**
 * The type of the room described in the {@link RoomEvent}.
 */
public enum RoomTypePayload {
    /** Operating room. */
    OPERATING_ROOM("operatingRoom"),
    /** Pre operating room. */
    PRE_OPERATING_ROOM("preOperatingRoom");

    private final String name;

    /**
     * Default constructor.
     * @param name the name
     */
    RoomTypePayload(final String name) {
        this.name = name;
    }

    /**
     * Obtain the name of the type.
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
