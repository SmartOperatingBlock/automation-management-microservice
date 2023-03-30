/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.medicaltechnology.payload;

import application.presenter.event.model.medicaltechnology.MedicalTechnologyTypePayload;

/**
 * {@link application.presenter.event.model.medicaltechnology.MedicalTechnologyEvent} event payload.
 */
public class MedicalTechnologyUsagePayload {
    /** Medical Technology Usage event key. */
    public static final String MEDICAL_TECHNOLOGY_USAGE_EVENT = "MEDICAL_TECHNOLOGY_USAGE_EVENT";
    private final String medicalTechnologyID;
    private final MedicalTechnologyTypePayload medicalTechnologyType;
    private final boolean inUse;

    /**
     * Default constructor.
     * @param medicalTechnologyID the medical technology id.
     * @param medicalTechnologyType the type of the medical technology.
     * @param inUse if the medical technology is in use.
     */
    public MedicalTechnologyUsagePayload(
            final String medicalTechnologyID,
            final MedicalTechnologyTypePayload medicalTechnologyType,
            final boolean inUse
    ) {
        this.medicalTechnologyID = medicalTechnologyID;
        this.medicalTechnologyType = medicalTechnologyType;
        this.inUse = inUse;
    }

    /**
     * Get the medical technology id involved in the event.
     * @return the medical technology id.
     */
    public String getMedicalTechnologyID() {
        return this.medicalTechnologyID;
    }

    /**
     * Get the medical technology type involved in the event.
     * @return the medical technology type.
     */
    public MedicalTechnologyTypePayload getMedicalTechnologyType() {
        return this.medicalTechnologyType;
    }

    /**
     * State if the medical technology is being used.
     * @return true if the involved medical technology is in use, false otherwise.
     */
    public boolean isInUse() {
        return this.inUse;
    }
}
