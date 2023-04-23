/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.serialization;

import application.presenter.event.model.Event;
import application.presenter.event.model.automation.proposal.MedicalTechnologyAutomationProposalEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@link EventSerializer} interface that allows an event to be
 * serialized to String.
 */
public class EventSerializerImpl implements EventSerializer {
    private final Map<String, Type> typeMap = new HashMap<>();

    /**
     * Default constructor.
     */
    public EventSerializerImpl() {
        this.typeMap.put(
                MedicalTechnologyAutomationProposalEvent.MEDICAL_TECHNOLOGY_AUTOMATION_PROPOSAL_EVENT_KEY,
                new TypeToken<MedicalTechnologyAutomationProposalEvent>() { }.getType()
        );
    }

    @Override
    public final Optional<String> eventToString(final Event<?> eventToSerialize) {
        return Optional.of(eventToSerialize.getKey())
                .filter(this.typeMap::containsKey)
                .map(key -> new Gson().toJson(eventToSerialize, this.typeMap.get(key)));
    }
}
