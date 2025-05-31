
# 🛒 E-Commerce Fake Store Interact with Email Notifications  

An e-commerce backend application built with **Java** and **Spring Boot**, designed to interact with the [Fake Store API](https://fakestoreapi.com/). This project provides RESTful endpoints to fetch product data, manage user interactions, and simulate e-commerce functionalities. It integrates with a separate [Email Service](https://github.com/ChaitanyaNadagouda/emailService) via **Apache Kafka** to handle email notifications asynchronously.

**A Spring Boot microservice that integrates with [Fake Store API](https://fakestoreapi.com/) and sends email notifications via Kafka.**  

[![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1%2B-brightgreen?logo=spring)](https://spring.io/)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-3.0%2B-black?logo=apachekafka)](https://kafka.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

---

## 🧱 Architecture Overview

The system follows a **microservices architecture** with two independent services:

### 1. **E-Commerce Service**
- Interacts with the Fake Store API.
- Handles product retrieval, order placement, and user data.
- Publishes email messages to Kafka for notification delivery.

### Set Environment Variables

username=${APP_USERNAME}  
password=${APP_PASSWORD}  

Through terminal or Integrated on any IDE like IntellijIdea Edit configurations Tab.

export APP_USERNAME="your_email@gmail.com"

export APP_PASSWORD="your_app_password"



### 2. **Email Service**
- Listens to Kafka topic `send email`.
- Sends email notifications using Gmail SMTP.


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

📚 API Documentation
E-Commerce Service Endpoints
Endpoint	Method	Description
/api/products	GET	Fetch all products
/api/products/{id}	GET	Get product by ID
/api/orders	POST	Place a new order (triggers Kafka email)

# E-Commerce Service 
cd E-Commerce_fakestoreinteract && mvn spring-boot:run

# Email Service 
cd emailService && mvn spring-boot:run

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


### 📌 Step 2: Set up Kafka

https://www.geeksforgeeks.org/installation-guide/how-to-install-and-run-apache-kafka-on-windows/


### 📌 Step 3: Build and Start
Make sure Your zookeeper and kafka both are up and even email service too.


Contributions are welcome!
Please fork the repository, create a feature branch, and submit a pull request with a clear description of your changes.


