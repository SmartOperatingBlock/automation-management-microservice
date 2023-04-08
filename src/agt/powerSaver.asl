/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * This agent is responsible to handle the process of energy-saving withing the Operating Block.
 * It handles the standby mode considering presences in the rooms settings environment values
 * according with the values of the user configuration for the standby mode.
 */

!observeOperatingBlock.
!createClock.

+!observeOperatingBlock
    <- ?obtainObserver(OperatingBlockObserverId);
       focus(OperatingBlockObserverId).

+!createClock
    <- makeArtifact("power_saver_clock", "cartago.tools.Clock", [], ClockId);
       focus(ClockId);
       setFrequency(1) [aid(ClockId)]; // 1 tick per second
       start [aid(ClockId)].

// No presence event
@no_presence
+presence(RoomId, RoomType, PresenceDetected) 
            : PresenceDetected == false &
              nticks(CurrentMillisecond) &
              minutesToStandby(RoomType, MinutesToStandby) &
              not no_presence(RoomId, RoomType, X, Y) // Check that it isn't already known
    <- +no_presence(RoomId, RoomType, CurrentMillisecond, MinutesToStandby * 60 * 1000).

// If presence is detected again in a room that is in standby send request to disable it.
@presence_in_standby [atomic]
+presence(RoomId, RoomType, PresenceDetected) 
            : PresenceDetected == true &
              requestedStandby(RoomId)
    <- out(stopStandby, RoomId);
       -requestedStandby(RoomId).

// If presence is detected again in a room which is not in standby mode then only delete the no presence event.
@presence_not_in_standby [atomic]
+presence(RoomId, RoomType, PresenceDetected) : PresenceDetected == true
    <- -no_presence(RoomId, RoomType, X, Y).


// At each tick check the need to set room in the standby mode
+tick: nticks(CurrentMillisecond) 
    <- !checkNeedStandbyMode(CurrentMillisecond).

+!checkNeedStandbyMode(CurrentMillisecond)
            : no_presence(RoomId, RoomType, Instant, Limit) &
              CurrentMillisecond - Instant > Limit
    <- !setStandbyMode(RoomId, RoomType);
       +requestedStandby(RoomId); // mental note to save for which room I have requested the standby mode.
       -no_presence(RoomId, RoomType, Instant, Limit);
       !!checkNeedStandbyMode(CurrentMillisecond). // Continue to check until there are match

-!checkNeedStandbyMode(CurrentMillisecond). // No more match found

// Set Standby mode Operating Room
+!setStandbyMode(RoomId, RoomType)
            : standbyEnvironmentConfig(RoomType, Temperature, Humidity, AmbientLight, SurgicalLight)
    <- out(requestStandby, RoomId, Temperature, Humidity, AmbientLight, SurgicalLight). // propose specific configuration to director

// Set Standby mode Pre/Post Operating Room
+!setStandbyMode(RoomId, RoomType)
            : standbyEnvironmentConfig(RoomType, Temperature, Humidity, AmbientLight)
    <- out(requestStandby, RoomId, Temperature, Humidity, AmbientLight). // propose specific configuration to director

// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

-?obtainObserver(OperatingBlockObserverId)
    <- .wait(100);
       ?obtainObserver(OperatingBlockObserverId).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }