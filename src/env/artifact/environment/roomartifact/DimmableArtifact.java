/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment.roomartifact;

/**
 * Interface that models a dimmable artifact, that is an artifacts that offers method to set the intensity of it.
 */
public interface DimmableArtifact {
    /**
     * Set the intensity of the artifact.
     * @param intensity the intensity.
     */
    void setIntensity(int intensity);
}
