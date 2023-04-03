/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment;

import application.controller.manager.EventManager;
import application.presenter.event.model.medicaltechnology.payload.MedicalTechnologyUsagePayload;
import application.presenter.event.model.roomevent.RoomEvent;
import application.presenter.event.model.roomevent.payload.HumidityPayload;
import application.presenter.event.model.roomevent.payload.LuminosityPayload;
import application.presenter.event.model.roomevent.payload.PresencePayload;
import application.presenter.event.model.roomevent.payload.TemperaturePayload;
import cartago.Artifact;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import infrastructure.events.KafkaClient;

/**
 * CArtAgO artifact that is responsible to observe the operating block and expose its data.
 */
public class OperatingBlockObserverArtifact extends Artifact {
    private boolean working;
    private EventManager eventManager;
    /**
     * Initialize the operating block observer artifact.
     */
    void init() {
        this.working = false;
        this.eventManager = KafkaClient.getInstance();
    }

    /**
     * Start the Operating Block Observer.
     */
    @OPERATION
    void start() {
        if (!working) {
            working = true;
            execInternalOp("poll");
        }
    }

    /**
     * Internal artifact's operation used to polling for new events with the event manager.
     */
    @INTERNAL_OPERATION
    void poll() {
        this.eventManager.poll(event -> {
            switch (event.getKey()) {
                case TemperaturePayload.TEMPERATURE_EVENT_KEY:
                    final RoomEvent<?> roomTempEvent = (RoomEvent<?>) event;
                    final TemperaturePayload temperaturePayload = (TemperaturePayload) roomTempEvent.getData();
                    signal("temperature",
                            roomTempEvent.getRoomId(),
                            roomTempEvent.getRoomType().toString(),
                            temperaturePayload.getTemperatureValue());
                    break;
                case HumidityPayload.HUMIDITY_EVENT_KEY:
                    final RoomEvent<?> roomHumidityEvent = (RoomEvent<?>) event;
                    final HumidityPayload humidityPayload = (HumidityPayload) roomHumidityEvent.getData();
                    signal("humidity",
                            roomHumidityEvent.getRoomId(),
                            roomHumidityEvent.getRoomType().toString(),
                            humidityPayload.getHumidityPercentage());
                    break;
                case LuminosityPayload.LUMINOSITY_EVENT_KEY:
                    final RoomEvent<?> roomLuminosityEvent = (RoomEvent<?>) event;
                    final LuminosityPayload luminosityPayload = (LuminosityPayload) roomLuminosityEvent.getData();
                    signal("luminosity",
                            roomLuminosityEvent.getRoomId(),
                            roomLuminosityEvent.getRoomType().toString(),
                            luminosityPayload.getLuminosityValue());
                    break;
                case PresencePayload.PRESENCE_EVENT_KEY:
                    final RoomEvent<?> roomPresenceEvent = (RoomEvent<?>) event;
                    final PresencePayload presencePayload = (PresencePayload) roomPresenceEvent.getData();
                    signal("presence",
                            roomPresenceEvent.getRoomId(),
                            roomPresenceEvent.getRoomType().toString(),
                            presencePayload.isPresenceDetected());
                    break;
                case MedicalTechnologyUsagePayload.MEDICAL_TECHNOLOGY_USAGE_EVENT_KEY:
                    // final MedicalTechnologyEvent medicalTechnologyUsageEvent = (MedicalTechnologyEvent) event;
                    // todo: understand in which room the medical technology is
                    // todo: understand the type of the medical technology (maybe can be fused with the previous query)
                    // todo: signal
                    break;
                default:
                    // not handled
                    break;
            }
            signal("event", event.getKey()); // tobe deleted
        });
    }
}
