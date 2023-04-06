/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository;

import entity.actuator.ActuatorID;
import entity.actuator.ActuatorType;
import entity.room.RoomID;

import java.util.Optional;

/**
 * Interface that models the repository to manage Actuators.
 */
public interface ActuatorRepository {
    /**
     * Method to find an actuator of a specific type inside a room specified with its id.
     * @param actuatorType the type of actuator to find
     * @param roomID the room id where the actuator should be placed.
     * @return an optional that contains the actuator id if found, empty otherwise.
     */
    Optional<ActuatorID> findActuatorInRoom(ActuatorType actuatorType, RoomID roomID);
}
