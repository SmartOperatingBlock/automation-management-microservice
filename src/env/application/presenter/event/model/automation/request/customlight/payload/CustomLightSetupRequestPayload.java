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
 * that represent the request for a custom light setup in a specific operating room.
 */
public class CustomLightSetupRequestPayload implements CustomLightSetupPayload {
    /** Custom Light Setup request event key. */
    public static final String CUSTOM_LIGHT_SETUP_REQUEST_EVENT_KEY = "CUSTOM_LIGHT_SETUP_REQUEST_EVENT";
    private final String roomId;
    private final int ambientLightLux;
    private final int surgicalLightLux;

    /**
     * Default constructor.
     * @param roomId the room id.
     * @param ambientLightLux the ambient light lux setup requested.
     * @param surgicalLightLux the surgical light lux setup requested.
     */
    public CustomLightSetupRequestPayload(
            final String roomId,
            final int ambientLightLux,
            final int surgicalLightLux
    ) {
        this.roomId = roomId;
        this.ambientLightLux = ambientLightLux;
        this.surgicalLightLux = surgicalLightLux;
    }

    /**
     * Get the room id involved in the event.
     * @return the room id.
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * Get the ambient light lux setup requested in the event.
     * @return the ambient light lux setup.
     */
    public int getAmbientLightLux() {
        return this.ambientLightLux;
    }

    /**
     * Get the surgical light lux setup requested in the event.
     * @return the surgical light lux setup.
     */
    public int getSurgicalLightLux() {
        return this.surgicalLightLux;
    }
}
