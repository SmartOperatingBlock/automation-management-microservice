/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.wot;

import city.sane.wot.DefaultWot;
import city.sane.wot.Wot;
import city.sane.wot.WotException;
import city.sane.wot.thing.ConsumedThing;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/**
 * Web of things client.
 */
public class WotClient {
    private static final String THING_DIRECTORY_VARIABLE = "THING_DESCRIPTION_DIRECTORY_BASE_URL";
    private ConsumedThing thing;

    static {
        // Checks on existence of environmental variable
        Objects.requireNonNull(
                System.getenv(THING_DIRECTORY_VARIABLE),
                "Thing Description Directory service base url is required"
        );
    }

    /**
     * Default constructor.
     * @param thingId the thing id to use.
     */
    public WotClient(final String thingId) {
        try {
            final String thingUrl = System.getenv(THING_DIRECTORY_VARIABLE) + thingId;
            final Wot wotClient = DefaultWot.clientOnly();
            final String thingDescription = this.obtainThingDescription(thingUrl);
            this.thing = wotClient.consume(thingDescription);
        } catch (final WotException e) {
            Logger.getLogger(WotClient.class.toString()).info("Wot TDD configuration error");
        } catch (IOException e) {
            Logger.getLogger(WotClient.class.toString()).info(thingId + " does not have a Thing Description associated");
        }
    }

    /**
     * Invoke an action on the thing.
     * @param action the name of the action.
     * @param inputs the inputs of the action.
     */
    public void invoke(final String action, final Map<String, Object> inputs) {
        try {
            this.thing.getActions().get(action).invoke(inputs).get();
        } catch (final InterruptedException | ExecutionException e) {
            Logger.getLogger(WotClient.class.toString()).info("Error in invoking '" + action + "' on " + thing.getId());
        }
    }

    private String obtainThingDescription(final String thingUrl) throws IOException {
        // We need that because sane-city wot doesn't support application/td+json schema.
        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(thingUrl).get().build();
        try (Response response = httpClient.newCall(request).execute()) {
            return Optional.ofNullable(response.body()).map(responseBody -> {
                try {
                    return responseBody.string();
                } catch (IOException e) {
                    return "";
                }
            }).orElse("");
        }
    }
}
