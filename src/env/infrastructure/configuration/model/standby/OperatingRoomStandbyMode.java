/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.configuration.model.standby;

import infrastructure.configuration.model.environment.OperatingRoomEnvironmentalDataConfig;

/**
 * Standby configuration for operating room.
 */
public class OperatingRoomStandbyMode extends StandbyMode {
    private OperatingRoomEnvironmentalDataConfig environment;

    /** Default constructor. */
    public OperatingRoomStandbyMode() {
        super();
    }

    /**
     * Constructor to perform copy.
     * @param other to copy.
     */
    public OperatingRoomStandbyMode(final OperatingRoomStandbyMode other) {
        super(other);
        this.environment = new OperatingRoomEnvironmentalDataConfig(other.environment);
    }

    /**
     * Standby mode environmental data configuration getter.
     * @return the environmental data configuration.
     */
    public OperatingRoomEnvironmentalDataConfig getEnvironment() {
        return new OperatingRoomEnvironmentalDataConfig(this.environment);
    }

    /**
     * Set the environmental data config for standby mode.
     * @param environment config to set.
     */
    public void setEnvironment(final OperatingRoomEnvironmentalDataConfig environment) {
        this.environment = new OperatingRoomEnvironmentalDataConfig(environment);
    }
}
