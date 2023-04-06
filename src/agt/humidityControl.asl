/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * This agent is responsible to monitor the humidity inside the Operating Block
 * in order to maintain it within the values of the user configuration.
 */

!observeOperatingBlock.

+!observeOperatingBlock
    <- ?obtainObserver(OperatingBlockObserverId);
       focus(OperatingBlockObserverId).

@ventilation [atomic]
+humidity(RoomId, RoomType, Value)
        : optimalHumidity(RoomType, OptimalValue) &
          TollerancedOptimalValue = OptimalValue + 1 &
          Value >= TollerancedOptimalValue
    <- .println(ventilation);
       !turnOnVentilation(RoomId).

@off_ventilation [atomic]
+humidity(RoomId, RoomType, Value) : optimalHumidity(RoomType, OptimalValue) & Value <= TollerancedOptimalValue
    <- .println(off_ventilation);
       !turnOffVentilation(RoomId).

// Ventilation goal
+!turnOnVentilation(RoomId) : not ventilation(RoomId)
    <- .concat(RoomId, "-ventilation", ResultString);
       makeArtifact(ResultString, "artifact.environment.roomartifact.Ventilation", [RoomId], VentilationId);
       setIntensity(100) [aid(VentilationId)];
       +ventilation(RoomId); // add mental note to not re-turn on
       disposeArtifact(VentilationId).
-!turnOnVentilation(RoomId) 
    <- true.

+!turnOffVentilation(RoomId) : ventilation(RoomId)
    <- .concat(RoomId, "-ventilation", ResultString);
       makeArtifact(ResultString, "artifact.environment.roomartifact.Ventilation", [RoomId], VentilationId);
       setIntensity(0) [aid(VentilationId)];
       -ventilation(RoomId); // add mental note to not re-turn off
       disposeArtifact(VentilationId).
-!turnOffVentilation(RoomId) 
    <- true.


// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

-?obtainObserver(OperatingBlockObserverId)
    <- .wait(100);
       ?obtainObserver(OperatingBlockObserverId).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }