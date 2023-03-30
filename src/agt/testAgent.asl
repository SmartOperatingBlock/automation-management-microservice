/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

!observe.

+!observe
    <- ?obtainObserver(OperatingBlockObserverId);
       start [aid(OperatingBlockObserverId)];
       focus(OperatingBlockObserverId).


// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

-?obtainObserver(OperatingBlockObserverId)
    <- makeArtifact("operating_block_observer", "artifact.environment.OperatingBlockObserverArtifact", [], OperatingBlockObserverId).
       

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
