/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.config;

import cartago.Artifact;
import cartago.OPERATION;
import application.presenter.configuration.model.Configuration;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        final Representer represent = new Representer(new DumperOptions());
        represent.getPropertyUtils().setSkipMissingProperties(true);
        final Yaml yaml = new Yaml(new Constructor(Configuration.class, new LoaderOptions()), represent);
        try (
                BufferedInputStream configInputStream = new BufferedInputStream(
                        new FileInputStream(System.getenv(CONFIGURATION_PATH_VARIABLE))
                )
        ) {
            final String fileContent = new String(configInputStream.readAllBytes(), StandardCharsets.UTF_8);
            final Configuration config = yaml.load(fileContent);
            // Signal the configuration to the observers
            // Operating room environment configuration
            signal("operatingRoom",
                    config.getOperatingRoom().getTemperature(),
                    config.getOperatingRoom().getHumidity(),
                    config.getOperatingRoom().getAmbientIlluminance(),
                    config.getOperatingRoom().getSurgicalIlluminance());

            // Pre post operating room environment configuration
            signal("preOperatingRoom",
                    config.getPrePostOperatingRoom().getTemperature(),
                    config.getPrePostOperatingRoom().getHumidity(),
                    config.getPrePostOperatingRoom().getAmbientIlluminance());

            // Operating room standby mode
            if (config.getOperatingRoomStandbyMode().isEnabled()) {
                signal("operatingRoomStandby",
                        config.getOperatingRoomStandbyMode().getMinutesToEnable(),
                        config.getOperatingRoomStandbyMode().getEnvironment().getTemperature(),
                        config.getOperatingRoomStandbyMode().getEnvironment().getHumidity(),
                        config.getOperatingRoomStandbyMode().getEnvironment().getAmbientIlluminance(),
                        config.getOperatingRoomStandbyMode().getEnvironment().getSurgicalIlluminance());
            }

            // Pre post operating room standby mode
            if (config.getPrePostOperatingRoomStandbyMode().isEnabled()) {
                signal("prePostOperatingRoomStandby",
                        config.getPrePostOperatingRoomStandbyMode().getMinutesToEnable(),
                        config.getPrePostOperatingRoomStandbyMode().getEnvironment().getTemperature(),
                        config.getPrePostOperatingRoomStandbyMode().getEnvironment().getHumidity(),
                        config.getPrePostOperatingRoomStandbyMode().getEnvironment().getAmbientIlluminance());
            }

            // Medical technologies scenarios
            config.getMedicalTechnologyScenarios().forEach(scenario -> {
                signal("medicalTechnologyScenario",
                        scenario.getMedicalTechnologyType(),
                        scenario.getAmbientIlluminance(),
                        scenario.getSurgicalIlluminance());
            });
        } catch (IOException e) {
            throw new IllegalStateException("Impossible to read configuration file", e);
        }
    }
}
