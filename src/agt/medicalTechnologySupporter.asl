/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * The agent is responsible to handle the automation scenarios that support the usage
 * of medical technologies inside the operating rooms.
 */

!observeOperatingBlock.

+!observeOperatingBlock
    <- ?obtainObserver(OperatingBlockObserverId);
       start [aid(OperatingBlockObserverId)];
       focus(OperatingBlockObserverId).

+medicalTechnologyUsage(Type, Value, RoomId)
    <- .println(Type);
       .println(Value);
       .println(RoomId).

// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

// If the operating block observer artifact is not found in the workspace, then create it
-?obtainObserver(OperatingBlockObserverId)
    <- .wait(100);
       ?obtainObserver(OperatingBlockObserverId).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }