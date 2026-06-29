# 📚 Elite Library Management System

A cloud-based Library Management System developed using **Spring Boot**, **Angular**, **MySQL**, and **AWS**.

---

## 🚀 Project Overview

This project is designed to manage a library digitally. It provides an easy way to manage students, seat bookings, and library administration through a modern web application.

The backend is developed using Spring Boot REST APIs, the frontend uses Angular, and the database is hosted on Amazon RDS. The application is deployed on AWS EC2.

---

## ✨ Features

- Student Registration
- Student Management
- Seat Booking
- Seat Availability
- Admin Dashboard
- Student Dashboard
- REST APIs
- MySQL Database
- Responsive UI

---

## 🛠 Tech Stack

### Frontend
- Angular
- TypeScript
- HTML
- CSS

### Backend
- Java
- Spring Boot
- Spring Data JPA
- REST API

### Database
- MySQL
- Amazon RDS

### Cloud
- AWS EC2
- Amazon RDS

---

## 🏗️ Project Architecture

User
↓
DuckDNS Domain
↓
Nginx Reverse Proxy
↓
Spring Boot Backend (AWS EC2)
↓
Amazon RDS (MySQL)

Monitoring:
CloudWatch → CloudWatch Alarm → Amazon SNS Email Notification


---

## ⚙️ Installation

### Backend

```bash
cd backend
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
ng serve
```

---

## ☁️ AWS Services Used

* Amazon EC2 – Hosted the Spring Boot backend
* Amazon RDS – Managed MySQL database
* Amazon CloudWatch – Monitored EC2 instance metrics
* CloudWatch Alarm – CPU utilization alert
* Amazon SNS – Email notifications for alarms
* Nginx – Reverse proxy configuration
* DuckDNS – Public domain access
* AWS Security Groups – Controlled inbound and outbound traffic


## 🔮 Future Enhancements

- Email Notifications
- QR Code Based Entry
- Online Payment
- Fine Management
- Attendance Reports

---

## 👨‍💻 Author

**Prashant Patil**

GitHub:
---

## 📸 Project Screenshots

### 🏠 Home Page

![Home Page](screenshots/homepage.png)

---

### 🪑 Seat Booking Page

![Seat Booking Page](screenshots/seat-booking.png)

---

### 📊 Live Seat Status

![Live Seat Status](screenshots/live-seat-status.png)

---

### 👨‍💼 Admin Dashboard

![Admin Dashboard](screenshots/admin-dashboard.png)

---

### 👨‍🎓 Student List

![Student List](screenshots/student-list.png)
