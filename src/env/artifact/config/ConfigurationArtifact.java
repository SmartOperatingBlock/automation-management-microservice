/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.config;

import application.controller.manager.ConfigurationLoader;
import cartago.Artifact;
import cartago.OPERATION;
import application.presenter.configuration.model.Configuration;
import infrastructure.configuration.FileConfigurationLoader;
import java.util.Objects;

/**
 * CArtAgO artifact to load the user-provided configuration of the automation management.
 */
public class ConfigurationArtifact extends Artifact {
    private static final String CONFIGURATION_PATH_VARIABLE = "CONFIG_PATH";
    /**
     * Initialize the configuration artifact.
     */
    void init() {
        Objects.requireNonNull(System.getenv(CONFIGURATION_PATH_VARIABLE), "Missing configuration path");
    }

    /**
     * Operation that loads the configuration provided by the user.
     */
    @OPERATION
    void load() {
        final ConfigurationLoader configurationLoader =
                new FileConfigurationLoader(System.getenv(CONFIGURATION_PATH_VARIABLE));
        final Configuration configuration = configurationLoader.loadConfiguration();
        // Signal the configuration to the observers
        // Operating room environment configuration
        signal("operatingRoom",
                configuration.getOperatingRoom().getTemperature(),
                configuration.getOperatingRoom().getHumidity(),
                configuration.getOperatingRoom().getAmbientIlluminance(),
                configuration.getOperatingRoom().getSurgicalIlluminance());

        // Pre post operating room environment configuration
        signal("preOperatingRoom",
                configuration.getPrePostOperatingRoom().getTemperature(),
                configuration.getPrePostOperatingRoom().getHumidity(),
                configuration.getPrePostOperatingRoom().getAmbientIlluminance());

        // Operating room standby mode
        if (configuration.getOperatingRoomStandbyMode().isEnabled()) {
            signal("operatingRoomStandby",
                    configuration.getOperatingRoomStandbyMode().getMinutesToEnable(),
                    configuration.getOperatingRoomStandbyMode().getEnvironment().getTemperature(),
                    configuration.getOperatingRoomStandbyMode().getEnvironment().getHumidity(),
                    configuration.getOperatingRoomStandbyMode().getEnvironment().getAmbientIlluminance(),
                    configuration.getOperatingRoomStandbyMode().getEnvironment().getSurgicalIlluminance());
        }

        // Pre post operating room standby mode
        if (configuration.getPrePostOperatingRoomStandbyMode().isEnabled()) {
            signal("prePostOperatingRoomStandby",
                    configuration.getPrePostOperatingRoomStandbyMode().getMinutesToEnable(),
                    configuration.getPrePostOperatingRoomStandbyMode().getEnvironment().getTemperature(),
                    configuration.getPrePostOperatingRoomStandbyMode().getEnvironment().getHumidity(),
                    configuration.getPrePostOperatingRoomStandbyMode().getEnvironment().getAmbientIlluminance());
        }

        // Medical technologies scenarios
        configuration.getMedicalTechnologyScenarios().forEach(scenario ->
                signal("medicalTechnologyScenario",
                    scenario.getMedicalTechnologyType(),
                    scenario.getAmbientIlluminance(),
                    scenario.getSurgicalIlluminance()));
    }
}
