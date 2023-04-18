/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.wot;

import entity.actuator.ActuatorID;
import entity.actuator.invoker.DimmableInvoker;
import entity.actuator.invoker.SwitchableInvoker;

import java.util.Map;

/**
 * Web of Thing Invoker in order to be able to communicate with things and invoke actions.
 */
public class WotInvoker implements SwitchableInvoker, DimmableInvoker {
    private static final String SET_INTENSITY_ACTION = "setIntensity";
    private static final String SET_INTENSITY_ACTION_INPUT_NAME = "intensity";
    private static final String SET_TURN_ON_ACTION = "on";
    private static final String SET_TURN_OFF_ACTION = "off";
    @Override
    public final void setIntensity(final ActuatorID actuatorID, final int intensity) {
        final WotClient wotClient = new WotClient(actuatorID.getId());
        wotClient.invoke(SET_INTENSITY_ACTION, Map.of(SET_INTENSITY_ACTION_INPUT_NAME, intensity));
    }

    @Override
    public final void turnOn(final ActuatorID actuatorID) {
        final WotClient wotClient = new WotClient(actuatorID.getId());
        wotClient.invoke(SET_TURN_ON_ACTION, Map.of());
    }

    @Override
    public final void turnOff(final ActuatorID actuatorID) {
        final WotClient wotClient = new WotClient(actuatorID.getId());
        wotClient.invoke(SET_TURN_OFF_ACTION, Map.of());
    }
}
