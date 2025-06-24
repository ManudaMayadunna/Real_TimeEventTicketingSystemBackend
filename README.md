---

# RealTimeEventTicketingBackend 🎟️

## Overview

This project is a **real-time event ticketing backend system** built with Java and Spring Boot. It is designed to handle event ticket management with multi-threaded processing, real-time updates through WebSockets, and RESTful API endpoints for system control and data management.

---

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot**

---

## 🚀 Features

✅ Multi-threaded ticket issuing using producer-consumer design
✅ JSON file generation for system logs and issued tickets
✅ Thread management endpoints (start, stop, reset)

---

## ⚙️ Prerequisites

* Java 17 or higher

---

## 📦 Installation

### 1️⃣ Clone the repository

```bash
git clone https://github.com/<your-username>/RealTimeEventTicketingBackend.git
cd RealTimeEventTicketingBackend
```

### 2️⃣ Configure database

Create a database in MySQL (e.g., `ticketing_db`).

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ticketing_db
spring.jpa.hibernate.ddl-auto=update
```

---

### 3️⃣ Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

The backend will start on:

```
http://localhost:9090
```

---

## 📡 API Endpoints

| Endpoint                    | Method | Description               |
| --------------------------- | ------ | ------------------------- |
| `/api/save/config`          | POST   | Save system configuration |
| `/api/save/vendor`          | POST   | Register a new vendor     |
| `/api/get/vendor`           | GET    | Get all vendors           |
| `/api/delete/vendor/{id}`   | DELETE | Delete vendor by ID       |
| `/api/save/customer`        | POST   | Register a new customer   |
| `/api/get/customer`         | GET    | Get all customers         |
| `/api/delete/customer/{id}` | DELETE | Delete customer by ID     |
| `/api/save/ticket`          | POST   | Issue a ticket            |
| `/api/get/tickets`          | GET    | List all issued tickets   |
| `/api/start`                | POST   | Start ticketing threads   |
| `/api/stop`                 | POST   | Stop ticketing threads    |
| `/api/reset`                | POST   | Reset the system          |

---

## 🔌 WebSocket

* URL: `ws://localhost:9090/ws-native`
* Sends real-time ticketing status and log updates.

---

## 📝 Usage Notes

✅ Start system → POST to `/api/start`
✅ Stop system → POST to `/api/stop`
✅ Download generated files:

* `tickets.json` — saved tickets
* `system_config_settings.json` — saved config

---

## 📈 Future Improvements

* Add authentication (e.g., Spring Security + JWT)
* Add pagination and filtering to ticket/customer/vendor APIs
* Dockerize the app
* Add unit/integration tests

---

## 🤝 Contribution

Feel free to fork, contribute, or raise issues!

---

## 📧 Contact

For inquiries:
`manudamayadunna@gmail.com`

---
