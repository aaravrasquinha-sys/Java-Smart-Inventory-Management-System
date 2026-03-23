# Java-Smart-Inventory-Management-System

A GUI-based Inventory Management System developed using **Java Swing** and **Object-Oriented Programming (OOP)** principles.
The system enables efficient inventory tracking, item management, and customer interaction in a structured and scalable way.

---

##  Features

###  Owner Functionalities

* Secure login system
* Add new inventory items
* Remove existing items
* Update item quantities
* View all inventory items
* Detect low-stock items
* Track expired items (for perishable goods)
* Monitor total revenue

###  Customer Functionalities

* Search for items
* Check availability
* Purchase items

---

##  Key Concepts Used

* Object-Oriented Programming (Inheritance, Abstraction, Encapsulation)
* Java Swing for GUI development
* Modular code structure (separation of GUI and core logic)
* Real-time inventory updates
* Exception handling (basic input validation)

---

##  Project Structure

```
java-inventory-management-system/
│
│   ├── gui/
│   │   └── InventoryGUI.java
│   │
│   ├── core/
│   │   ├── InventoryManager.java
│   │   ├── InventoryItem.java
│   │   ├── PerishableItem.java
│   │   └── Owner.java
│
├── README.md
├── .gitignore
```

---

##  How to Run

### 1. Compile the project

```
javac src/gui/InventoryGUI.java
```

### 2. Run the application

```
java gui.InventoryGUI
```

---

##  Future Improvements

* Database integration (MySQL / SQLite)
* REST API backend using Spring Boot
* Web-based frontend (React / Node.js)
* Barcode/QR code integration
* Authentication with encryption

---

##  Author

Aarav Rasquinha

---


