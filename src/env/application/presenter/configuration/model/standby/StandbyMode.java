/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.configuration.model.standby;

/**
 * Class that models the standby mode in the configuration.
 */
public class StandbyMode {
    private Boolean enabled;
    private Integer minutesToEnable;

    /** Default constructor. */
    public StandbyMode() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     * Constructor to perform copy.
     * @param other to copy.
     */
    public StandbyMode(final StandbyMode other) {
        this.enabled = other.enabled;
        this.minutesToEnable = other.minutesToEnable;
    }

    /**
     * Enabled getter.
     * @return true if enabled, false otherwise.
     */
    public Boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Enable setter.
     * @param enabled the status to enable or disable.
     */
    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Minutes to enable standby mode getter.
     * @return the minutes.
     */
    public Integer getMinutesToEnable() {
        return this.minutesToEnable;
    }

    /**
     * Set the minutes of no activity to enable standby mode.
     * @param minutesToEnable minutes of no activity to turn the standby mode on.
     */
    public void setMinutesToEnable(final Integer minutesToEnable) {
        this.minutesToEnable = minutesToEnable;
    }
}
