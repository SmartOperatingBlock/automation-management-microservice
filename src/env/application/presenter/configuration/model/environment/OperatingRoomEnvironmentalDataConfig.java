/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.configuration.model.environment;

/**
 * Class that model the environmental data of an operating room.
 * It differs from the standard one for the presence of the surgical lights.
 */
public class OperatingRoomEnvironmentalDataConfig extends EnvironmentalDataConfig {
    private Double surgicalIlluminance;

    /** Default constructor. */
    public OperatingRoomEnvironmentalDataConfig() {
        super();
    }

    /**
     * Constructor to perform copy.
     * @param other to copy.
     */
    public OperatingRoomEnvironmentalDataConfig(final OperatingRoomEnvironmentalDataConfig other) {
        super(other);
        this.surgicalIlluminance = other.surgicalIlluminance;
    }

    /**
     * Surgical illuminance getter.
     * @return the surgical illuminance.
     */
    public Double getSurgicalIlluminance() {
        return this.surgicalIlluminance;
    }

    /**
     * Surgical illuminance setter.
     * @param surgicalIlluminance to set.
     */
    public void setSurgicalIlluminance(final Double surgicalIlluminance) {
        this.surgicalIlluminance = surgicalIlluminance;
    }
}
