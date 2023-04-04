/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.configuration.model.environment;

/**
 * Class that models the data provided in the configuration.
 */
public class EnvironmentalDataConfig {
    private Double temperature;
    private Double humidity;
    private Double ambientIlluminance;

    /** Default constructor. */
    public EnvironmentalDataConfig() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     * Constructor to perform copy.
     * @param other to copy.
     */
    public EnvironmentalDataConfig(final EnvironmentalDataConfig other) {
        this.temperature = other.temperature;
        this.humidity = other.humidity;
        this.ambientIlluminance = other.ambientIlluminance;
    }

    /**
     * Temperature getter.
     * @return the temperature.
     */
    public Double getTemperature() {
        return this.temperature;
    }

    /**
     * Temperature setter.
     * @param temperature the temperature to set.
     */
    public void setTemperature(final Double temperature) {
        this.temperature = temperature;
    }

    /**
     * Humidity getter.
     * @return the humidity.
     */
    public Double getHumidity() {
        return this.humidity;
    }

    /**
     * Humidity setter.
     * @param humidity to set.
     */
    public void setHumidity(final Double humidity) {
        this.humidity = humidity;
    }

    /**
     * Illuminance getter.
     * @return the illuminance.
     */
    public Double getAmbientIlluminance() {
        return this.ambientIlluminance;
    }

    /**
     * Illuminance setter.
     * @param ambientIlluminance to set.
     */
    public void setAmbientIlluminance(final Double ambientIlluminance) {
        this.ambientIlluminance = ambientIlluminance;
    }
}
