/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package artifact.environment;

import application.controller.manager.EventSender;
import application.presenter.event.model.automation.proposal.MedicalTechnologyAutomationProposalEvent;
import application.presenter.event.model.automation.proposal.payload.MedicalTechnologyAutomationProposalPayload;
import cartago.Artifact;
import cartago.OPERATION;
import infrastructure.events.KafkaClient;

import java.time.Instant;

/**
 * CArtAgO artifact that is responsible to send the automation proposals to the operating rooms.
 */
public class OperatingRoomProposerArtifact extends Artifact {
    private EventSender eventSender;

    /**
     * Initialize the operating room proposer artifact.
     */
    void init() {
        this.eventSender = KafkaClient.getInstance();
    }

    /**
     * Send medical technology automation scenario proposal due to the use of a medical technology
     * inside the operating room.
     * @param roomId the room id.
     * @param medicalTechnologyType the type of medical technology involved.
     * @param ambientLightLux the lux to propose in ambient lights.
     * @param surgicalLightLux the lux to propose in surgical lights.
     */
    @OPERATION
    void sendMedicalTechnologyAutomationProposal(
            final String roomId,
            final String medicalTechnologyType,
            final int ambientLightLux,
            final int surgicalLightLux
    ) {
        this.eventSender.notify(new MedicalTechnologyAutomationProposalEvent(
                new MedicalTechnologyAutomationProposalPayload(
                        roomId,
                        medicalTechnologyType,
                        ambientLightLux,
                        surgicalLightLux
                ),
                Instant.now().toString()
        ));
    }
}
