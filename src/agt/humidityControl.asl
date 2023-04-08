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

tollerance(1).

!observeOperatingBlock.

+!observeOperatingBlock
    <- ?obtainObserver(OperatingBlockObserverId);
       focus(OperatingBlockObserverId).

// Get the humidity updates
@humidity [atomic]
+humidity(RoomId, RoomType, Value)
    <- !adjustHumidity(RoomId, RoomType, Value).

// Check if there is a specific value of humidity for that room id and then achieve the optimal humidity
+!adjustHumidity(RoomId, RoomType, CurrentValue): specificHumidityTarget(RoomId, OptimalValue)
    <- !achieveOptimalHumidity(RoomId, CurrentValue, OptimalValue).

// If there isn't any specific value for the room id then achieve the optimal humidity based on the room type
+!adjustHumidity(RoomId, RoomType, CurrentValue): not specificHumidityTarget(RoomId, X) & optimalHumidity(RoomType, OptimalValue)
    <- !achieveOptimalHumidity(RoomId, CurrentValue, OptimalValue).

// Goals to achieve optimal humidity
+!achieveOptimalHumidity(RoomId, CurrentValue, OptimalValue)
        : tollerance(Tollerance) &
          TollerancedOptimalValue = OptimalValue + Tollerance &
          CurrentValue >= TollerancedOptimalValue
    <- .println("ventilation");
       !turnOnVentilation(RoomId).

+!achieveOptimalHumidity(RoomId, CurrentValue, OptimalValue) : CurrentValue <= OptimalValue
    <- .println("off ventilation");
       !turnOffVentilation(RoomId).

// Ventilation goals
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