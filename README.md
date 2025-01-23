# Account Service Microservice

A Spring Boot microservice for managing customer accounts.

## Features
- Account creation service
- Customer inquiry service
- H2 in-memory database
- Java 8
- Spring Boot 2.6

## API Endpoints

### Create Account
POST /api/v1/account

### Get Customer Account
GET /api/v1/account/{customerNumber}

## Setup
1. Clone the repository
2. Run `mvn clean install`
3. Run `mvn spring-boot:run`

## Testing
Use Postman or any REST client to test the endpoints.
