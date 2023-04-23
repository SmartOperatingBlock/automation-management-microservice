/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.automation.request.medicaltechnology;

/**
 * {@link MedicalTechnologyAutomationRequestEvent} event payload.
 */
public class MedicalTechnologyAutomationRequestPayload {
    private final String roomId;
    private final String medicalTechnologyType;

    /**
     * Default constructor.
     * @param roomId the room id in which the automation is requested.
     * @param medicalTechnologyType the medical technology type for which the automation is requested.
     */
    public MedicalTechnologyAutomationRequestPayload(final String roomId, final String medicalTechnologyType) {
        this.roomId = roomId;
        this.medicalTechnologyType = medicalTechnologyType;
    }

    /**
     * Get the room id involved in the event.
     * @return the room id.
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * Get the medical technology type name involved in the event.
     * @return the medical technology type name.
     */
    public String getMedicalTechnologyType() {
        return this.medicalTechnologyType;
    }
}
