/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.automation.proposal.payload;

/**
 * {@link application.presenter.event.model.automation.proposal.MedicalTechnologyAutomationProposalEvent} event payload.
 */
public class MedicalTechnologyAutomationProposalPayload {
    private final String roomId;
    private final String medicalTechnologyType;
    private final int ambientLightLux;
    private final int surgicalLightLux;

    /**
     * Default constructor.
     * @param roomId the room id where the medical technology is in use.
     * @param medicalTechnologyType the medical technology type name that is in use.
     * @param ambientLightLux the lux to set in ambient lights.
     * @param surgicalLightLux the lux to set in surgical lights.
     */
    public MedicalTechnologyAutomationProposalPayload(
            final String roomId,
            final String medicalTechnologyType,
            final int ambientLightLux,
            final int surgicalLightLux
    ) {
        this.roomId = roomId;
        this.medicalTechnologyType = medicalTechnologyType;
        this.ambientLightLux = ambientLightLux;
        this.surgicalLightLux = surgicalLightLux;
    }

    /**
     * Get the room id involved in the event.
     * @return the room id
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

    /**
     * Get the lux to set in the ambient lights.
     * @return the lux to set.
     */
    public int getAmbientLightLux() {
        return this.ambientLightLux;
    }

    /**
     * Get the lux to set in the surgical lights.
     * @return the lux to set.
     */
    public int getSurgicalLightLux() {
        return this.surgicalLightLux;
    }
}
