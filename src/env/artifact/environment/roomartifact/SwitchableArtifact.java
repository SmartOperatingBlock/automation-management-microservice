/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment.roomartifact;

/**
 * Interface that models a switchable artifact, that is an artifacts that offers method to turn it on and off.
 */
public interface SwitchableArtifact {
    /**
     * Turn the artifact on.
     */
    void turnOn();

    /**
     * Turn the artifact off.
     */
    void turnOff();
}
