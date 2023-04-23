/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.automation.request.customlight.payload;

/**
 * {@link application.presenter.event.model.automation.request.customlight.CustomLightRequestEvent} event payload
 * that represent the request to stop the custom management of lights in a specific operating room.
 */
public class CustomLightStopRequestPayload implements CustomLightSetupPayload {
    /** Custom Light Stop request event key. */
    public static final String CUSTOM_LIGHT_STOP_REQUEST_EVENT_KEY = "CUSTOM_LIGHT_STOP_REQUEST_EVENT";

    private final String roomId;

    /**
     * Default constructor.
     * @param roomId the room id involved in the event.
     */
    public CustomLightStopRequestPayload(final String roomId) {
        this.roomId = roomId;
    }

    /**
     * Get the room id involved in the event.
     * @return the room id.
     */
    public String getRoomId() {
        return this.roomId;
    }
}
