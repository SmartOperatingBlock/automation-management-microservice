/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment;

import application.controller.manager.EventManager;
import cartago.Artifact;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import infrastructure.events.KafkaClient;

/**
 * CArtAgO artifact that is responsible to observe the operating block and expose its data.
 */
public class OperatingBlockObserverArtifact extends Artifact {
    private boolean working;
    private EventManager eventManager;
    /**
     * Initialize the operating block observer artifact.
     */
    void init() {
        this.working = false;
        this.eventManager = KafkaClient.getInstance();
    }

    /**
     * Start the Operating Block Observer.
     */
    @OPERATION
    void start() {
        if (!working) {
            working = true;
            execInternalOp("poll");
        }
    }

    /**
     * Internal artifact's operation used to polling for new events with the event manager.
     */
    @INTERNAL_OPERATION
    void poll() {
        this.eventManager.poll(event -> {
            signal("event", event.getKey()); // tobe deleted
        });
    }
}
