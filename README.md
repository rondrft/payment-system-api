# Payment System API 💸

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

---

## 🚀 Overview

**Payment System API** is a backend REST API inspired by real-world payment platforms like Mercado Pago. Built with a focus on clean architecture, scalability, and professional development practices.

The goal of this project is to deeply understand how production-grade financial systems work — from layered architecture and data modeling to concurrency control and error handling.

---

## 🏗️ Architecture

The project follows a **layered architecture** with clear separation of concerns:

```
controller/     → Receives HTTP requests and returns responses
service/        → Business logic lives here
repository/     → Database access layer
model/          → JPA entities mapped to database tables
dto/            → Data Transfer Objects (request and response)
exception/      → Custom exceptions and global error handling
```

---

## ✅ Current Features

### 💳 Account Management
- Create accounts with name, email and initial balance
- Input validation with meaningful error messages
- Duplicate email detection

### 💸 Transfers
- Transfer funds between accounts
- Pessimistic locking to prevent race conditions in concurrent transfers
- Insufficient funds validation
- Full transaction record with sender, receiver, amount, status and timestamp

### 📄 Transaction History
- Paginated transaction history per account
- Bidirectional query (shows both sent and received transfers)
- Sorted by most recent first

### 🚨 Error Handling
- Global exception handler with structured JSON error responses
- Custom exceptions per business rule (AccountNotFoundException, DuplicateEmailException, InsufficientFundsException)
- Proper HTTP status codes (404, 409, 422, 400)

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Validation | Bean Validation (Jakarta) |
| Boilerplate reduction | Lombok |

---

## 📈 Roadmap

### ✅ Done
- [x] Project structure and layered architecture
- [x] Account and Transaction entities
- [x] DTOs with validation
- [x] Repositories with pagination and pessimistic locking
- [x] Custom exceptions and global error handler

### 🔄 In Progress
- [ ] Service layer (business logic)
- [ ] Controller layer (REST endpoints)

### 🎯 Planned
- [ ] Spring Security with JWT authentication
- [ ] Docker containerization
- [ ] Unit and integration testing (JUnit 5)
- [ ] API documentation (Swagger / OpenAPI)

---

## 🤝 About

This project is part of my journey returning to backend development after a break, rebuilding and deepening my understanding of professional Java/Spring Boot architecture — not just memorizing syntax, but truly understanding why each decision is made.

---

🔗 **LinkedIn**: https://www.linkedin.com/in/rondrft/

📧 **Email**: rondrft@gmail.com

🐙 **GitHub**: https://github.com/rondrft