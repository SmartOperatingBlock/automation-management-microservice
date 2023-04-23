/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.automation.request.medicaltechnology;

import application.presenter.event.model.Event;

/**
 * Medical Technology Automation request event.
 * This is the event with which a doctor accept a proposal for the adaptation of the environment
 * in order to support the usage of a medical technology.
 */
public class MedicalTechnologyAutomationRequestEvent implements Event<MedicalTechnologyAutomationRequestPayload> {
    /** Medical Technology Automation request event key. */
    public static final String MEDICAL_TECHNOLOGY_AUTOMATION_REQUEST_EVENT_KEY =
            "MEDICAL_TECHNOLOGY_AUTOMATION_REQUEST_EVENT";

    private final String key;
    private final MedicalTechnologyAutomationRequestPayload data;
    private final String dateTime;

    /**
     * Default constructor.
     * @param key the key of the event.
     * @param data the data payload of the event.
     * @param dateTime the datetime of the event.
     */
    public MedicalTechnologyAutomationRequestEvent(
            final String key,
            final MedicalTechnologyAutomationRequestPayload data,
            final String dateTime
    ) {
        this.key = key;
        this.data = data;
        this.dateTime = dateTime;
    }

    @Override
    public final String getKey() {
        return this.key;
    }

    @Override
    public final MedicalTechnologyAutomationRequestPayload getData() {
        return this.data;
    }

    @Override
    public final String getDateTime() {
        return this.dateTime;
    }
}
