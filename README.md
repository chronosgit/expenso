# Expenso

## Java REST API Expense Tracker

A secure, modern, and fully-featured Expense Tracker API built in Java. Track your spending, categorize expenses, and get insightful summaries—all via a RESTful API.

## Overview

A RESTful Expense Tracker API where users can:

- Register/login.
- Add, edit, delete, and list expenses.
- Categorize expenses (food, rent, entertainment, etc.).
- Get monthly/weekly expense summaries.

## Architecture & Layers

- Presentation Layer (Controller)
    - Handles REST API endpoints.
    - Accepts and returns JSON responses following JSON:API.
    - Implements HATEOAS links for navigable API responses.
- Service Layer
    - Business logic for expense management.
    - Handles authentication, validation, and security checks.
    - Communicates with repositories/DAO layer.
- Repository / DAO Layer
    - Manages database interactions via JDBC for PostgreSQL.
    - Optionally implement Redis caching for frequently accessed data like recent transactions or user sessions.
- Security Layer
    - JWT authentication & authorization.
    - Optional integration of RSA/ECDSA for JWT signing (cryptography practice).
    - Enforce HTTPS for network connections (TLS setup).
    - Follows HATEOAS principles and JSON:API standards for responses.

## Core Features

- User Management
    - Registration/Login with JWT.–
    - Password hashing (BCrypt) – introduces cryptography.–
    - Role-based authorization (User/Admin).
- Expense Management
    - CRUD operations for expenses.
    - Categorization and tagging.
    - Querying by date range, category, or amount.
- Reporting
    - Monthly/weekly expense summaries.
    - Top categories by spending.
    - Optional: Export reports in CSV/JSON.
- API Standards
    - HATEOAS: Include links for next/previous pages, related resources.
    - JSON:API: Standardize response objects, error handling.
- Networking & Security
    - HTTPS configuration for API server.
    - Optional: Network client in Java to fetch external exchange rates or budgets.
    - Secure storage of sensitive data (JWT, passwords, keys).

## Technologies

- Language: Java
- Build tool: Maven
- DBMS: PostgreSQL + JDBC
- Caching: Redis (optional, for performance)
- Logging: SLF4J + Logback
- Testing: JUnit5 + Mockito
- Containerization: Docker for DB and API
- Security: JWT, BCrypt, TLS, basic cryptography (AES/RSA)
- Build Tool: Maven