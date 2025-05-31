# 🛒 E-Commerce Fake Store Interact

An e-commerce backend application built with **Java** and **Spring Boot**, designed to interact with the [Fake Store API](https://fakestoreapi.com/). This project provides RESTful endpoints to fetch product data, manage user interactions, and simulate e-commerce functionalities. It integrates with a separate [Email Service](https://github.com/ChaitanyaNadagouda/emailService) via **Apache Kafka** to handle email notifications asynchronously.

---

## 🧱 Architecture Overview

The system follows a **microservices architecture** with two independent services:

### 1. **E-Commerce Service**
- Interacts with the Fake Store API.
- Handles product retrieval, order placement, and user data.
- Publishes email messages to Kafka for notification delivery.

### 2. **Email Service**
- Listens to Kafka topic `sendEmail`.
- Sends email notifications using Gmail SMTP.

### 3. *Project Structure**

E-Commerce_fakestoreinteract/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── emailservice/
│       │           ├── controllers/
│       │           ├── services/
│       │           ├── models/
│       │           └── Application.java
│       └── resources/
│           └── application.properties
├── pom.xml

emailService/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── emailservice/
│       │           ├── consumers/
│       │           ├── dtos/
│       │           ├── utilities/
│       │           └── EmailServiceApplication.java
│       └── resources/
│           └── application.properties
├── pom.xml


---

## 🚀 Features

- **Product Retrieval**: Fetch product listings and details from the Fake Store API.
- **User Management**: Handle user data and interactions.
- **Order Processing**: Simulate order placements and track order history.
- **Email Notifications**: Send transactional emails using Gmail SMTP through an external email service.
- **Kafka Integration**: Utilize Apache Kafka for asynchronous communication between services.
- **Configuration Management**: Externalize configurations using environment variables.

---

## 🛠 Technologies Used

- Java 17  
- Spring Boot  
- Apache Kafka  
- Gmail SMTP  
- Maven  
- Fake Store API  

## ⚙️ Getting Started

### ✅ Prerequisites

- Java 17 or higher
- Maven 3.6+
- Apache Kafka (running on `localhost:9092`)
- Gmail account for SMTP (Enable "App Password" or "Less secure apps")

---

### 📦 Installation & Setup

#### Step 1: Clone the Repositories

git clone https://github.com/ChaitanyaNadagouda/E-Commerce_fakestoreinteract.git
git clone https://github.com/ChaitanyaNadagouda/emailService.git


### 📌 Step 2: Set Environment Variables

Configure the following variables for the **Email Service** to authenticate Gmail SMTP:

export APP_USERNAME=your_email@gmail.com
export APP_PASSWORD=your_app_password

### 📌 Step 3: Set up Kafka

https://www.geeksforgeeks.org/installation-guide/how-to-install-and-run-apache-kafka-on-windows/


### 📌 Step 4: Build and Start
Make sure Your zookeeper and kafka both are up and even email service too.


Contributions are welcome!
Please fork the repository, create a feature branch, and submit a pull request with a clear description of your changes.


