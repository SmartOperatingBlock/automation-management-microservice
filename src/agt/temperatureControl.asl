/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * This agent is responsible to monitor the temperature inside the Operating Block
 * in order to maintain it within the values of the user configuration.
 */

!observeOperatingBlock.

+!observeOperatingBlock
    <- ?obtainObserver(OperatingBlockObserverId);
       focus(OperatingBlockObserverId).

@cooling [atomic]
+temperature(RoomId, RoomType, Value)
            : optimalTemperature(RoomType, OptimalValue) &
              TollerancedOptimalValue = OptimalValue + 0.5 &
              Value > TollerancedOptimalValue
    <- .println(cooling);
       !turnOffHeating(RoomId);
       !turnOnCooling(RoomId).

@heating [atomic]
+temperature(RoomId, RoomType, Value) 
            : optimalTemperature(RoomType, OptimalValue) &
              TollerancedOptimalValue = OptimalValue - 0.5 & 
              Value < TollerancedOptimalValue
    <- .println(heating);
       !turnOffCooling(RoomId);
       !turnOnHeating(RoomId).

@off_temperature [atomic]
+temperature(RoomId, RoomType, Value) : optimalTemperature(RoomType, OptimalValue) & Value == OptimalValue
    <- .println(off_temperature);
       !turnOffCooling(RoomId);
       !turnOffHeating(RoomId).

// Cooling goal
+!turnOnCooling(RoomId) : not cooling(RoomId)
    <- .concat(RoomId, "-cooler", ResultString);
       makeArtifact(ResultString, "artifact.environment.roomartifact.Cooler", [RoomId], CoolerId);
       turnOn [aid(CoolerId)];
       +cooling(RoomId); // add mental note to not re-turn on
       disposeArtifact(CoolerId).
-!turnOnCooling(RoomId) 
    <- true.

+!turnOffCooling(RoomId) : cooling(RoomId)
    <- .concat(RoomId, "-cooler", ResultString);
       makeArtifact(ResultString, "artifact.environment.roomartifact.Cooler", [RoomId], CoolerId);
       turnOff [aid(CoolerId)];
       -cooling(RoomId); // remove mental note to not re-turn off
       disposeArtifact(CoolerId).
-!turnOffCooling(RoomId) 
    <- true.

// Heating goal
+!turnOnHeating(RoomId) : not heating(RoomId)
    <- .concat(RoomId, "-heater", ResultString);
       makeArtifact(ResultString, "artifact.environment.roomartifact.Heater", [RoomId], HeaterId);
       turnOn [aid(HeaterId)];
       +heating(RoomId); // add mental note to not re-turn on
       disposeArtifact(HeaterId).
-!turnOnHeating(RoomId) 
    <- true.

+!turnOffHeating(RoomId) : heating(RoomId)
    <- .concat(RoomId, "-heater", ResultString);
       makeArtifact(ResultString, "artifact.environment.roomartifact.Heater", [RoomId], HeaterId);
       turnOff [aid(HeaterId)];
       -heating(RoomId); // remove mental note to not re-turn off
       disposeArtifact(HeaterId).
-!turnOffHeating(RoomId) 
    <- true.

// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

-?obtainObserver(OperatingBlockObserverId)
    <- .wait(100);
       ?obtainObserver(OperatingBlockObserverId).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }