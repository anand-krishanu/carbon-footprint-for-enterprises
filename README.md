# Carbon Footprint Tracker for Enterprises

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A robust Spring Boot application for tracking organizational carbon emissions with role-based access control and reporting features.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Quick Start](#quick-start)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [API Documentation](#api-documentation)
  - [Authentication](#authentication)
  - [Emission Records](#emission-records)
  - [Departments](#departments)
  - [Reports](#reports)
- [Example Requests](#example-requests)
- [Role-Based Access](#role-based-access)
- [Future Enhancements](#future-enhancements)
- [License](#license)

## Features

- **Department & Emission Management**  
  CRUD operations for emissions and departments
- **Role-Based Access Control**  
  Only `MANAGER` roles can modify data
- **JWT Authentication**  
  Secure API access using JSON Web Tokens
- **Reporting**:
  - PDF reports of carbon emissions
  - JSON summary of annual CO₂ emissions
- **Spring AOP**  
  Logging for critical business operations
- **Multi-Database Support**  
  Works with both MySQL and H2

## Technologies

- **Backend**: Java 17, Spring Boot 3.x
- **Security**: Spring Security, JWT
- **Database**: MySQL/H2, Spring Data JPA
- **Reporting**: iText PDF
- **Tools**: Lombok, Spring AOP

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+ (optional)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/carbon-footprint-tracker.git
   cd carbon-footprint-tracker
   ```

2. Configure Database:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/carbon_footprint
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update  
   ```
3. Run The application
  ```bash
  ./mvnw spring-boot:run
  ```
