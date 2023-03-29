/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.roomevent.payload;

/**
 * Presence payload for {@link application.presenter.event.model.roomevent.RoomEvent}.
 */
public class PresencePayload implements RoomEventPayload {
    private final boolean presenceDetected;

    /**
     * Default constructor.
     * @param presenceDetected the presence payload.
     */
    public PresencePayload(final boolean presenceDetected) {
        this.presenceDetected = presenceDetected;
    }

    /**
     * State if the presence is detected or not.
     * @return true if the presence is detected, false otherwise.
     */
    public boolean isPresenceDetected() {
        return this.presenceDetected;
    }
}
