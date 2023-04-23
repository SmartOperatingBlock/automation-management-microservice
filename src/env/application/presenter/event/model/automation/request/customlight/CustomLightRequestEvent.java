/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.automation.request.customlight;

import application.presenter.event.model.Event;
import application.presenter.event.model.automation.request.customlight.payload.CustomLightSetupPayload;

/**
 * Custom light setup request event.
 * This is the event with which a doctor request a custom light setup inside the Operating Room.
 * @param <E> the type of the event payload.
 */
public class CustomLightRequestEvent<E extends CustomLightSetupPayload> implements Event<E> {
    private final String key;
    private final E data;
    private final String dateTime;

    /**
     * Default constructor.
     * @param key the key of the event.
     * @param data the data payload of the event.
     * @param dateTime the datetime of the event.
     */
    public CustomLightRequestEvent(
            final String key,
            final E data,
            final String dateTime
    ) {
        this.key = key;
        this.data = data;
        this.dateTime = dateTime;
    }

    @Override
    public final String getKey() {
        return this.key;
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
