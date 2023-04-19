/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package architecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * Test the clean architecture.
 */
class CleanArchitectureTest {
    @Test
    void testCleanArchitecture() {
        final String entityLayer = "entity";
        final String usecaseLayer = "usecase";
        final String applicationLayer = "application";
        final String infrastructureLayer = "infrastructure";

        layeredArchitecture()
            .consideringAllDependencies()
            .layer(entityLayer).definedBy(".." + entityLayer + "..")
            .layer(usecaseLayer).definedBy(".." + usecaseLayer + "..")
            .layer(applicationLayer).definedBy(".." + applicationLayer + "..")
            .layer(infrastructureLayer).definedBy(".." + infrastructureLayer + "..")
            .whereLayer(entityLayer).mayOnlyBeAccessedByLayers(usecaseLayer, applicationLayer, infrastructureLayer)
            .whereLayer(usecaseLayer).mayOnlyBeAccessedByLayers(applicationLayer, infrastructureLayer)
            .whereLayer(applicationLayer).mayOnlyBeAccessedByLayers(infrastructureLayer)
            .whereLayer(infrastructureLayer).mayNotBeAccessedByAnyLayer()
            .check(new ClassFileImporter()
                            .withImportOption(element -> !element.contains("/test/")) // ignore tests classes
                            .importPackages(entityLayer, usecaseLayer, applicationLayer, infrastructureLayer)
            );
    }
}
