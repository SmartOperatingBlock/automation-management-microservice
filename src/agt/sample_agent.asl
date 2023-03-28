/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/* Initial beliefs and rules */

/* Initial goals */

!start.
!loadConfig.

/* Plans */

+!start : true <- .print("hello world.").

+!loadConfig
    <- makeArtifact(configuration, "config.ConfigurationArtifact", [], ConfigurationId);
       focus(ConfigurationId);
       load.

+config(X)
    <- println(X).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
