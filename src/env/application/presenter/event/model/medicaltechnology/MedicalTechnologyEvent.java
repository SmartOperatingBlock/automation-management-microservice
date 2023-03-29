/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.medicaltechnology;

import application.presenter.event.model.Event;
import application.presenter.event.model.medicaltechnology.payload.MedicalTechnologyUsagePayload;

/**
 * Medical Technology update event.
 */
public class MedicalTechnologyEvent implements Event<MedicalTechnologyUsagePayload> {
    private final String key;
    private final MedicalTechnologyUsagePayload data;
    private final String dateTime;

    /**
     * Default constructor.
     * @param key the key of the event.
     * @param data the data payload of the event.
     * @param dateTime the date time of the event.
     */
    public MedicalTechnologyEvent(
            final String key,
            final MedicalTechnologyUsagePayload data,
            final String dateTime) {
        this.key = key;
        this.data = data;
        this.dateTime = dateTime;
    }

    @Override
    public final String getKey() {
        return this.key;
    }

    @Override
    public final MedicalTechnologyUsagePayload getData() {
        return this.data;
    }

    @Override
    public final String getDateTime() {
        return this.dateTime;
    }
}
