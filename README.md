# nats-demo

## Getting started

1. Download Keycloak adapter `git clone git@github.com:trodix/keycloak-nats.git`
2. Start containers (postgres + keycloak + nats) `docker compose up -d`
3. Configure Keycloak (create a client and enable events for ths client)
4. Build and install the extension
   ```bash
   cd keycloak-nats && ./gradlew shadowJar && mv build/libs/keycloak-nats-adapter-0.2.0-all.jar ../nats-demo/keycloak/providers/ && docker restart keycloak
   ```
5. Configure `app.keycloak.realmId` in [application.yml](./src/main/resources/application.yml)  
6. Run spring boot `mvn spring-boot:run`