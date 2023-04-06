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
 * CArtAgO artifact that is responsible for the communication with an ambient light inside a room of the
 * Operating Block.
 */
public class AmbientLight extends AbstractRoomArtifact implements DimmableArtifact {
    @Override
    @OPERATION
    public final void setIntensity(final int luxToSet) {
        Logger.getLogger(AmbientLight.class.toString())
              .info("[" + this.getRoomId() + "]" + " Set " + luxToSet + " LUX");
    }
}
