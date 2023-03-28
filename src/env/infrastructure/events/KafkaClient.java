/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.events;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * This class manage the Kafka client needed to consume events.
 */
public class KafkaClient {
    private static final String BOOSTRAP_SERVER_URL_VARIABLE = "BOOTSTRAP_SERVER_URL";
    private static final String SCHEMA_REGISTRY_URL_VARIABLE = "SCHEMA_REGISTRY_URL";
    private static final String ROOM_EVENT_TOPIC = "room-events";
    private static final String MEDICAL_TECHNOLOGY_EVENT_TOPIC = "process-events";
    private static final long POLLING_TIME = 100L;

    private static KafkaClient instance;

    private final KafkaConsumer<String, String> kafkaConsumer;

    /**
     * Obtain the current instance of the Kafka Client.
     * @return the kafka client.
     */
    public static synchronized KafkaClient getInstance() {
        if (instance == null) {
            instance = new KafkaClient();
        }
        return instance;
    }

    /**
     * Default constructor.
     */
    protected KafkaClient() {
        Objects.requireNonNull(System.getenv(BOOSTRAP_SERVER_URL_VARIABLE), "Kafka bootstrap server url required");
        Objects.requireNonNull(System.getenv(SCHEMA_REGISTRY_URL_VARIABLE), "Kafka schema registry url required");
        this.kafkaConsumer = new KafkaConsumer<>(
                loadConfiguration(
                        System.getenv(BOOSTRAP_SERVER_URL_VARIABLE),
                        System.getenv(SCHEMA_REGISTRY_URL_VARIABLE)
                )
        );
        this.kafkaConsumer.subscribe(List.of(ROOM_EVENT_TOPIC, MEDICAL_TECHNOLOGY_EVENT_TOPIC));
    }

    /**
     * Polling cycle to obtain all the events.
     */
    public void poll() {
        while (true) {
            this.kafkaConsumer.poll(Duration.ofMillis(POLLING_TIME)).forEach(event -> {
                Logger.getLogger(KafkaClient.class.getName()).fine(event.toString());
            });
        }
    }

    private Map<String, Object> loadConfiguration(final String boostrapServerUrl, final String schemaRegistryUrl) {
        return Map.of(
            "bootstrap.servers", boostrapServerUrl,
            "schema.registry.url", schemaRegistryUrl,
            "group.id", "automation-management-consumer",
            "key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer",
            "value.deserializer", "io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer"
        );
    }
}
