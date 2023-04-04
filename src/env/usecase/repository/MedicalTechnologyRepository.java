/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository;

import entity.medicaltechnology.MedicalTechnology;
import entity.medicaltechnology.MedicalTechnologyID;

import java.util.Optional;

/**
 * Interface that models the repository to manage Medical Technologies.
 */
public interface MedicalTechnologyRepository {
    /**
     * Find a medical technology by its id and gets its data.
     * @param medicalTechnologyID the id used to find the medical technology
     * @return the medical technology data
     */
    Optional<MedicalTechnology> findBy(MedicalTechnologyID medicalTechnologyID);
}
