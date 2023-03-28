/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/*
 * This agent loads the configuration using the ConfigurationArtifact
 * and inform the right agents inside the workspace of the work to do.
 */

!loadConfig.

+!loadConfig
    <- makeArtifact(configuration, "artifact.config.ConfigurationArtifact", [], ConfigurationId);
       focus(ConfigurationId);
       load.

+config(X)
    <- println(X).
    // todo: inform agents

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }