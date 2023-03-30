/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.roomevent.payload;

/**
 * Humidity payload for {@link application.presenter.event.model.roomevent.RoomEvent}.
 */
public class HumidityPayload implements RoomEventPayload {
    /** Humidity event key. */
    public static final String HUMIDITY_EVENT_KEY = "HUMIDITY_EVENT";
    private final int humidityPercentage;

    /**
     * Default constructor.
     * @param humidityPercentage the humidity percentage.
     */
    public HumidityPayload(final int humidityPercentage) {
        this.humidityPercentage = humidityPercentage;
    }

    /**
     * Get the Humidity percentage.
     * @return the humidity percentage.
     */
    public int getHumidityPercentage() {
        return this.humidityPercentage;
    }
}
