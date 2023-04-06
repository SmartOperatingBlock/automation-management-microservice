/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * This agent is responsible to monitor the luminosity inside the Operating Block and over the
 * surgical table in order to maintain it within the values of the user configuration.
 */

!observeOperatingBlock.

+!observeOperatingBlock
    <- ?obtainObserver(OperatingBlockObserverId);
       focus(OperatingBlockObserverId).

// Operating Room
+luminosity(RoomId, RoomType, Value) 
            : optimalIlluminance(RoomType, OptimalAmbientLight, OptimalSurgicalLight) &
              Value \== OptimalAmbientLight
    <- .println(adjust_luminosity_operating_room);
       !setAmbientLight(RoomId, OptimalAmbientLight);
       !setSurgicalLight(RoomId, OptimalSurgicalLight).

// Pre/Post Operating Room
+luminosity(RoomId, RoomType, Value) 
            : optimalIlluminance(RoomType, OptimalAmbientLight) &
              Value \== OptimalAmbientLight
    <- .println(adjust_luminosity_pre_post_operating_room);
       !setAmbientLight(RoomId, OptimalAmbientLight).

// Ambient light set goal
+!setAmbientLight(RoomId, Value)
    <- .concat(RoomId, "-ambientlight", ResultString);
       makeArtifact(ResultString, "artifact.environment.roomartifact.AmbientLight", [RoomId], AmbientLightId);
       setIntensity(Value) [aid(AmbientLightId)];
       disposeArtifact(AmbientLightId).

// Surgical light set goal
+!setSurgicalLight(RoomId, Value)
    <- .concat(RoomId, "-surgicallight", ResultString);
       makeArtifact(ResultString, "artifact.environment.roomartifact.SurgicalLight", [RoomId], SurgicalLightId);
       setIntensity(Value) [aid(SurgicalLightId)];
       disposeArtifact(SurgicalLightId).

// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

-?obtainObserver(OperatingBlockObserverId)
    <- .wait(100);
       ?obtainObserver(OperatingBlockObserverId).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }