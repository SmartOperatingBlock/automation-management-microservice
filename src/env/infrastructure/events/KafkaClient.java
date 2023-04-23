/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.events;

import application.controller.manager.EventManager;
import application.controller.manager.EventSender;
import application.presenter.event.model.Event;
import application.presenter.event.serialization.EventDeserializer;
import application.presenter.event.serialization.EventDeserializerImpl;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * This class manage the Kafka client needed to consume events.
 */
public class KafkaClient implements EventManager, EventSender {
    private static final String BOOSTRAP_SERVER_URL_VARIABLE = "BOOTSTRAP_SERVER_URL";
    private static final String SCHEMA_REGISTRY_URL_VARIABLE = "SCHEMA_REGISTRY_URL";
    private static final String ROOM_EVENT_TOPIC = "room-events";
    private static final String MEDICAL_TECHNOLOGY_EVENT_TOPIC = "process-events";
    private static final long POLLING_TIME = 100L;

    private static KafkaClient instance;

    private final KafkaConsumer<String, String> kafkaConsumer;
    private final KafkaProducer<String, String> kafkaProducer;
    private final EventDeserializer eventDeserializer;

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
        final Map<String, Object> kafkaConfiguration = loadConfiguration(
                System.getenv(BOOSTRAP_SERVER_URL_VARIABLE),
                System.getenv(SCHEMA_REGISTRY_URL_VARIABLE)
        );
        this.kafkaConsumer = new KafkaConsumer<>(kafkaConfiguration);
        this.kafkaProducer = new KafkaProducer<>(kafkaConfiguration);
        this.kafkaConsumer.subscribe(List.of(ROOM_EVENT_TOPIC, MEDICAL_TECHNOLOGY_EVENT_TOPIC));
        this.eventDeserializer = new EventDeserializerImpl();
    }

    @Override
    public final void notify(final Event<?> eventToSend) {
        this.kafkaProducer.send(new ProducerRecord<>(
                getTopicFromEventKey(eventToSend.getKey()),
                eventToSend.getKey(),
                eventToSend)
        );
    }

    @Override
    public final void poll(final Consumer<Event<?>> eventConsumer) {
        while (true) {
            this.kafkaConsumer.poll(Duration.ofMillis(POLLING_TIME)).forEach(event ->
                    this.eventDeserializer.fromString(event.key(), event.value()).ifPresent(eventConsumer));
        }
    }

    private Map<String, Object> loadConfiguration(final String boostrapServerUrl, final String schemaRegistryUrl) {
        return Map.of(
            "bootstrap.servers", boostrapServerUrl,
            "schema.registry.url", schemaRegistryUrl,
            "group.id", "automation-management-consumer",
            "key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer",
            "value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer",
            "key.serializer", "org.apache.kafka.common.serialization.StringSerializer",
            "value.serializer", "org.apache.kafka.common.serialization.StringSerializer"
        );
    }

    private String getTopicFromEventKey(final String eventKey) {
        // todo: switch with the key of the event
        return eventKey;
    }
}
