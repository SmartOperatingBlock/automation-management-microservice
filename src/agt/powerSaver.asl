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

@no_presence
+presence(RoomId, RoomType, PresenceDetected) 
            : PresenceDetected == false &
              nticks(CurrentMillisecond) &
              minutesToStandby(RoomType, MinutesToStandby)
    <- +no_presence(RoomId, RoomType, CurrentMillisecond, MinutesToStandby * 60 * 1000).

@presence [atomic]
+presence(RoomId, RoomType, PresenceDetected) : PresenceDetected == true
    <- -no_presence(RoomId, RoomType, X, Y).

+tick: nticks(CurrentMillisecond) 
    <- !checkNeedStandbyMode(CurrentMillisecond).

+!checkNeedStandbyMode(CurrentMillisecond)
            : no_presence(RoomId, RoomType, Instant, Limit) &
              CurrentMillisecond - Instant > Limit
              //todo: obtain configuration considering the type of the room
    <- //todo: write on tuple space the configuration to reach
       -no_presence(RoomId, RoomType, Instant, Limit);
       !!checkNeedStandbyMode(CurrentMillisecond). // Continue to check until there are match

-!checkNeedStandbyMode(CurrentMillisecond). // No more match found

// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

-?obtainObserver(OperatingBlockObserverId)
    <- .wait(100);
       ?obtainObserver(OperatingBlockObserverId).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }