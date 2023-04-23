/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.serialization;

import application.presenter.event.model.Event;
import application.presenter.event.model.automation.request.customlight.CustomLightRequestEvent;
import application.presenter.event.model.automation.request.customlight.payload.CustomLightSetupRequestPayload;
import application.presenter.event.model.automation.request.customlight.payload.CustomLightStopRequestPayload;
import application.presenter.event.model.automation.request.medicaltechnology.MedicalTechnologyAutomationRequestEvent;
import application.presenter.event.model.medicaltechnology.MedicalTechnologyEvent;
import application.presenter.event.model.medicaltechnology.payload.MedicalTechnologyUsagePayload;
import application.presenter.event.model.roomevent.RoomEvent;
import application.presenter.event.model.roomevent.payload.HumidityPayload;
import application.presenter.event.model.roomevent.payload.LuminosityPayload;
import application.presenter.event.model.roomevent.payload.PresencePayload;
import application.presenter.event.model.roomevent.payload.TemperaturePayload;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@link EventDeserializer} interface that allows an event to be
 * deserialized from String.
 */
public class EventDeserializerImpl implements EventDeserializer {
    private final Map<String, Type> typeMap = new HashMap<>();

    /**
     * Default constructor.
     */
    public EventDeserializerImpl() {
        this.typeMap.put(TemperaturePayload.TEMPERATURE_EVENT_KEY, new TypeToken<RoomEvent<TemperaturePayload>>() { }.getType());
        this.typeMap.put(HumidityPayload.HUMIDITY_EVENT_KEY, new TypeToken<RoomEvent<HumidityPayload>>() { }.getType());
        this.typeMap.put(LuminosityPayload.LUMINOSITY_EVENT_KEY, new TypeToken<RoomEvent<LuminosityPayload>>() { }.getType());
        this.typeMap.put(PresencePayload.PRESENCE_EVENT_KEY, new TypeToken<RoomEvent<PresencePayload>>() { }.getType());
        this.typeMap.put(
                MedicalTechnologyUsagePayload.MEDICAL_TECHNOLOGY_USAGE_EVENT_KEY,
                new TypeToken<MedicalTechnologyEvent>() { }.getType()
        );
        this.typeMap.put(
                MedicalTechnologyAutomationRequestEvent.MEDICAL_TECHNOLOGY_AUTOMATION_REQUEST_EVENT_KEY,
                new TypeToken<MedicalTechnologyAutomationRequestEvent>() { }.getType()
        );
        this.typeMap.put(
                CustomLightSetupRequestPayload.CUSTOM_LIGHT_SETUP_REQUEST_EVENT_KEY,
                new TypeToken<CustomLightRequestEvent<CustomLightSetupRequestPayload>>() { }.getType()
        );
        this.typeMap.put(
                CustomLightStopRequestPayload.CUSTOM_LIGHT_STOP_REQUEST_EVENT_KEY,
                new TypeToken<CustomLightRequestEvent<CustomLightStopRequestPayload>>() { }.getType()
        );
    }

    @Override
    public final Optional<Event<?>> fromString(final String eventKey, final String event) {
        return Optional.of(eventKey)
                .filter(this.typeMap::containsKey)
                .map(key -> new Gson().fromJson(event, this.typeMap.get(key)));
    }
}
