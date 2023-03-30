/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.roomevent.payload;

/**
 * Luminosity payload for {@link application.presenter.event.model.roomevent.RoomEvent}.
 */
public class LuminosityPayload implements RoomEventPayload {
    /** Luminosity event key. */
    public static final String LUMINOSITY_EVENT_KEY = "LUMINOSITY_EVENT";
    private final double luminosityValue;
    private final String luminosityUnit;

    /**
     * Default constructor.
     * @param luminosityValue the luminosity value.
     * @param luminosityUnit the luminosity unit.
     */
    public LuminosityPayload(final double luminosityValue, final String luminosityUnit) {
        this.luminosityValue = luminosityValue;
        this.luminosityUnit = luminosityUnit;
    }

    /**
     * Get the luminosity value.
     * @return the luminosity.
     */
    public double getLuminosityValue() {
        return this.luminosityValue;
    }

    /**
     * Get the luminosity unit.
     * @return the luminosity unit.
     */
    public String getLuminosityUnit() {
        return this.luminosityUnit;
    }
}
