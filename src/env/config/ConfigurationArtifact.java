/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package config;

import cartago.Artifact;
import cartago.OPERATION;
import infrastructure.configuration.model.Configuration;
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
        final Representer represent = new Representer();
        represent.getPropertyUtils().setSkipMissingProperties(true);
        final Yaml yaml = new Yaml(new Constructor(Configuration.class), represent);
        try (
                BufferedInputStream configInputStream = new BufferedInputStream(
                        new FileInputStream(System.getenv(CONFIGURATION_PATH_VARIABLE))
                )
        ) {
            final String fileContent = new String(configInputStream.readAllBytes(), StandardCharsets.UTF_8);
            final Configuration config = yaml.load(fileContent);
            signal("config", config.getOperatingRoomStandbyMode().isEnabled().toString()); // tobe deleted
        } catch (IOException e) {
            throw new IllegalStateException("Impossible to read configuration file", e);
        }
    }
}
