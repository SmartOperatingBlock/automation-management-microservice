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
!checkLightSupportProposals.
!checkLightSupportEnd.

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
    <- .send(lightAutomationSupporter, tell, scenario(MT, AL, SL)).


// Check standby proposals for pre/post operating room
+!checkPrePostOperatingRoomStandbyProposals
    <- in(requestStandby, RoomId, Temperature, Humidity, AmbientLight);
       .concat("requested standby for pre/post operating room:", RoomId, LogString);
       .println(LogString);
       .send(temperatureControl, tell, specificTemperatureTarget(RoomId, Temperature));
       .send(humidityControl, tell, specificHumidityTarget(RoomId, Humidity));
       .send(luminosityControl, tell, specificIlluminanceTarget(RoomId, AmbientLight));
       !!checkPrePostOperatingRoomStandbyProposals.

// Check standby proposals for operating room
+!checkOperatingRoomStandbyProposals
    <- in(requestStandby, RoomId, Temperature, Humidity, AmbientLight, SurgicalLight);
       .concat("requested standby for operating room: ", RoomId, LogString);
       .println(LogString);
       .send(temperatureControl, tell, specificTemperatureTarget(RoomId, Temperature));
       .send(humidityControl, tell, specificHumidityTarget(RoomId, Humidity));
       .send(luminosityControl, tell, specificIlluminanceTarget(RoomId, AmbientLight, SurgicalLight));
       !!checkOperatingRoomStandbyProposals.

+!checkStandbyEnd
    <- in(stopStandby, RoomId);
       .concat("stop standby for room: ", RoomId, LogString);
       .println(LogString);
       .send(temperatureControl, untell, specificTemperatureTarget(RoomId, _));
       .send(humidityControl, untell, specificHumidityTarget(RoomId, _));
       .send(luminosityControl, untell, specificIlluminanceTarget(RoomId, _, _));
       .send(luminosityControl, untell, specificIlluminanceTarget(RoomId, _));
       !!checkStandbyEnd.

+!checkLightSupportProposals
    <- in(lightSupportStart, RoomId, AmbientLight, SurgicalLight);
       .concat("request light scenario in ", RoomId, LogString);
       println(LogString);
       .send(luminosityControl, tell, specificIlluminanceTarget(RoomId, AmbientLight, SurgicalLight));
       !!checkLightSupportProposals.

+!checkLightSupportEnd
    <- in(lightSupportStop, RoomId);
       .concat("stop light scenario in ", RoomId, LogString);
       println(LogString);
       .send(luminosityControl, untell, specificIlluminanceTarget(RoomId, _, _));
       !!checkLightSupportEnd.
       

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }