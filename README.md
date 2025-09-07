
# 🛍️ Dream Shops

[![Java](https://img.shields.io/badge/Java-17-red?style=for-the-badge\&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen?style=for-the-badge\&logo=springboot)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge\&logo=mysql)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.11.0-orange?style=for-the-badge\&logo=apachemaven)](https://maven.apache.org/)
[![Security](https://img.shields.io/badge/Security-JWT-yellow?style=for-the-badge\&logo=jsonwebtokens)](https://jwt.io/)

A **simple e-commerce demo application** built with **Spring Boot**, **MySQL**, **Spring Security (JWT)**, and **JPA**.
This project is created for practice purposes, implementing **authentication, authorization, CRUD operations, and JWT security**.

---

## 🚀 Features

* 🔐 User Authentication & Authorization (JWT)
* 👤 Role-based access (Admin / User)
* 📦 Product & Category Management
* 🛒 Shopping Cart & Orders
* ⭐ Reviews system
* 📂 Image upload support (Multipart)
* 🛠️ Global Exception Handling

---

## 🏗️ Tech Stack

* **Backend:** Spring Boot 3.5.4
* **Security:** Spring Security + JWT
* **Database:** MySQL
* **ORM:** Hibernate / JPA
* **Mapping:** ModelMapper
* **Build Tool:** Maven
* **Java Version:** 17

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the repository

```bash
git clone https://github.com/your-username/dream-shops.git
cd dream-shops
```

### 2️⃣ Configure Database

Create a new MySQL database:

```sql
CREATE DATABASE dream_shops_db;
```

Update `application.properties` with your DB credentials:

```properties
spring.datasource.username=your-username
spring.datasource.password=your-password
```

### 3️⃣ Run the project

Using Maven:

```bash
mvn spring-boot:run
```

Or in your IDE, run `DreamShopsApplication.java`.

The server will start on:
👉 `http://localhost:8888`

---

## 🔑 API Endpoints

| Method | Endpoint                | Description                  |
| ------ | ----------------------- | ---------------------------- |
| POST   | `/api/v1/auth/register` | Register a new user          |
| POST   | `/api/v1/auth/login`    | Authenticate and get JWT     |
| GET    | `/api/v1/products`      | Get all products             |
| POST   | `/api/v1/products`      | Add new product (Admin only) |
| GET    | `/api/v1/orders`        | Get user orders              |

---

## 🧪 Testing

Run unit tests:

```bash
mvn test
```

---

## 📜 License

This project is for learning purposes only. Free to use and modify.
