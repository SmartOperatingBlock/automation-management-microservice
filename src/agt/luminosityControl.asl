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

// Get the luminosity updates
@luminosity [atomic]
+luminosity(RoomId, RoomType, Value)
    <- ! adjustLuminosity(RoomId, RoomType, Value).

// Check if there is a specific value of luminosity for that room id and then achieve the optimal luminosity
// Pre/Post Operating Room
+!adjustLuminosity(RoomId, RoomType, CurrentValue): specificIlluminanceTarget(RoomId, OptimalAmbientLight)
    <- !achieveOptimalIlluminance(RoomId, CurrentValue, OptimalAmbientLight).
// Operating Room
+!adjustLuminosity(RoomId, RoomType, CurrentValue): specificIlluminanceTarget(RoomId, OptimalAmbientLight, OptimalSurgicalLight)
    <- !achieveOptimalIlluminance(RoomId, CurrentValue, OptimalAmbientLight, OptimalSurgicalLight).

// If there isn't any specific value for the room id then achieve the optimal luminosity based on the room type
// Pre/Post Operating Room
+!adjustLuminosity(RoomId, RoomType, CurrentValue)
            : not specificIlluminanceTarget(RoomId, X) & 
              optimalIlluminance(RoomType, OptimalAmbientLight)
    <- !achieveOptimalIlluminance(RoomId, CurrentValue, OptimalAmbientLight).
// Operating Room
+!adjustLuminosity(RoomId, RoomType, CurrentValue)
            : not specificIlluminanceTarget(RoomId, X, Y) &
              optimalIlluminance(RoomType, OptimalAmbientLight, OptimalSurgicalLight)
    <- !achieveOptimalIlluminance(RoomId, CurrentValue, OptimalAmbientLight, OptimalSurgicalLight).

// Goals to achieve optimal illuminance
// Pre/Post Operating Room
+!achieveOptimalIlluminance(RoomId, CurrentValue, OptimalAmbientLight) : CurrentValue \== OptimalAmbientLight
    <- .println("adjust luminosity pre/post operating room");
       !setAmbientLight(RoomId, OptimalAmbientLight).
+!achieveOptimalIlluminance(RoomId, CurrentValue, OptimalAmbientLight) : CurrentValue == OptimalAmbientLight
    <- true.

// Operating Room
+!achieveOptimalIlluminance(RoomId, CurrentValue, OptimalAmbientLight, OptimalSurgicalLight) : CurrentValue \== OptimalAmbientLight
    <- .println("adjust luminosity operating room");
       !setAmbientLight(RoomId, OptimalAmbientLight);
       !setSurgicalLight(RoomId, OptimalSurgicalLight).
+!achieveOptimalIlluminance(RoomId, CurrentValue, OptimalAmbientLight, OptimalSurgicalLight) : CurrentValue == OptimalAmbientLight
    <- true.

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