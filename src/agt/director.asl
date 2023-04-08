/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * This agent loads the configuration using the ConfigurationArtifact
 * and inform the right agents inside the workspace of the work to do.
 */

!loadConfig.
!checkPrePostOperatingRoomStandbyProposals.
!checkOperatingRoomStandbyProposals.
!checkStandbyEnd.

+!loadConfig
    <- makeArtifact(configuration, "artifact.config.ConfigurationArtifact", [], ConfigurationId);
       focus(ConfigurationId);
       load.

+operatingRoom(T, H, AL, SL)
    <- .send(temperatureControl, tell, optimalTemperature("operatingRoom", T));
       .send(humidityControl, tell, optimalHumidity("operatingRoom", H));
       .send(luminosityControl, tell, optimalIlluminance("operatingRoom", AL, SL)).

+preOperatingRoom(T, H, AL)
    <- .send(temperatureControl, tell, optimalTemperature("preOperatingRoom", T));
       .send(humidityControl, tell, optimalHumidity("preOperatingRoom", H));
       .send(luminosityControl, tell, optimalIlluminance("preOperatingRoom", AL)).

+operatingRoomStandby(M, T, H, AL, SL)
    <- .send(powerSaver, tell, minutesToStandby("operatingRoom", M));
       .send(powerSaver, tell, standbyEnvironmentConfig("operatingRoom", T, H, AL, SL)).

+prePostOperatingRoomStandby(M, T, H, AL)
    <- .send(powerSaver, tell, minutesToStandby("preOperatingRoom", M));
       .send(powerSaver, tell, standbyEnvironmentConfig("preOperatingRoom", T, H, AL)).

+medicalTechnologyScenario(MT, AL, SL)
    <- .send(medicalTechnologySupporter, tell, scenario(MT, AL, SL)).


// Check standby proposals for pre/post operating room
+!checkPrePostOperatingRoomStandbyProposals
    <- in(requestStandby, RoomId, Temperature, Humidity, AmbientLight);
       .println(requestedPre);
       .println(RoomId);
       !!checkPrePostOperatingRoomStandbyProposals.

// Check standby proposals for operating room
+!checkOperatingRoomStandbyProposals
    <- in(requestStandby, RoomId, Temperature, Humidity, AmbientLight, SurgicalLight);
       .println(requestedOp);
       .println(RoomId);
       !!checkOperatingRoomStandbyProposals.

+!checkStandbyEnd
    <- in(stopStandby, RoomId);
       .println(stopStandby);
       .println(RoomId);
       !!checkStandbyEnd.

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }