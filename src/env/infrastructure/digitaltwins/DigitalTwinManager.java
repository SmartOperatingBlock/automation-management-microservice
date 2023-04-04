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
import entity.medicaltechnology.MedicalTechnology;
import entity.medicaltechnology.MedicalTechnologyID;
import entity.room.RoomID;
import infrastructure.digitaltwins.adtpresentation.MedicalTechnologyAdtPresentation;
import jade.util.Logger;
import usecase.repository.MedicalTechnologyRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Digital Twin Manager that implements the repositories of the application.
 */
public class DigitalTwinManager implements MedicalTechnologyRepository {
    private static final String DT_APP_ID_VARIABLE = "AZURE_CLIENT_ID";
    private static final String DT_TENANT_VARIABLE = "AZURE_TENANT_ID";
    private static final String DT_APP_SECRET_VARIABLE = "AZURE_CLIENT_SECRET";
    private static final String DT_ENDPOINT_VARIABLE = "AZURE_DT_ENDPOINT";

    private final DigitalTwinsClient dtClient;

    /**
     * Default constructor.
     */
    public DigitalTwinManager() {
        Objects.requireNonNull(System.getenv(DT_APP_ID_VARIABLE), "Azure client app id required");
        Objects.requireNonNull(System.getenv(DT_TENANT_VARIABLE), "Azure tenant id required");
        Objects.requireNonNull(System.getenv(DT_APP_SECRET_VARIABLE), "Azure client secret id required");
        Objects.requireNonNull(System.getenv(DT_ENDPOINT_VARIABLE), "Azure dt endpoint required");
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
                        client.query("SELECT TOP(1) CT.$dtId"
                                        + "FROM DIGITALTWINS T"
                                        + "JOIN CT RELATED T."
                                        + MedicalTechnologyAdtPresentation.IS_LOCATED_IN_OPERATING_ROOM_RELATIONSHIP
                                        + "WHERE T.$dtId = '"
                                        + medicalTechnologyID.getId()
                                        + "'",
                                        String.class).stream()
                                .findFirst()
                                .map(firstResult ->
                                        new RoomID(new Gson().fromJson(firstResult, JsonObject.class).get("$dtId").getAsString())
                                )
                );
            });
        });
    }

    private <R> R applySafeDigitalTwinOperation(final R defaultResult, final Function<DigitalTwinsClient, R> operation) {
        try {
            return operation.apply(this.dtClient);
        } catch (final ErrorResponseException exception) {
            Logger.getLogger(DigitalTwinManager.class.toString()).fine(exception.getMessage());
            return defaultResult;
        }
    }
}
