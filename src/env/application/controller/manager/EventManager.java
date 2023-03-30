/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.controller.manager;

import application.presenter.event.model.Event;

import java.util.function.Consumer;

/**
 * Interface that models a manager that is able to obtain event from the external.
 */
public interface EventManager {
    /**
     * Method used to consume events via polling providing a consumer to process them.
     * @param eventConsumer the consumer used to consume the event.
     */
    void poll(Consumer<Event<?>> eventConsumer);
}
