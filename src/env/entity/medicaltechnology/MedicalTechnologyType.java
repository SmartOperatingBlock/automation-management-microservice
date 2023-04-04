/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.medicaltechnology;

/**
 * The type of {@link MedicalTechnology}.
 */
public enum MedicalTechnologyType {
    /** Endoscope technology. */
    ENDOSCOPE("endoscope"),
    /** X-ray technology. */
    XRAY("xray");

    private final String name;

    /**
     * Default constructor.
     * @param name the name of the medical technology type.
     */
    MedicalTechnologyType(final String name) {
        this.name = name;
    }

    /**
     * Get the display name.
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
