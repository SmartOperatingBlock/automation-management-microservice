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

tollerance(0.5).

!observeOperatingBlock.

+!observeOperatingBlock
    <- ?obtainObserver(OperatingBlockObserverId);
       focus(OperatingBlockObserverId).

// Get the temperature updates
@temperature [atomic]
+temperature(RoomId, RoomType, Value)
    <- !adjustTemperature(RoomId, RoomType, Value).

// Check if there is a specific value of temperature for that room id and then achieve the optimal temperature
+!adjustTemperature(RoomId, RoomType, CurrentValue): specificTemperatureTarget(RoomId, OptimalValue)
    <- !achieveOptimalTemperature(RoomId, CurrentValue, OptimalValue).

// If there isn't any specific value for the room id then achieve the optimal temperature based on the room type
+!adjustTemperature(RoomId, RoomType, CurrentValue): not specificTemperatureTarget(RoomId, X) & optimalTemperature(RoomType, OptimalValue)
    <- !achieveOptimalTemperature(RoomId, CurrentValue, OptimalValue).

// Goals to achieve optimal temperature
+!achieveOptimalTemperature(RoomId, CurrentValue, OptimalValue)
            : tollerance(Tollerance) &
              TollerancedOptimalValue = OptimalValue + Tollerance &
              CurrentValue > TollerancedOptimalValue
    <- .println(cooling);
       !turnOffHeating(RoomId);
       !turnOnCooling(RoomId).

+!achieveOptimalTemperature(RoomId, CurrentValue, OptimalValue)
            : tollerance(Tollerance) &
              TollerancedOptimalValue = OptimalValue - Tollerance & 
              CurrentValue < TollerancedOptimalValue
    <- .println(heating);
       !turnOffCooling(RoomId);
       !turnOnHeating(RoomId).

+!achieveOptimalTemperature(RoomId, CurrentValue, OptimalValue) : CurrentValue == OptimalValue
    <- .println(off_temperature);
       !turnOffCooling(RoomId);
       !turnOffHeating(RoomId).

// Cooling goals
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

// Heating goals
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