/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.serialization;

import application.presenter.event.model.Event;

import java.util.Optional;

/**
 * Interface that models an event deserializer.
 */
public interface EventDeserializer {
    /**
     * Deserialize an event from string.
     * @param eventKey the key of the event.
     * @param event the event in string format.
     * @return the deserialized event as an optional.
     */
    Optional<Event<?>> fromString(String eventKey, String event);
}
