# Fullstack Medical Journaling System - Backend

## Overview
The **Fullstack Medical Journaling System** is a web application designed for managing patient information in a healthcare setting. It consists of a **Spring Boot backend** and a **React.js frontend**, with data stored in a **relational database (MySQL or PostgreSQL)**. The system is containerized using **Docker**, running at least three separate containers: **frontend, backend, and database**.

## Backend Repository
The backend is built with **Spring Boot** and provides a RESTful API that the frontend consumes. It follows a **three-tier architecture** with a clear separation of concerns:
- **Data Layer:** Handles database interactions using **JPA and Hibernate**.
- **Service Layer:** Contains business logic and processing.
- **Controller Layer:** Exposes REST endpoints for the frontend.

### Backend Features:
- User authentication and authorization with three roles: **Patient, Doctor, and Staff**.
- Management of **patients, observations, encounters, conditions, practitioners, and organizations**.
- Secure API endpoints using **Spring Security**.
- Optional integration with **HAPI FHIR** for external patient data retrieval.
- Containerized environment using **Docker**.

For backend setup and configuration, refer to the **backend repository README**.

## Frontend Repository
The frontend is built with **React.js** and serves as the interface for interacting with the system. It follows **component-based architecture** and consumes the backend’s REST API.

### Frontend Features:
- User authentication and role-based access.
- **Doctors and staff** can create patient notes and diagnoses.
- **Doctors** can access full patient records.
- **Patients** can view their own medical information.
- **Messaging system**: Patients can send messages to doctors/staff and receive replies.
- Responsive UI for various devices.

For frontend setup and configuration, refer to the **frontend repository README**.

## Technologies Used
### Backend:
- **Spring Boot** (Java)
- **Spring Security** (Authentication & Authorization)
- **JPA/Hibernate** (ORM for database interaction)
- **MySQL/PostgreSQL** (Relational database)
- **Docker** (Containerization)

### Frontend:
- **React.js** (Component-based UI framework)
- **React Router** (Navigation management)
- **Redux (Optional)** (State management)
- **Axios** (API communication)
- **Bootstrap/Material UI** (Styling and UI components)

## Deployment & Setup
### Prerequisites:
- **Docker** installed on your machine.
- **Java 17+** and **Node.js (latest LTS)** installed if running locally.

### Running with Docker:
1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/fullstack-medical-system.git
   cd fullstack-medical-system
   ```
2. **Start the application:**
   ```sh
   docker-compose up --build
   ```
3. **Access the system:**
   - **Frontend**: `http://localhost:3000`
   - **Backend API**: `http://localhost:8080/api`


---
**Note:** Ensure proper `.env` configuration for database credentials before running the application.

