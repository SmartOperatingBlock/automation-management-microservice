/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model;

/**
 * Interface that models a generic event with no additional fields but only with a key,
 * a payload data of type E and the dateTime of the event itself.
 * @param <E> the type of the payload data.
 */
public interface Event<E> {
    /**
     * Get the key of the event.
     * @return the key.
     */
    String getKey();

    /**
     * Get the data payload of the event.
     * @return the data payload.
     */
    E getData();

    /**
     * Get the date time in string format.
     * @return the string of date time.
     */
    String getDateTime();
}
