# 💰 Fincore Dashboard API -Financial Transaction Management System (Spring Boot)

A robust backend system built using **Spring Boot** to manage financial transactions and provide **secure, role-based dashboard analytics**.

---

## 📌 Project Overview

This system enables efficient tracking and management of financial transactions with **secure authentication** and **role-based access control (RBAC)**.

### 🚀 Key Features
- 🔐 Secure JWT-based authentication
- 👥 Role-based access control (Admin, Analyst, Viewer)
- 💰 Transaction management system
- 📊 Dashboard analytics (income, expense, balance)
- 🧩 Modular monolithic architecture
- 🧼 Clean separation of concerns

---

## 🧱 Tech Stack

| Technology | Description |
|----------|------------|
| Java 21 | Core programming language |
| Spring Boot | Backend framework |
| Spring Web | REST API development |
| Spring Data JPA (Hibernate) | ORM & database interaction |
| MySQL | Relational database |
| Spring Security | Authentication & authorization |
| JWT | Token-based authentication |
| Lombok | Boilerplate reduction |

---

## 🏗️ Architecture
<img width="1000" height="1900" alt="diagram-export-4-6-2026-10_13_17-AM" src="https://github.com/user-attachments/assets/83b01237-acb8-47a5-a33c-7d6c43bc8a46" />

The project follows a **Layered Architecture**:
Controller Layer → Handles HTTP requests & responses
Service Layer → Business logic & RBAC enforcement
Repository Layer → Database interaction
Security Layer → JWT authentication & filters
DTO Layer → Request/Response abstraction


---

## 🔐 Authentication & Security

### 🔑 Features
- JWT-based authentication
- Stateless session management
- Custom `UserDetails` implementation
- BCrypt password encryption
- JWT request filtering

### 🔄 Authentication Flow

1. User logs in → JWT token generated
2. Token sent in `Authorization` header
3. JWT filter intercepts request
4. Token is validated
5. User details fetched from DB
6. Role-based access control applied

---

## 👥 User Roles & Permissions

| Role | Permissions |
|------|------------|
| **ADMIN** | Create, update, delete, and view all transactions + full dashboard |
| **ANALYST** | View all transactions + full dashboard analytics |
| **VIEWER** | View only personal dashboard summary |

---

## 💰 Transaction Management

### 📌 Fields
- `amount`
- `type` (INCOME / EXPENSE)
- `category`
- `date`
- `notes`
- `user` (Many-to-One relationship)

### ⚙️ Features
- ➕ Create transaction → Admin only
- 📄 View transactions → Admin & Analyst
- ✏️ Update transaction → Admin (with ownership validation)
- ❌ Delete transaction → Admin only

---

## 📊 Dashboard Analytics

Provides key financial insights:

- 💵 Total Income
- 💸 Total Expense
- 📈 Net Balance

### 🔍 Role-Based Logic
- **Viewer** → Only personal data
- **Admin / Analyst** → Aggregated data (all users)

---

## 🧠 Business Logic Highlights

- Role-based filtering implemented at **Service Layer**
- JWT does **NOT store roles** → roles fetched from DB
- Ownership validation before update/delete
- Clean DTO-based request/response design

---

## 🛡️ Validation & Error Handling

- ✅ Input validation using `@Valid`
- ⚠️ Runtime exception handling
- 🌐 Proper HTTP status codes (200, 201, 400, 401, 403, 404)

---

## 📡 API Endpoints

### 🔐 Authentication APIs

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | `/auth/register` | Register new user |
| POST | `/auth/login` | Login & receive JWT |

---

### 💰 Transaction APIs

| Method | Endpoint | Access | Description |
|--------|----------|--------|------------|
| POST | `/transactions` | Admin | Create transaction |
| GET | `/transactions` | Admin, Analyst | Get all transactions |
| PUT | `/transactions/{id}` | Admin | Update transaction |
| DELETE | `/transactions/{id}` | Admin | Delete transaction |

---

### 📊 Dashboard API

| Method | Endpoint | Description |
|--------|----------|------------|
| GET | `/dashboard/summary` | Get financial summary |

**Behavior:**
- Viewer → Personal summary  
- Admin/Analyst → Aggregated summary  

---

## 🔗 Key Design Decisions

- ✅ Single dashboard API with role-based filtering
- ✅ DTO separation from entity layer
- ✅ Enum usage for roles & transaction types
- ✅ Modular monolithic architecture
- ❌ Avoided redundant APIs

---

## 🚀 Future Enhancements

- 📊 Category-wise analytics
- 📅 Monthly trends
- 📄 Pagination for transactions
- 📘 Swagger API documentation
- 🌍 Global exception handling
- ☁️ AWS EC2 deployment

---

## ▶️ How to Run the Project

### 📌 Prerequisites
- Java 21
- Maven
- MySQL

### ⚙️ Setup Steps

```bash
# Clone repository
git clone https://github.com/your-username/your-repo-name.git

# Navigate to project
cd your-repo-name

# Configure database
Update application.properties with your MySQL credentials

# Build project
mvn clean install

# Run application
mvn spring-boot:run
