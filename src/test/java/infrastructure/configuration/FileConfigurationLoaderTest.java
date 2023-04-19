/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.configuration;

import application.controller.manager.ConfigurationLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Test of the {@link FileConfigurationLoader}.
 */
class FileConfigurationLoaderTest {
    @Test
    @DisplayName("File configuration loader should throw the right exception when provide a file that does not exists")
    void testFileNotFound() {
        final ConfigurationLoader configurationLoader = new FileConfigurationLoader("wrong-file.txt");
        assertThrows(IllegalStateException.class, configurationLoader::loadConfiguration);
    }

    @Test
    @DisplayName("File configuration must be correctly loaded and returned as a Configuration object instance")
    void testConfigurationLoading() {
        final URL fileResource = getClass().getClassLoader().getResource("configuration-example.yml");
        if (fileResource != null) {
            final String filePath = new File(fileResource.getFile()).getAbsolutePath();
            final ConfigurationLoader configurationLoader = new FileConfigurationLoader(filePath);
            assertDoesNotThrow(configurationLoader::loadConfiguration);
        } else {
            fail("Cannot load the test configuration file");
        }
    }
}
