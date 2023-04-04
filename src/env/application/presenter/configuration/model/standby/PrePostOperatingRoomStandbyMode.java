/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.configuration.model.standby;

import application.presenter.configuration.model.environment.EnvironmentalDataConfig;

/**
 * Standby configuration for pre/post operating room.
 */
public class PrePostOperatingRoomStandbyMode extends StandbyMode {
    private EnvironmentalDataConfig environment;

    /**
     * Default constructor.
     */
    public PrePostOperatingRoomStandbyMode() {
        super();
    }

    /**
     * Constructor to perform copy.
     * @param other to copy.
     */
    public PrePostOperatingRoomStandbyMode(final PrePostOperatingRoomStandbyMode other) {
        super(other);
        this.environment = new EnvironmentalDataConfig(other.environment);
    }

    /**
     * Standby mode environmental data configuration getter.
     * @return the environmental data configuration.
     */
    public EnvironmentalDataConfig getEnvironment() {
        return new EnvironmentalDataConfig(this.environment);
    }

    /**
     * Set the environmental data config for standby mode.
     * @param environment config to set.
     */
    public void setEnvironment(final EnvironmentalDataConfig environment) {
        this.environment = new EnvironmentalDataConfig(environment);
    }
}
