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
 * Interface that models an event serializer.
 */
public interface EventSerializer {
    /**
     * Serialize an event to string.
     * @param eventToSerialize the event to serialize.
     * @return the string corresponding to the event.
     */
    Optional<String> eventToString(Event<?> eventToSerialize);
}
