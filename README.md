# Kirana Transactions Management System

### A Spring Boot-based application for managing transactions in Kirana stores, built with MongoDB, JWT Authentication, and REST APIs.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technology Stack](#technology-stack)
4. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
5. [Project Structure](#project-structure)
6. [API Endpoints](#api-endpoints)
    - [Authentication Endpoints](#authentication-endpoints)
    - [Transaction Endpoints](#transaction-endpoints)
    - [Reporting Endpoints](#reporting-endpoints)
7. [JWT Authentication](#jwt-authentication)
8. [Rate Limiting](#rate-limiting)
9. [Caching](#caching)
10. [Future Enhancements](#future-enhancements)
11. [License](#license)

---

## Project Overview

The **Kirana Transactions Management System** is designed to manage day-to-day transactions of small Kirana (grocery) stores. The project is built using **Spring Boot** with a **MongoDB** database for scalability. It includes secure JWT-based authentication and supports APIs for registering users, processing transactions, and generating reports.

The system allows store owners to:
- Create and manage users.
- Record transactions (both credit and debit).
- Track and manage their store's transaction history.
- Generate reports based on transaction history.
- Generate the report of total Debit, total Credit and the total netflow within specific period.


---

## Features

- **User Management**: Register, login, and authenticate users with JWT tokens.
- **Transaction Management**: Record debit and credit transactions for users.
- **JWT Authentication**: Secure every route using JWT (JSON Web Tokens).
- **Rate Limiting**: Implement rate limiting for specific APIs to prevent abuse.
- **Caching**: Optimize performance by caching currency conversion rates.
- **RESTful APIs**: Expose RESTful endpoints for interaction with the system.
- **Flexible Transaction Types**: Support for different types of transactions using ENUM.
- **MongoDB Integration**: High-performance and scalable document database.

---

## Technology Stack

- **Java 21**: Latest features of Java 21.
- **Spring Boot 3.3.3**: For rapid development and production-ready applications.
- **MongoDB**: NoSQL database for document-based data storage.
- **JWT (JSON Web Tokens)**: Secure token-based authentication.
- **Bucket4j**: For rate limiting to protect against abuse.
- **Lombok**: To reduce boilerplate code.
- **Maven**: Dependency management and project build.
- **Logback**: For logging and application monitoring.

---

## Getting Started

### Prerequisites

- **Java 21** installed on your machine.
- **MongoDB** installed locally or on a cloud provider.
- **Maven** for dependency management.

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/kirana-transactions.git
    cd kirana-transactions
    ```

2. **Build the project**:
    ```bash
    mvn clean install
    ```

3. **Configure MongoDB connection** in `application.properties`:
   ```properties
    spring.data.mongodb.uri=<Your MongoDB connection String>
    ```
   - For testing purposes you can use the url `mongodb+srv://chitluridevicharan:charan@cluster0.qgrpql7.mongodb.net/KIRANA_TRANSACTIONS?retryWrites=true&w=majority&appName=Cluster0
     `
    

4. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

---

## Project Structure

```
src/
│
├── main/
│   ├── java/
│   │   └── com/
│   │       └── kiranaservices/
│   │           └── kirana_transactions/
│   │               ├── config/          # Configuration classes (JWT, Rate Limiting, etc.)
│   │               ├── controller/      # REST controllers for handling API requests
│   │               ├── model/           # Model classes (User, Transaction)
│   │               ├── repository/      # MongoDB repositories for data access
│   │               ├── service/         # Business logic services
│   │               ├── enum/            # Enums for the type of transactions.
│   │               ├── util/            # JwtUTil for creating and verifying the auth Token.
│   │               └── KiranaTransactionsApplication.java # Main entry point
│   └── resources/
│       ├── application.properties       # Application configuration
│       └── static/                      # Static resources (if any)
│
└── test/                                # Unit tests
```

---

## API Endpoints


### Postman Documentation

- Here is the link to the detailed documentation of all the end points of this application.
- https://web.postman.co/workspace/0d5822ce-8563-494b-bb36-3c2386723824/collection/33533288-93bf452b-c265-443e-ba95-4f86a1180974
---

## JWT Authentication

The system uses **JWT** for securing API endpoints. The authentication process involves:

1. **Register** a new user or **login** an existing user.
2. Upon successful authentication, a JWT token is returned.
3. This token should be included in the `Authorization` header for all subsequent API requests:

   ```
   Authorization: Bearer <token>
   ```

4. The JWT token is verified on every request to secured endpoints.

The `JwtAuthFilter` is implemented globally but can be applied to specific routes as needed.

---

## Rate Limiting

The project integrates **Bucket4j** for rate limiting. This prevents abuse by limiting the number of requests to certain endpoints.

Only 10 requests can be made per minute.

You can customize the rate-limiting policy in the `AppConfig` class.

---

## Caching

To enhance performance, caching can be enabled for frequently accessed data, such as transaction reports.

In here caching was implemented as to keep the currency conversion rates of different currency types, and the cache is refreshed for every 1 hour as the currency rates are volatile.

Currently, the project uses **Spring Boot's Caching** abstraction for managing cache, which can be configured further based on requirements.

---

## Future Enhancements

- **Role-based access control**: Add roles like `admin`, `user` for better management.
- **Pagination**: Add pagination for transaction history API.
- **Graphical Reporting**: Provide graphical reports of transactions (monthly, yearly, etc.).
- **Integration with external payment services**: Allow payment integration for real-time transactions.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contributing

We welcome contributions! 
