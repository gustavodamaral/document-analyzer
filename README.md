# Document Analyzer Project

This is a multi-module Spring Boot project that uses Gradle for building and managing dependencies. It integrates with AWS services through **LocalStack** and uses **PostgreSQL** as the database. The project also leverages **Google Vertex AI** for AI-driven features like synonym generation using the **Gemini AI** model.

## Project Structure

```plaintext
.
├── buildSrc                 # Gradle build scripts
├── docanalyzer-api           # API module responsible for REST endpoints
├── docanalyzer-commons       # Shared libraries for common logic
├── docanalyzer-core          # Core domain and services for business logic
├── docanalyzer-infrastructure # Infrastructure layer for database, S3, and AI integrations
├── docker-compose.yml        # Docker Compose file for local services
├── gradle                    # Gradle wrapper and libraries
├── init-scripts              # Database initialization scripts for PostgreSQL
├── s3-data                   # Directory holding mock S3 data to be uploaded
└── settings.gradle           # Gradle settings file for managing multi-module project
```

## Key Components

- **docanalyzer-api**: Contains REST controllers for document and user-related operations.
- **docanalyzer-core**: Contains core business logic such as analyzing word frequency and fetching synonyms via Gemini AI.
- **docanalyzer-infrastructure**: Handles database entities, repositories, S3 interactions, and Gemini API integration.

## Local Development Setup

This project uses **Docker Compose** to spin up required services locally, such as:

- **LocalStack**: Simulates AWS S3 for document storage.
- **AWS CLI**: Automates uploading mock documents to LocalStack's S3.
- **PostgreSQL**: Relational database for storing document and user data.

## Docker Compose Services

- **localstack**: A mock AWS environment that simulates S3 for storing documents.
- **aws-cli**: An AWS CLI container that uploads the mock files `document1.txt` and `document2.txt` to the local S3 bucket.
- **db**: PostgreSQL database for the project.

## How to Run

### Step 1: Run Docker Compose
Make sure you have Docker and Docker Compose installed on your machine.

Run the following command to bring up the required services (LocalStack, AWS CLI, and PostgreSQL):

```bash
docker-compose up
```
This will:

- Create the S3 bucket `my-local-bucket`.
- Upload mock documents from the `./s3-data` directory to the S3 bucket.
- Start a PostgreSQL database and initialize it with scripts in `init-scripts/init-db`.

**Note:** You need to wait until Docker Compose finishes setting up the environment before proceeding to the next step.

### Step 2: Build and Run the Application
Once Docker Compose is up and running, you can now build and run the Spring Boot application using Gradle.

#### Build the project:
```bash
./gradlew clean build
```
#### Run the application:
```bash
./gradlew bootRun
```

**Important:** Ensure that Docker Compose is running before executing Gradle commands. The application depends on services like LocalStack and PostgreSQL, which are set up by Docker Compose.  
If Docker Compose is not running, the application may fail to connect to the mock S3 bucket or the PostgreSQL database.

## Explanation of the Docker Compose Configuration

- **LocalStack**:
    - The S3 service is exposed on port `4566`.
    - Volumes are mounted to ensure that the mock files from `./s3-data` are uploaded when the services start.
    - The S3 bucket (`my-local-bucket`) is created, and the files are uploaded using the AWS CLI.

## PostgreSQL

- The database service runs on port `5432` and initializes with the scripts located in `init-scripts/init-db`.

## Files and Directories Used in Docker Compose

- `./s3-data`: Contains the mock files (`document1.txt` and `document2.txt`) that are uploaded to the LocalStack S3 bucket.
- `./init-scripts/init-db`: Contains SQL scripts that are run to initialize the PostgreSQL database (e.g., creating tables and inserting sample data).

## Gradle Build and Dependencies

This project uses Gradle for dependency management and building the project. Here's a summary of the key dependencies used:

### Key Dependencies

- **Spring Boot 3.2.5**: Core framework for building the REST API and managing services.
- **PostgreSQL Driver**: For connecting to the PostgreSQL database.
- **AWS SDK**: For interacting with S3 (via LocalStack in the development environment).
- **MapStruct**: Used for mapping domain objects to response DTOs.
- **Google Vertex AI Gemini**: Integrated for AI-driven tasks such as synonym generation.
- **Lombok**: For reducing boilerplate code like getters, setters, and constructors.

### Adding Dependencies

In the Gradle build files, dependencies are organized and shared across submodules using the `buildSrc` module. Key dependencies like `spring-boot`, `openapi`, `jackson`, `mapstruct`, and `aws-sdk` are included in `build.gradle`.

### Notes

- **LocalStack** is used to simulate AWS services locally, so no real AWS credentials are required.
- **PostgreSQL** database is set up with initialization scripts located in the `init-scripts` folder.
- **S3 Data**: The `s3-data` folder contains mock text files that are uploaded into a local S3 bucket for testing purposes.
