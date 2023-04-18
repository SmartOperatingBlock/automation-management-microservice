/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.actuator.invoker;

/**
 * Interface that models an actuator invoker that can be turned on and off.
 */
public interface SwitchableInvoker {
    /**
     * Turn the actuator on.
     */
    void turnOn();

    /**
     * Turn the actuator off.
     */
    void turnOff();
}
