/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.digitaltwins;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.DigitalTwinsClient;
import com.azure.digitaltwins.core.DigitalTwinsClientBuilder;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.actuator.ActuatorID;
import entity.actuator.ActuatorType;
import entity.medicaltechnology.MedicalTechnology;
import entity.medicaltechnology.MedicalTechnologyID;
import entity.room.RoomID;
import infrastructure.digitaltwins.adtpresentation.ActuatorAdtPresentation;
import infrastructure.digitaltwins.adtpresentation.MedicalTechnologyAdtPresentation;
import infrastructure.digitaltwins.query.AdtQuery;
import usecase.repository.ActuatorRepository;
import usecase.repository.MedicalTechnologyRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * Digital Twin Manager that implements the repositories of the application.
 */
public class DigitalTwinManager implements MedicalTechnologyRepository, ActuatorRepository {
    private static final String DT_APP_ID_VARIABLE = "AZURE_CLIENT_ID";
    private static final String DT_TENANT_VARIABLE = "AZURE_TENANT_ID";
    private static final String DT_APP_SECRET_VARIABLE = "AZURE_CLIENT_SECRET";
    private static final String DT_ENDPOINT_VARIABLE = "AZURE_DT_ENDPOINT";

    private final DigitalTwinsClient dtClient;

    static {
        // Checks on existence of environmental variable
        Objects.requireNonNull(System.getenv(DT_APP_ID_VARIABLE), "Azure client app id required");
        Objects.requireNonNull(System.getenv(DT_TENANT_VARIABLE), "Azure tenant id required");
        Objects.requireNonNull(System.getenv(DT_APP_SECRET_VARIABLE), "Azure client secret id required");
        Objects.requireNonNull(System.getenv(DT_ENDPOINT_VARIABLE), "Azure dt endpoint required");
    }

    /**
     * Default constructor.
     */
    public DigitalTwinManager() {
        this.dtClient = new DigitalTwinsClientBuilder()
                .credential(new DefaultAzureCredentialBuilder().build())
                .endpoint(System.getenv(DT_ENDPOINT_VARIABLE))
                .buildClient();
    }

    @Override
    public final Optional<MedicalTechnology> findBy(final MedicalTechnologyID medicalTechnologyID) {
        return this.applySafeDigitalTwinOperation(Optional.empty(), client -> {
            // Get the medical technology digital twin and obtain also the room in which it is located (if present)
            return MedicalTechnologyAdtPresentation.toMedicalTechnology(
                    client.getDigitalTwin(medicalTechnologyID.getId(), BasicDigitalTwin.class)
            ).map(medicalTechnology -> {
                // Get the room if exists
                return new MedicalTechnology(medicalTechnology.getId(),
                        medicalTechnology.getType(),
                        client.query(new AdtQuery().selectTop(1, "CT.$dtId")
                                                .fromDigitalTwins("T")
                                                .joinRelationship(
                                                        "CT",
                                                        "T",
                                                        MedicalTechnologyAdtPresentation.IS_LOCATED_IN_OPERATING_ROOM_RELATIONSHIP
                                                ).where("T.$dtId = '" + medicalTechnologyID.getId() + "'")
                                                .toQuery(),
                                        String.class).stream()
                                .findFirst()
                                .map(firstResult ->
                                        new RoomID(new Gson().fromJson(firstResult, JsonObject.class).get("$dtId").getAsString())
                                )
                );
            });
        });
    }

    @Override
    public final Optional<ActuatorID> findActuatorInRoom(final ActuatorType actuatorType, final RoomID roomID) {
        return this.applySafeDigitalTwinOperation(Optional.empty(), client ->
                client.query(new AdtQuery().selectTop(1, "CT.$dtId")
                                        .fromDigitalTwins("T")
                                        .joinRelationship(
                                                "CT",
                                                "T",
                                                ActuatorAdtPresentation.HAS_ACTUATOR_RELATIONSHIP
                                        ).where("T.$dtId = '" + roomID.getId() + "'")
                                        .and("CT.type = "
                                                + ActuatorAdtPresentation.toActuatorDigitalTwinType(actuatorType)
                                        ).toQuery(),
                            String.class).stream()
                .findFirst()
                .map(firstResult ->
                        new ActuatorID(new Gson().fromJson(firstResult, JsonObject.class).get("$dtId").getAsString())
                ));
    }

    private <R> R applySafeDigitalTwinOperation(final R defaultResult, final Function<DigitalTwinsClient, R> operation) {
        try {
            return operation.apply(this.dtClient);
        } catch (final ErrorResponseException exception) {
            Logger.getLogger(DigitalTwinManager.class.toString()).info(exception.getMessage());
            return defaultResult;
        }
    }
}
