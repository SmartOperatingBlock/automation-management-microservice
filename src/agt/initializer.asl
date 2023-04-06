/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * Agent that is responsible to create the main artifacts used by the agents.
 */

!createOperatingBlockObserver.

+!createOperatingBlockObserver
    <- makeArtifact("operating_block_observer", "artifact.environment.OperatingBlockObserverArtifact", [], OperatingBlockObserverId);
       start [aid(OperatingBlockObserverId)].