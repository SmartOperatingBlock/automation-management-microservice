/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.configuration.model.scenario;

import java.util.Objects;

/**
 * Class that models a medical technology scenario config.
 */
public class MedicalTechnologyScenario {
    private String medicalTechnologyType;
    private Double ambientIlluminance;
    private Double surgicalIlluminance;

    /** Default constructor. */
    public MedicalTechnologyScenario() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     * Constructor to perform copy.
     * @param other to copy.
     */
    public MedicalTechnologyScenario(final MedicalTechnologyScenario other) {
        this.medicalTechnologyType = other.medicalTechnologyType;
        this.ambientIlluminance = other.ambientIlluminance;
        this.surgicalIlluminance = other.surgicalIlluminance;
    }

    /**
     * Medical technology type getter.
     * @return the medical technology type.
     */
    public String getMedicalTechnologyType() {
        return this.medicalTechnologyType;
    }

    /**
     * Medical Technology type setter.
     * @param medicalTechnologyType to set.
     */
    public void setMedicalTechnologyType(final String medicalTechnologyType) {
        this.medicalTechnologyType = medicalTechnologyType;
    }

    /**
     * Ambient light getter.
     * @return the ambient light.
     */
    public Double getAmbientIlluminance() {
        return this.ambientIlluminance;
    }

    /**
     * Ambient light setter.
     * @param ambientIlluminance to set.
     */
    public void setAmbientIlluminance(final Double ambientIlluminance) {
        this.ambientIlluminance = ambientIlluminance;
    }

    /**
     * Surgical light getter.
     * @return the surgical light.
     */
    public Double getSurgicalIlluminance() {
        return this.surgicalIlluminance;
    }

    /**
     * Surgical light setter.
     * @param surgicalIlluminance to set.
     */
    public void setSurgicalIlluminance(final Double surgicalIlluminance) {
        this.surgicalIlluminance = surgicalIlluminance;
    }

    @Override
    public final boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        final MedicalTechnologyScenario that = (MedicalTechnologyScenario) other;
        return this.getMedicalTechnologyType().equals(that.getMedicalTechnologyType());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.getMedicalTechnologyType());
    }
}
