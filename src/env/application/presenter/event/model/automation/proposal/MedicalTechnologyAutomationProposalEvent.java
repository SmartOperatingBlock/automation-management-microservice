/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.automation.proposal;

import application.presenter.event.model.Event;
import application.presenter.event.model.automation.proposal.payload.MedicalTechnologyAutomationProposalPayload;

/**
 * Medical Technology Automation Proposal event.
 * This is the event with which this service propose to an Operating Room the automation scenario that supports
 * the usage of a medical technology.
 */
public class MedicalTechnologyAutomationProposalEvent implements Event<MedicalTechnologyAutomationProposalPayload> {
    /** Medical Technology Automation Proposal event key. */
    public static final String MEDICAL_TECHNOLOGY_AUTOMATION_PROPOSAL_EVENT_KEY =
            "MEDICAL_TECHNOLOGY_AUTOMATION_PROPOSAL_EVENT";

    private final String key;
    private final MedicalTechnologyAutomationProposalPayload data;
    private final String dateTime;

    /**
     * Default constructor.
     * @param data the data payload of the event.
     * @param dateTime the date time of the event.
     */
    public MedicalTechnologyAutomationProposalEvent(
            final MedicalTechnologyAutomationProposalPayload data,
            final String dateTime) {
        this.key = MEDICAL_TECHNOLOGY_AUTOMATION_PROPOSAL_EVENT_KEY;
        this.data = data;
        this.dateTime = dateTime;
    }

    @Override
    public final String getKey() {
        return this.key;
    }

    @Override
    public final MedicalTechnologyAutomationProposalPayload getData() {
        return this.data;
    }

    @Override
    public final String getDateTime() {
        return this.dateTime;
    }
}
