/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

// CArtAgO artifact code for project automation_management_microservice

package example;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.ObsProperty;

/**
 * Example class for Cartago env.
 */
public class Counter extends Artifact {
    /**
     * Init counter.
     * @param initialValue
     */
    void init(final int initialValue) {
        defineObsProperty("count", initialValue);
    }

    /**
     * Inc operation.
     */
    @OPERATION
    void inc() {
        final ObsProperty prop = getObsProperty("count");
        prop.updateValue(prop.intValue() + 1);
        signal("tick");
    }
}

