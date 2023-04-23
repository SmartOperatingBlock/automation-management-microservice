/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * The agent is responsible to handle the automation scenarios that support the usage
 * of medical technologies and custom light settings inside the operating rooms.
 */

!observeOperatingBlock.
!createOperatingBlockSender.

+!observeOperatingBlock
    <- ?obtainObserver(OperatingBlockObserverId);
       focus(OperatingBlockObserverId).
    
+!createOperatingBlockSender
    <- makeArtifact("operating_room_proposer", "artifact.environment.OperatingRoomProposerArtifact", [], OperatingRoomSenderId).

// Medical technology usage events
+medicalTechnologyUsage(Type, true, RoomId) // Usage of a medical technology
            : scenario(Type, AmbientLight, SurgicalLight) &
              not medicalTechnologyInUse(Type, R) // Check that the medical technology is not know to be already in use
    <- +medicalTechnologyInUse(Type, RoomId);
       lookupArtifact("operating_room_proposer", OperatingRoomSenderId);
       sendMedicalTechnologyAutomationProposal(RoomId, Type, AmbientLight, SurgicalLight) [aid(OperatingRoomSenderId)].

+medicalTechnologyUsage(Type, false, RoomId) // Stop usage of a medical technology that isn't currently supported
            : medicalTechnologyInUse(Type, RoomId) & // Check that the medical technology was in use
              not medicalTechnologySupported(Type, RoomId) // This plan cover the case in which the doctor chose to not use the support
    <- -medicalTechnologyInUse(Type, RoomId).

+medicalTechnologyUsage(Type, false, RoomId) // Stop usage of a medical technology that is currently supported
            : medicalTechnologySupported(Type, RoomId) // This plan cover the case in which the doctor use the support
    <- -medicalTechnologyInUse(Type, RoomId);
       -medicalTechnologySupported(Type, RoomId);
       out(lightSupportStop, RoomId).

// Medical technology automation scenario request from operating room.
+medicalTechnologyRequestScenario(RoomId, MedicalTechnologyType)
            : medicalTechnologyInUse(MedicalTechnologyType, RoomId) &
              scenario(MedicalTechnologyType, AmbientLight, SurgicalLight) &
              not customLight(RoomId) // check that there isn't any other automation scenario in that room
    <- +medicalTechnologySupported(MedicalTechnologyType, RoomId);
       out(lightSupportStart, RoomId, AmbientLight, SurgicalLight).

// Custom light setup request from operating room.
+customLightSetupRequest(RoomId, AmbientLight, SurgicalLight)
            : not medicalTechnologySupported(X, RoomId) // check that there isn't any other automation in that room.
    <- +customLight(RoomId);
       out(lightSupportStart, RoomId, AmbientLight, SurgicalLight).

+customLightStopRequest(RoomId)
    <- -customLight(RoomId);
       out(lightSupportStop, RoomId).

// Obtain the operating block observer
+?obtainObserver(OperatingBlockObserverId)
    <- lookupArtifact("operating_block_observer", OperatingBlockObserverId).

-?obtainObserver(OperatingBlockObserverId)
    <- .wait(100);
       ?obtainObserver(OperatingBlockObserverId).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }