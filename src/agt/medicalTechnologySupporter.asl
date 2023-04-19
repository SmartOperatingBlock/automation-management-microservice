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
       focus(OperatingBlockObserverId).

+medicalTechnologyUsage(Type, true, RoomId) // Usage of a medical technology
    <- true.

+medicalTechnologyUsage(Type, false, RoomId) // Stop usage of a medical technology
    <- true.

// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

-?obtainObserver(OperatingBlockObserverId)
    <- .wait(100);
       ?obtainObserver(OperatingBlockObserverId).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }