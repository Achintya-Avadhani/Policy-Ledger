# HDFC Life Policy Ledger

A guided-learning Spring Boot project for organizing life insurance policies, customers, riders, and claims. The code is arranged in layers so the learning path can progress from the domain and persistence layer through services, request validation, REST endpoints, and error handling.

> **Current project status:** The guided-learning placeholders are implemented, and a Maven `pom.xml` is included. You can build and run the application with the commands below.

## Project structure

```text
.
├── README.md
├── pom.xml
├── .gitignore
└── src/
    └── main/
        ├── java/com/hdfclife/ledger/
│   ├── LedgerApplication.java
│   ├── config/
│   │   ├── DataSeeder.java
│   │   ├── OpenApiConfig.java
│   │   └── RestExceptionHandler.java
│   ├── domain/
│   │   ├── Claim.java
│   │   ├── Customer.java
│   │   ├── Policy.java
│   │   └── Rider.java
│   ├── dto/
        │   │   ├── ClaimResponse.java
        │   │   ├── CreateClaimRequest.java
        │   │   ├── CreatePolicyRequest.java
        │   │   ├── ErrorResponse.java
        │   │   └── PolicyResponse.java
        │   ├── exception/
        │   │   ├── ClaimNotFoundException.java
        │   │   ├── DeskException.java
        │   │   ├── DuplicatePolicyException.java
        │   │   ├── InvalidRequestException.java
        │   │   └── PolicyNotFoundException.java
        │   ├── repo/
        │   │   ├── ClaimRepository.java
        │   │   ├── CustomerRepository.java
        │   │   ├── PolicyRepository.java
        │   │   └── RiderRepository.java
        │   ├── service/
        │   │   ├── ClaimService.java
        │   │   └── PolicyService.java
        │   ├── validation/
        │   │   ├── PolicyType.java
        │   │   └── PolicyTypeValidator.java
        │   └── web/
        │       ├── ClaimController.java
        │       └── PolicyController.java
        └── resources/
            ├── application.yml
            ├── application-dev.yml
            ├── application-prod.yml
            └── db/migration/
                ├── V1__hdfc_life_schema.sql
                ├── V2__seed_reference_riders.sql
                └── V3__seed_policy_ledger.sql
```

### Package responsibilities

- `LedgerApplication.java` is the Spring Boot application entry point.
- `config/` holds application configuration, API documentation setup, seed-data configuration, and the global REST exception handler. Reference and example records are currently supplied by Flyway migrations.
- `domain/` contains JPA entities mapped to the Flyway schema (`customers`, `policies`, `claims`, `riders`, `policy_riders`).
- `dto/` contains the request and response models for policy and claim operations, plus a standard error response.
- `exception/` defines domain errors for missing policies or claims, duplicate policies, invalid requests, and a shared base exception.
- `repo/` is the persistence boundary for claims, customers, policies, and riders.
- `service/` contains policy and claim business rules, coordinating repositories and translating domain behavior for the web layer.
- `validation/` contains the policy product types (`TERM`, `ULIP`, and `ENDOWMENT`) and custom `@ValidPolicyType` validation.
- `web/` is the REST API boundary for policy and claim requests.
- `resources/application*.yml` configures the default application and development/production profiles.
- `resources/db/migration/` contains Flyway database migrations: the relational schema, rider reference data, and sample customers, policies, and claims.

## Configuration and data

The default profile is `dev`, and the application is configured for port `8080`. The development profile uses an in-memory H2 database (`jdbc:h2:mem:ledgerdb`), enables SQL logging and Flyway, and exposes the H2 console at `/h2-console`. Since the database is in memory, its contents are recreated when the application restarts.

The `prod` profile uses PostgreSQL by default:

```text
DB_URL=jdbc:postgresql://localhost:5432/ledgerdb
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

Set these environment variables to match your PostgreSQL server. Hibernate is configured to validate the schema rather than generate it; Flyway migrations create the database structure.

## Running the application

Install a JDK (17+) and Maven compatible with the Spring Boot version in `pom.xml`.

From the project root:

```bash
# Start with the default development profile
mvn spring-boot:run

# Explicitly select the development profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Package the application
mvn clean package

# Run the packaged application
java -jar target/*.jar
```

To start with the PostgreSQL-backed production profile:

```bash
export DB_URL="jdbc:postgresql://localhost:5432/ledgerdb"
export DB_USERNAME="postgres"
export DB_PASSWORD="your-password"
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

After the application starts, the configured base URL is `http://localhost:8080`. The H2 console is available at `http://localhost:8080/h2-console` when using the development profile. Swagger UI is at `http://localhost:8080/swagger-ui.html`.

### REST API

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/api/policies` | Create a policy (optional `riderCodes`) |
| `GET` | `/api/policies` | List all policies |
| `GET` | `/api/policies/{policyNo}` | Get a policy by number |
| `POST` | `/api/claims` | File a claim against an Active policy |
| `GET` | `/api/claims` | List all claims |
| `GET` | `/api/claims/{claimNo}` | Get a claim by number |

## Database migrations

Flyway applies migrations from `src/main/resources/db/migration` in version order:

1. `V1__hdfc_life_schema.sql` creates customer, policy, claim, rider, and policy-rider tables.
2. `V2__seed_reference_riders.sql` inserts reference rider types.
3. `V3__seed_policy_ledger.sql` inserts sample customers, policies, and a claim, and associates a rider with a policy.
