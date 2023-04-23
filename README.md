# Automation management microservice
![workflow status](https://github.com/smartoperatingblock/automation-management-microservice/actions/workflows/build-and-deploy.yml/badge.svg)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Version](https://img.shields.io/github/v/release/smartoperatingblock/automation-management-microservice?style=plastic)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_automation-management-microservice&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_automation-management-microservice)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_automation-management-microservice&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_automation-management-microservice)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_automation-management-microservice&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_automation-management-microservice)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_automation-management-microservice&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_automation-management-microservice)

This is the repository of the Automation Management microservice of the Smart Operating Block project.

## Usage
You need to specify the following environment variable:
- `AZURE_CLIENT_ID`: ID of an Azure AD application
- `AZURE_TENANT_ID`: ID of the application's Azure AD tenant
- `AZURE_CLIENT_SECRET`: the application's client secrets
- `AZURE_DT_ENDPOINT`: the Azure Digital Twins instance endpoint
- `BOOTSTRAP_SERVER_URL`: the kafka connection endpoint
- `CONFIG_PATH`: the path where the microservice can find the configuration for environmental data and automation scenarios.
- `SCHEMA_REGISTRY_URL`: the schema registry url
- `THING_DESCRIPTION_DIRECTORY_BASE_URL`: the base url for the thing description directory. To this base url must be necessary to add only the id of the thing.

If you want to run it via docker container:
1. Provide a `.env` file with all the environment variable described above
2. Run the container with the command:
   ```bash
    docker run ghcr.io/smartoperatingblock/automation-management-microservice:latest
    ```
   1. If you want to pass an environment file whose name is different from `.env` use the `--env-file <name>` parameter.
   
## Documentation
- Check out the website [here](https://smartoperatingblock.github.io/automation-management-microservice/)
- Direct link to the *Code* documentation [here](https://smartoperatingblock.github.io/automation-management-microservice/documentation/code-doc/)
- Direct link to the *AsyncAPI* documentation [here](https://smartoperatingblock.github.io/automation-management-microservice/documentation/asyncapi-doc)
