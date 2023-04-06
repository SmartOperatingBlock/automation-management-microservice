/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment.roomartifact;

import cartago.Artifact;

/**
 * Abstract class that represent the base class for a CArtAgO boundary artifacts placed inside a Room within
 * the Operating Block.
 */
public abstract class AbstractRoomArtifact extends Artifact {
    private String roomId;

    /**
     * Initialize the artifact.
     * This method is called by Jason agents.
     * @param roomId the id of the room where the artifact is placed.
     */
    protected void init(final String roomId) {
        this.roomId = roomId;
    }

    /**
     * Obtain the roomId where the artifact is placed.
     * @return the roomId.
     */
    public String getRoomId() {
        return this.roomId;
    }
}
