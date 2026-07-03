# 🍔 FoodG

A **production-grade Food Delivery Platform** inspired by applications like **Zomato** and **Swiggy**, built using **Spring Boot**, **Java 21**, **PostgreSQL**, and modern backend development practices.

FoodG is designed as a scalable backend system that manages the complete food ordering lifecycle—from user authentication to restaurant management, cart operations, ordering, and payments—while following clean architecture and RESTful API design.

---

## 🚀 Features

### ✅ Authentication & Authorization

* JWT-based Authentication
* Role-Based Access Control (RBAC)
* User Registration & Login
* Secure Password Encryption
* Refresh Token Support

---

### 👤 User Management

* User Profile Management
* Address Management
* Order History
* Favorite Restaurants *(Planned)*

---

### 🍽 Restaurant Management

* Restaurant Registration
* Restaurant Profile
* Restaurant Availability
* Restaurant Categories
* Restaurant Rating *(Planned)*

---

### 📋 Menu Management

* Create Menu Items
* Update Menu Items
* Delete Menu Items
* Category-wise Menus
* Item Availability

---

### 🛒 Cart Management

* Add Items to Cart
* Update Quantity
* Remove Items
* Calculate Total Amount
* Persistent Shopping Cart

---

### 📦 Order Management

* Place Orders
* Order Status Tracking
* Cancel Orders
* Order History
* Restaurant Order Dashboard

---

### 💳 Payment Module

* Payment Processing
* Payment Status Tracking
* Transaction Records
* Payment Verification

---

### 🚚 Delivery Module *(Planned)*

* Delivery Partner Assignment
* Live Delivery Tracking
* Delivery Status Updates
* ETA Calculation

---

### 🔔 Notification Module

* Notification API
* Notification Persistence
* Read/Unread Notifications

**Real-time WebSocket Notifications:** 🚧 Planned

---

### 📊 Analytics Module *(Planned)*

* Restaurant Sales Analytics
* Revenue Reports
* Popular Items
* Customer Insights
* Dashboard Metrics

---

### ⚡ WebSocket *(Planned)*

* Live Order Updates
* Real-time Notifications
* Delivery Tracking
* Restaurant Dashboard Updates

---

## 🛠 Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Spring Validation

### Database

* PostgreSQL

### Authentication

* JWT

### Build Tool

* Gradle

### Documentation

* OpenAPI / Swagger

### Utilities

* Lombok
* MapStruct

### Testing

* JUnit 5
* Mockito

---

## 🏗 Architecture

```text
                Client
                   │
             REST APIs
                   │
        Spring Boot Backend
                   │
 ┌──────────────────────────────────────┐
 │ Authentication Module                │
 │ User Module                          │
 │ Restaurant Module                    │
 │ Menu Module                          │
 │ Cart Module                          │
 │ Order Module                         │
 │ Payment Module                       │
 │ Notification Module                  │
 └──────────────────────────────────────┘
                   │
              PostgreSQL
```

---

## 📂 Project Structure

```text
src
├── controller
├── service
│   ├── impl
│   └── interfaces
├── repository
├── entity
├── dto
├── mapper
├── security
├── config
├── exception
├── util
└── validation
```

---

## ⚙ Getting Started

### Prerequisites

* Java 21
* PostgreSQL
* Gradle

### Clone the Repository

```bash
git clone https://github.com/itsdebashish/foodg-backend.git
cd foodg
```

### Configure Database

Update the database configuration in:

```text
src/main/resources/application.yml
```

### Run the Application

```bash
./gradlew bootRun
```

### Run Tests

```bash
./gradlew test
```

---

## 📖 API Documentation

Once the application is running, access Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🚧 Roadmap

* [x] Authentication & Authorization
* [x] User Management
* [x] Restaurant Module
* [x] Menu Module
* [x] Cart Module
* [x] Order Module
* [x] Payment Module
* [x] Notification API
* [ ] WebSocket Integration
* [ ] Real-time Notifications
* [ ] Delivery Management
* [ ] Analytics Dashboard
* [ ] Redis Caching
* [ ] Docker Support
* [ ] CI/CD Pipeline
* [ ] Kubernetes Deployment

---

## 📌 Project Goals

* Build a scalable backend architecture.
* Follow production-grade coding standards.
* Implement secure authentication and authorization.
* Apply clean architecture and SOLID principles.
* Support future scalability through modular design.
* Serve as a portfolio project demonstrating enterprise backend development.

---

## 🤝 Contributing

Contributions, suggestions, and improvements are welcome. Please create a feature branch, commit your changes with clear messages, and open a pull request for review.

---
