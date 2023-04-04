/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.model.medicaltechnology;

/**
 * Type of medical technology that is involved in the {@link MedicalTechnologyEvent}.
 */
public enum MedicalTechnologyTypePayload {
    /** Endoscope. */
    ENDOSCOPE("endoscope"),
    /** X-Ray. */
    XRAY("xray");

    private final String name;

    /**
     * Default constructor.
     * @param name the name
     */
    MedicalTechnologyTypePayload(final String name) {
        this.name = name;
    }

    /**
     * Obtain the name of the type.
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
