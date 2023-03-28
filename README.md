# Automation management microservice
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