/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.configuration.model;

import application.presenter.configuration.model.environment.EnvironmentalDataConfig;
import application.presenter.configuration.model.environment.OperatingRoomEnvironmentalDataConfig;
import application.presenter.configuration.model.scenario.MedicalTechnologyScenario;
import application.presenter.configuration.model.standby.OperatingRoomStandbyMode;
import application.presenter.configuration.model.standby.PrePostOperatingRoomStandbyMode;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that models the configuration loaded.
 */
public class Configuration {
    private OperatingRoomEnvironmentalDataConfig operatingRoom;
    private OperatingRoomStandbyMode operatingRoomStandbyMode;
    private EnvironmentalDataConfig prePostOperatingRoom;
    private PrePostOperatingRoomStandbyMode prePostOperatingRoomStandbyMode;
    private Set<MedicalTechnologyScenario> medicalTechnologyScenarios;

    /**
     * Operating room environmental data config getter.
     * @return the operating room environmental data config.
     */
    public OperatingRoomEnvironmentalDataConfig getOperatingRoom() {
        return new OperatingRoomEnvironmentalDataConfig(this.operatingRoom);
    }

    /**
     * Operating room environmental data config setter.
     * @param operatingRoom the operating room environmental data config to set.
     */
    public void setOperatingRoom(final OperatingRoomEnvironmentalDataConfig operatingRoom) {
        this.operatingRoom = new OperatingRoomEnvironmentalDataConfig(operatingRoom);
    }

    /**
     * Operating room standby mode config getter.
     * @return the operating room standby mode.
     */
    public OperatingRoomStandbyMode getOperatingRoomStandbyMode() {
        return new OperatingRoomStandbyMode(this.operatingRoomStandbyMode);
    }

    /**
     * Operating room standby mode config setter.
     * @param operatingRoomStandbyMode config to set.
     */
    public void setOperatingRoomStandbyMode(final OperatingRoomStandbyMode operatingRoomStandbyMode) {
        this.operatingRoomStandbyMode = new OperatingRoomStandbyMode(operatingRoomStandbyMode);
    }

    /**
     * Pre/Post operating room environmental data config getter.
     * @return the pre/post operating room environmental data config.
     */
    public EnvironmentalDataConfig getPrePostOperatingRoom() {
        return new EnvironmentalDataConfig(this.prePostOperatingRoom);
    }

    /**
     * Pre/Post operating room environmental data config setter.
     * @param prePostOperatingRoom the pre/post operating room environmental data config to set.
     */
    public void setPrePostOperatingRoom(final EnvironmentalDataConfig prePostOperatingRoom) {
        this.prePostOperatingRoom = new EnvironmentalDataConfig(prePostOperatingRoom);
    }

    /**
     * Pre/Post operating room standby mode config getter.
     * @return the pre/post operating room standby mode.
     */
    public PrePostOperatingRoomStandbyMode getPrePostOperatingRoomStandbyMode() {
        return new PrePostOperatingRoomStandbyMode(this.prePostOperatingRoomStandbyMode);
    }

    /**
     * Pre/Post operating room standby mode config setter.
     * @param prePostOperatingRoomStandbyMode config to set.
     */
    public void setPrePostOperatingRoomStandbyMode(final PrePostOperatingRoomStandbyMode prePostOperatingRoomStandbyMode) {
        this.prePostOperatingRoomStandbyMode = new PrePostOperatingRoomStandbyMode(prePostOperatingRoomStandbyMode);
    }

    /**
     * Medical Technology scenario set getter.
     * @return the medical technology scenarios set in the configuration.
     */
    public Set<MedicalTechnologyScenario> getMedicalTechnologyScenarios() {
        return new HashSet<>(this.medicalTechnologyScenarios);
    }

    /**
     * Setter of the medical technology scenarios.
     * @param medicalTechnologyScenarios to set.
     */
    public void setMedicalTechnologyScenarios(final Set<MedicalTechnologyScenario> medicalTechnologyScenarios) {
        this.medicalTechnologyScenarios = new HashSet<>(medicalTechnologyScenarios);
    }
}
