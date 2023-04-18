/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

plugins {
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "1.1.6"
    id("com.gradle.enterprise") version "3.13"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

rootProject.name = "automation-management-microservice"

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure() // Always publish Gradle Build Scan if there is a failure.
    }
}

gitHooks {
    preCommit {
        tasks("checkstyleMain")
        tasks("checkstyleTest")
        tasks("pmdMain")
        tasks("pmdTest")
        tasks("cpdJavaCheck")
        tasks("spotbugsMain")
        tasks("spotbugsTest")
    }

    commitMsg {
        conventionalCommits()
    }

    hook("post-commit") {
        from {
            "git verify-commit HEAD &> /dev/null; " +
                    "if (( $? == 1 )); then echo -e '\\033[0;31mWARNING(COMMIT UNVERIFIED): commit NOT signed\\033[0m';" +
                    "else echo -e '\\033[0;32mOK COMMIT SIGNED\\033[0m'; fi"
        }
    }

    createHooks(true)
}
