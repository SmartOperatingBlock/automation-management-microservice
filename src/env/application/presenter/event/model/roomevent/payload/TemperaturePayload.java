/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.roomevent.payload;

/**
 * Temperature payload for {@link application.presenter.event.model.roomevent.RoomEvent}.
 */
public class TemperaturePayload implements RoomEventPayload {
    /** Temperature event key. */
    public static final String TEMPERATURE_EVENT_KEY = "TEMPERATURE_EVENT";
    private final double temperatureValue;
    private final String temperatureUnit;

    /**
     * Default constructor.
     * @param temperatureValue the temperature value.
     * @param temperatureUnit the temperature unit.
     */
    public TemperaturePayload(final double temperatureValue, final String temperatureUnit) {
        this.temperatureValue = temperatureValue;
        this.temperatureUnit = temperatureUnit;
    }

    /**
     * Get the temperature value.
     * @return the temperature value.
     */
    public double getTemperatureValue() {
        return this.temperatureValue;
    }

    /**
     * Get the temperature unit.
     * @return the temperature unit.
     */
    public String getTemperatureUnit() {
        return this.temperatureUnit;
    }
}
