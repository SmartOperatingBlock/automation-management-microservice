/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.roomevent;

import application.presenter.event.model.Event;
import application.presenter.event.model.roomevent.payload.RoomEventPayload;

/**
 * Room environment conditions update event.
 * In addition to a normal {@link application.presenter.event.model.Event} it has the roomId and the roomType to which
 * the event refers.
 * @param <E> the type of the event payload.
 */
public class RoomEvent<E extends RoomEventPayload> implements Event<E> {
    private final String key;
    private final String roomId;
    private final RoomTypePayload roomType;
    private final E data;
    private final String dateTime;

    /**
     * Default constructor.
     * @param key the key of the event.
     * @param roomId the room identification to which the event is related to.
     * @param roomType the room type to which the event is related to.
     * @param data the payload.
     * @param dateTime the date time of the event.
     */
    public RoomEvent(
            final String key,
            final String roomId,
            final RoomTypePayload roomType,
            final E data,
            final String dateTime
    ) {
        this.key = key;
        this.roomId = roomId;
        this.roomType = roomType;
        this.data = data;
        this.dateTime = dateTime;
    }

    @Override
    public final String getKey() {
        return this.key;
    }

    /**
     * Obtain the room id to which the event is related to.
     * @return the room id.
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * Obtain the room type to which the event is related to.
     * @return the room type.
     */
    public RoomTypePayload getRoomType() {
        return this.roomType;
    }

    @Override
    public final E getData() {
        return this.data;
    }

    @Override
    public final String getDateTime() {
        return this.dateTime;
    }
}
