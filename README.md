# 🚀 SnappyShop Recommender

A microservices-based product recommendation system simulating a real-world e-commerce scenario using Spring Boot. This version demonstrates REST-based inter-service communication to observe latency under load,
with plans to optimize it using gRPC in the next phase.

---

## 🧠 Problem Statement

In modern microservices, REST APIs can introduce latency due to HTTP overhead and JSON parsing. This project demonstrates how latency affects user-facing systems when two services interact using REST,
and serves as a baseline for future optimization using gRPC.

---

## 📌 Project Goals

- Build two Spring Boot microservices:
  - **User Activity Service**: Exposes REST endpoint and calls the recommendation service.
  - **Recommendation Engine Service**: Returns mock product recommendations.
- Simulate latency in the recommendation response.
- Log and measure end-to-end response time.
- Perform load testing using JMeter or similar tools.
- Later replace REST with **gRPC** to observe performance improvements.

---

## 🧩 Architecture


[User Activity Service]  --(REST HTTP call)-->  [Recommendation Engine Service]
          ↑                                                ↑
    REST API (8080)                                   REST API (8081)


