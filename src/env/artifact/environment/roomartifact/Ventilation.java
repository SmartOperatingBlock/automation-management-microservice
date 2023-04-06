/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment.roomartifact;

import cartago.OPERATION;

import java.util.logging.Logger;

/**
 * CArtAgO artifact that is responsible for the communication with the hvac system of the operating block respect
 * to the ventilation part.
 */
public class Ventilation extends AbstractRoomArtifact implements DimmableArtifact {
    @Override
    @OPERATION
    public final void setIntensity(final int intensityPercentage) {
        if (intensityPercentage < 0 || intensityPercentage > 100) {
            throw new IllegalArgumentException("Intensity must be an integer percentage");
        }

        Logger.getLogger(Ventilation.class.toString())
              .info("[" + this.getRoomId() + "]" + " Set " + intensityPercentage);
    }
}
