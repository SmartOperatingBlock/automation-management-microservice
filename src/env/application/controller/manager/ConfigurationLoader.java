/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.controller.manager;

import application.presenter.configuration.model.Configuration;

/**
 * Interface that models a loader of {@link application.presenter.configuration.model.Configuration}.
 */
public interface ConfigurationLoader {
    /**
     * Method that load and return the configuration.
     * @return the configuration.
     */
    Configuration loadConfiguration();
}
