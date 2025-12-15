# 🚀 Quick Start Guide - Modern Banking System

## ⚡ Fastest Way to Run

### 1️⃣ **Run Backend (Terminal 1)**
```bash
cd C:\Users\VANSH\.gemini\antigravity\scratch\modern-banking-system\backend
mvn spring-boot:run
```

Wait for: `Modern Banking System is running!`

### 2️⃣ **Open Frontend (Browser)**
Open this file in your browser:
```
C:\Users\VANSH\.gemini\antigravity\scratch\modern-banking-system\frontend\index.html
```

**OR** using Python (Terminal 2):
```bash
cd C:\Users\VANSH\.gemini\antigravity\scratch\modern-banking-system\frontend
python -m http.server 3000
```
Then open: `http://localhost:3000`

## 🎯 Quick Test Flow

1. **Register**: Create account with username, email, password
2. **Login**: Use your credentials
3. **Create Account**: Fill form with account details
4. **Deposit**: Add money to your account
5. **Withdraw**: Remove money from account
6. **Transfer**: Send money to another account
7. **History**: View all transactions

## 🐳 Docker Quick Start (Alternative)

```bash
cd C:\Users\VANSH\.gemini\antigravity\scratch\modern-banking-system\docker
docker-compose up -d
```

Access: `http://localhost`

## 📡 API Endpoints Reference

**Base URL**: `http://localhost:8080/api`

### Register
```bash
POST /auth/register
{
  "username": "john",
  "email": "john@test.com",
  "password": "pass123"
}
```

### Login
```bash
POST /auth/login
{
  "username": "john",
  "password": "pass123"
}
```

### Create Account
```bash
POST /accounts/create
{
  "holderName": "John Doe",
  "initialBalance": 1000,
  "phone": "1234567890",
  "address": "123 Main St",
  "dateOfBirth": "1990-01-01",
  "userId": 1
}
```

### Deposit
```bash
POST /transactions/deposit
{
  "accountNumber": "123456789012",
  "amount": 500
}
```

## 🔧 Troubleshooting

**Backend won't start?**
- Check Java version: `java -version` (need 11+)
- Check Maven: `mvn -version`
- Check port 8080 is free

**Frontend can't connect?**
- Ensure backend is running
- Check browser console for errors
- Verify API_BASE_URL in `js/config.js`

**Database issues?**
- H2 in-memory DB works out of the box
- For MySQL, run `database/schema.sql`

## 📊 H2 Console (Development)

URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:bankingdb`
- Username: `sa`
- Password: (empty)

## 🎓 For Your DevOps Project

**Show these to your instructor:**
1. ✅ Complete project structure
2. ✅ Running application (frontend + backend)
3. ✅ Docker containerization
4. ✅ CI/CD pipeline files
5. ✅ Clean code architecture
6. ✅ API documentation in README
7. ✅ Database schema

## 📁 Project Location

```
C:\Users\VANSH\.gemini\antigravity\scratch\modern-banking-system\
```

**Recommended**: Set this as your active workspace in your IDE

## 🎨 Key Features to Demonstrate

1. **User Registration & Login** - Authentication system
2. **Multiple Accounts** - One user, many accounts
3. **Transactions** - Deposit, Withdraw, Transfer
4. **Transaction History** - Complete audit trail
5. **Balance Validation** - Can't withdraw more than balance
6. **Responsive UI** - Works on all devices
7. **REST APIs** - Clean API design
8. **Docker Ready** - One command deployment
9. **CI/CD Pipelines** - Automated testing & deployment

## 💡 Quick Commands Cheatsheet

```bash
# Build project
mvn clean install

# Run tests
mvn test

# Package JAR
mvn clean package

# Run JAR
java -jar target/banking-system.jar

# Docker build
docker build -f docker/Dockerfile -t banking-system .

# Docker run
docker run -p 8080:8080 banking-system

# Docker Compose
docker-compose -f docker/docker-compose.yml up -d

# Git init
git init
git add .
git commit -m "Initial commit"
```

---

**Need help?** Check the full [README.md](../modern-banking-system/README.md) for detailed documentation.

**Good luck with your DevOps project! 🚀**
