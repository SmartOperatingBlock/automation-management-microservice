/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.actuator.invoker;

/**
 * Interface that models an actuator invoker that ca be turned on with an intensity.
 */
public interface DimmableInvoker {
    /**
     * Set the intensity of the actuator.
     * @param intensity the intensity to set in the actuator.
     */
    void setIntensity(int intensity);
}
