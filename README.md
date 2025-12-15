# 🏦 Modern Banking System - DevOps Project

<<<<<<< HEAD
<<<<<<< HEAD
A complete, production-ready banking web application (FINAL VERSION)
=======
A complete, production-ready banking web application (EXPERIMENT CONFLICT VERSION)
>>>>>>> experiment
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-11+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.x-brightgreen.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Docker Deployment](#docker-deployment)
- [CI/CD Pipeline](#cicd-pipeline)
- [Database Schema](#database-schema)
- [Testing](#testing)
- [Contributing](#contributing)

## ✨ Features

### Backend Features
- ✅ User Registration & Authentication
- ✅ Multiple Account Management per User
- ✅ Deposit, Withdraw, and Transfer Operations
- ✅ Transaction History Tracking
- ✅ RESTful API Architecture
- ✅ Clean OOP Design (Controller-Service-Repository Pattern)
- ✅ Exception Handling & Validation
- ✅ H2 for Development, MySQL for Production

### Frontend Features
- ✅ Modern, Responsive UI with Bootstrap 5
- ✅ Login & Registration Pages
- ✅ Interactive Dashboard
- ✅ Account Creation & Management
- ✅ Transaction Operations (Deposit/Withdraw/Transfer)
- ✅ Real-time Balance Updates
- ✅ Transaction History View

### DevOps Features
- ✅ Docker Containerization
- ✅ Docker Compose for Multi-Container Setup
- ✅ GitHub Actions CI/CD Pipeline
- ✅ Jenkins Pipeline Support
- ✅ Automated Testing
- ✅ Code Quality Checks

## 🛠️ Technology Stack

### Backend
- **Language:** Java 11+
- **Framework:** Spring Boot 2.7.x
- **ORM:** Spring Data JPA / Hibernate
- **Database:** H2 (Development), MySQL 8.0 (Production)
- **Build Tool:** Maven 3.6+
- **API:** RESTful Web Services

### Frontend
- **HTML5** - Structure
- **CSS3** - Styling
- **Bootstrap 5.3** - UI Framework
- **Vanilla JavaScript** - Logic & API Integration
- **Font Awesome** - Icons
- **Google Fonts** - Typography

### DevOps
- **Containerization:** Docker
- **Orchestration:** Docker Compose
- **CI/CD:** GitHub Actions, Jenkins
- **Version Control:** Git & GitHub

## 📁 Project Structure

```
modern-banking-system/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/banking/
│   │   │   │   ├── BankingApplication.java
│   │   │   │   ├── controller/
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   ├── AccountController.java
│   │   │   │   │   └── TransactionController.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── UserService.java
│   │   │   │   │   ├── AccountService.java
│   │   │   │   │   └── TransactionService.java
│   │   │   │   ├── repository/
│   │   │   │   ├── model/
│   │   │   │   ├── dto/
│   │   │   │   ├── exception/
│   │   │   │   └── config/
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       ├── application-dev.properties
│   │   │       └── application-prod.properties
│   │   └── test/
│   └── pom.xml
├── frontend/
│   ├── index.html
│   ├── dashboard.html
│   ├── css/
│   │   └── style.css
│   └── js/
│       ├── config.js
│       ├── auth.js
│       └── dashboard.js
├── database/
│   └── schema.sql
├── docker/
│   ├── Dockerfile
│   └── docker-compose.yml
├── .github/
│   └── workflows/
│       └── ci-cd.yml
├── Jenkinsfile
├── .gitignore
└── README.md
```

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK 11 or higher** - [Download](https://adoptium.net/)
- **Maven 3.6+** - [Download](https://maven.apache.org/download.cgi)
- **MySQL 8.0+** (for production) - [Download](https://dev.mysql.com/downloads/)
- **Git** - [Download](https://git-scm.com/downloads)
- **Docker** (optional) - [Download](https://www.docker.com/products/docker-desktop)

### Verify Installation

```bash
java -version
mvn -version
git --version
docker --version  # Optional
```

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/modern-banking-system.git
cd modern-banking-system
```

### 2. Configure Database

#### For Development (H2 - No Setup Required)
The application uses H2 in-memory database by default. No additional configuration needed.

#### For Production (MySQL)

1. Create the database:
```bash
mysql -u root -p
CREATE DATABASE banking_system;
exit;
```

2. Run the schema:
```bash
mysql -u root -p banking_system < database/schema.sql
```

3. Update `backend/src/main/resources/application-prod.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_system
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the Backend

```bash
cd backend
mvn clean install
```

## ▶️ Running the Application

### Option 1: Run Locally (Development Mode)

#### Start Backend:
```bash
cd backend
mvn spring-boot:run
```

The backend will start at `http://localhost:8080`

#### Open Frontend:
Simply open `frontend/index.html` in your web browser, or use a local server:

```bash
cd frontend
# Using Python
python -m http.server 3000

# Using Node.js http-server
npx http-server -p 3000
```

Access the application at `http://localhost:3000`

### Option 2: Run with Docker

#### Build and Run with Docker Compose:
```bash
docker-compose -f docker/docker-compose.yml up -d
```

This will start:
- MySQL database on port 3306
- Backend on port 8080
- Nginx (frontend) on port 80

Access the application at `http://localhost`

#### Stop the containers:
```bash
docker-compose -f docker/docker-compose.yml down
```

### Option 3: Production Deployment

```bash
cd backend
mvn clean package -DskipTests
java -jar target/banking-system.jar --spring.profiles.active=prod
```

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123"
}
```

#### Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

### Account Endpoints

#### Create Account
```http
POST /api/accounts/create
Content-Type: application/json

{
  "holderName": "John Doe",
  "initialBalance": 1000.00,
  "phone": "1234567890",
  "address": "123 Main St",
  "dateOfBirth": "1990-01-01",
  "userId": 1
}
```

#### Get User Accounts
```http
GET /api/accounts/user/{userId}
```

#### Get Account Balance
```http
GET /api/accounts/{accountNumber}/balance
```

### Transaction Endpoints

#### Deposit Money
```http
POST /api/transactions/deposit
Content-Type: application/json

{
  "accountNumber": "123456789012",
  "amount": 500.00,
  "description": "Salary deposit"
}
```

#### Withdraw Money
```http
POST /api/transactions/withdraw
Content-Type: application/json

{
  "accountNumber": "123456789012",
  "amount": 200.00,
  "description": "ATM withdrawal"
}
```

#### Transfer Money
```http
POST /api/transactions/transfer
Content-Type: application/json

{
  "accountNumber": "123456789012",
  "toAccountNumber": "987654321098",
  "amount": 300.00,
  "description": "Payment"
}
```

#### Get Transaction History
```http
GET /api/transactions/account/{accountNumber}
```

## 🐳 Docker Deployment

### Build Docker Image
```bash
docker build -f docker/Dockerfile -t modern-banking-system:latest .
```

### Run Docker Container
```bash
docker run -p 8080:8080 modern-banking-system:latest
```

### Using Docker Compose (Recommended)
```bash
# Start all services
docker-compose -f docker/docker-compose.yml up -d

# View logs
docker-compose -f docker/docker-compose.yml logs -f

# Stop all services
docker-compose -f docker/docker-compose.yml down

# Rebuild and restart
docker-compose -f docker/docker-compose.yml up -d --build
```

## 🔄 CI/CD Pipeline

### GitHub Actions

The project includes a GitHub Actions workflow (`.github/workflows/ci-cd.yml`) that automatically:

1. ✅ Builds the project with Maven
2. ✅ Runs unit tests
3. ✅ Performs code quality checks
4. ✅ Builds Docker image
5. ✅ Pushes to Docker Hub (on main branch)
6. ✅ Runs security scans

**Setup:**
1. Add secrets to GitHub repository:
   - `DOCKERHUB_USERNAME`
   - `DOCKERHUB_TOKEN`

### Jenkins Pipeline

The `Jenkinsfile` provides a complete pipeline with stages:

1. Checkout
2. Build
3. Test
4. Package
5. Code Quality Analysis
6. Docker Build
7. Docker Push
8. Deploy

**Setup:**
1. Create a new Pipeline job in Jenkins
2. Point to this repository
3. Add Docker Hub credentials with ID: `dockerhub-credentials`

## 🗄️ Database Schema

### Users Table
- `id` (Primary Key)
- `username` (Unique)
- `email` (Unique)
- `password` (Hashed)
- `created_at`

### Accounts Table
- `id` (Primary Key)
- `account_number` (Unique)
- `holder_name`
- `balance`
- `phone`
- `address`
- `date_of_birth`
- `user_id` (Foreign Key → users)
- `created_at`

### Transactions Table
- `id` (Primary Key)
- `type` (DEPOSIT, WITHDRAW, TRANSFER)
- `amount`
- `from_account`
- `to_account`
- `status` (SUCCESS, FAILED, PENDING)
- `transaction_date`
- `description`

## 🧪 Testing

### Run Unit Tests
```bash
cd backend
mvn test
- Manual API test scenarios added for account and transaction endpoints
```

### Run Tests with Coverage
```bash
mvn test jacoco:report
```

### H2 Console (Development)
Access at: `http://localhost:8080/h2-console`

- **JDBC URL:** `jdbc:h2:mem:bankingdb`
- **Username:** `sa`
- **Password:** (leave empty)

## 🎯 Future Enhancements

- [ ] JWT Authentication
- [ ] Email Notifications
- [ ] Password Reset Functionality
- [ ] Account Statements (PDF)
- [ ] Admin Dashboard
- [ ] Kubernetes Deployment
- [ ] Prometheus & Grafana Monitoring
- [ ] Rate Limiting
- [ ] Two-Factor Authentication

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com

## 🙏 Acknowledgments

- Spring Boot Documentation
- Bootstrap Framework
- Docker Documentation
- DevOps Community

---

## 📸 Screenshots
- Git branch creation
- Commit history graph
- Merge operations
- Merge conflict and resolution
- GitHub repository overview

## 🧠 Challenges Faced
- Understanding branch workflows
- Handling merge conflicts
- Resolving conflicts using Git Bash

## ✅ Conclusion
This project helped me gain hands-on experience with Git Bash and GitHub, including branching, merging, conflict resolution, and remote repository management.


⭐ **Star this repository if you find it helpful!**

## 📞 Support

For support, email your.email@example.com or create an issue in this repository.
