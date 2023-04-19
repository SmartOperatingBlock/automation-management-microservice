/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.configuration;

import application.controller.manager.ConfigurationLoader;
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

/**
 * A {@link ConfigurationLoader} that loads the configuration via file.
 */
public class FileConfigurationLoader implements ConfigurationLoader {
    private final String configurationPath;

    /**
     * Default constructor.
     * @param configurationPath the path to the configuration file.
     */
    public FileConfigurationLoader(final String configurationPath) {
        this.configurationPath = configurationPath;
    }

    @Override
    public final Configuration loadConfiguration() {
        final Representer represent = new Representer(new DumperOptions());
        represent.getPropertyUtils().setSkipMissingProperties(true);
        final Yaml yaml = new Yaml(new Constructor(Configuration.class, new LoaderOptions()), represent);
        try (
                BufferedInputStream configInputStream = new BufferedInputStream(
                        new FileInputStream(this.configurationPath)
                )
        ) {
            final String fileContent = new String(configInputStream.readAllBytes(), StandardCharsets.UTF_8);
            return yaml.load(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("Impossible to read configuration file", e);
        }
    }
}
