/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.controller.manager;

import application.presenter.event.model.Event;

/**
 * Interface that models a sender of events to external.
 */
public interface EventSender {
    /**
     * Method used to send an event through the sender.
     * @param eventToSend the event to send.
     */
    void notify(Event<?> eventToSend);
}
