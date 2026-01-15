# Numerology Application

A comprehensive Java Swing-based desktop application for generating personalized numerology reports. The application follows SOLID principles and uses MySQL for backend data storage.

## Table of Contents

1. [Features](#features)
2. [Architecture](#architecture)
3. [SOLID Principles Implementation](#solid-principles-implementation)
4. [Project Structure](#project-structure)
5. [Setup Instructions](#setup-instructions)
6. [Usage Guide](#usage-guide)
7. [Database Schema](#database-schema)
8. [Technologies Used](#technologies-used)

## Features

1. **Loading/Splash Screen**: Beautiful splash screen with progress bar that navigates to login/signup
2. **User Authentication**: 
   - Signup with validation (username uniqueness, password confirmation)
   - Login with validation (users must signup first)
   - Secure password handling
3. **Dashboard**: Personalized dashboard showing numerology insights overview
4. **Numerology Calculator**: 
   - Input username and date of birth
   - Calculate Life Path Number, Destiny Number, Soul Number, and Personality Number
5. **PDF Report Generation**: 
   - Generate comprehensive numerology reports in PDF format
   - Includes insights for Career, Relationships, Health, Money, and Characteristics
   - Downloadable reports

## Architecture

The application follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────┐
│           Presentation Layer            │
│  (UI Components - Swing)                │
│  - SplashScreen                         │
│  - Login                                │
│  - Signup                               │
│  - Dashboard                            │
│  - NumerologyMainPage                   │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│            Service Layer                 │
│  (Business Logic)                        │
│  - NumberCalculator                     │
│  - NumerologyInterpretationService      │
│  - PDFGeneratorService                  │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│            Data Access Layer             │
│  (DAO - Database Operations)            │
│  - DB (Connection Manager)              │
│  - UserDao                              │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│            Data Transfer Objects         │
│  (DTOs)                                  │
│  - UserDTO                               │
│  - NumerologyDTO                         │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│            Database Layer                │
│  (MySQL)                                 │
│  - numerologydb                          │
│  - users table                           │
│  - numerology_reports table              │
└─────────────────────────────────────────┘
```

### Component Responsibilities

1. **UI Layer**: Handles user interactions, input validation, and display
2. **Service Layer**: Contains business logic for numerology calculations and interpretations
3. **DAO Layer**: Manages database operations (CRUD)
4. **DTO Layer**: Transfers data between layers
5. **Database Layer**: Stores user data and reports

## SOLID Principles Implementation

### 1. Single Responsibility Principle (SRP)

Each class has a single, well-defined responsibility:

- **`NumberCalculator`**: Only calculates numerology numbers from dates and names
- **`NumerologyInterpretationService`**: Only provides interpretations for numbers
- **`PDFGeneratorService`**: Only generates PDF reports
- **`UserDao`**: Only handles user-related database operations
- **`DB`**: Only manages database connections

### 2. Open/Closed Principle (OCP)

- **`NumerologyInterpretationService`**: Can be extended with new interpretations without modifying existing code
- Service interfaces can be extended without changing core functionality
- New numerology calculation methods can be added without modifying existing ones

### 3. Liskov Substitution Principle (LSP)

- DTOs can be substituted with their implementations
- Service classes can be replaced with alternative implementations if interfaces are used
- Database connection can be substituted with different implementations

### 4. Interface Segregation Principle (ISP)

- Services are separated into focused interfaces
- `NumberCalculator` focuses only on calculations
- `NumerologyInterpretationService` focuses only on interpretations
- No client is forced to depend on methods it doesn't use

### 5. Dependency Inversion Principle (DIP)

- **`PDFGeneratorService`** depends on `NumerologyInterpretationService` abstraction (constructor injection)
- High-level modules (UI) depend on service abstractions, not concrete implementations
- Database operations are abstracted through DAO layer

## Project Structure

```
booking_app/
├── src/
│   ├── App.java                          # Main entry point
│   └── com/
│       └── my_example/
│           ├── dao/                      # Data Access Objects
│           │   ├── DB.java               # Database connection manager
│           │   └── UserDao.java         # User database operations
│           ├── dto/                      # Data Transfer Objects
│           │   ├── UserDTO.java         # User data transfer
│           │   └── NumerologyDTO.java   # Numerology data transfer
│           ├── service/                  # Business Logic Services
│           │   ├── NumberCalculator.java
│           │   ├── NumerologyInterpretationService.java
│           │   └── PDFGeneratorService.java
│           └── ui/                       # User Interface Components
│               ├── SplashScreen.java     # Loading screen
│               ├── Login.java            # Login page
│               ├── Signup.java           # Signup page
│               ├── Dashboard.java        # Dashboard page
│               └── NumerologyMainPage.java  # Main numerology calculator
├── lib/
│   └── mysql-connector-j-9.5.0.jar      # MySQL JDBC driver
├── images/
│   └── pvr_image.png                     # Splash screen image
├── database_setup.sql                    # Database setup script
└── README.md                             # This file
```

## Setup Instructions

### Prerequisites

1. **Java Development Kit (JDK)**: Version 8 or higher
2. **MySQL Server**: Version 5.7 or higher
3. **IDE**: VS Code, IntelliJ IDEA, or Eclipse (optional)

### Step 1: Database Setup

1. Start MySQL server
2. Open MySQL command line or MySQL Workbench
3. Run the `database_setup.sql` script:
   ```sql
   source database_setup.sql
   ```
   Or copy and paste the contents into MySQL client

4. Verify database creation:
   ```sql
   SHOW DATABASES;
   USE numerologydb;
   SHOW TABLES;
   ```

### Step 2: Configure Database Connection

Edit `src/com/my_example/dao/DB.java` and update the connection string if needed:
- **Host**: localhost (or your MySQL host)
- **Port**: 3306 (default MySQL port)
- **Database**: numerologydb
- **Username**: root (or your MySQL username)
- **Password**: 123456789 (update to your MySQL password)

```java
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/numerologydb", 
    "root", 
    "your_password"
);
```

### Step 3: Build and Run

1. **Using VS Code**:
   - Open the project folder in VS Code
   - Ensure Java extension is installed
   - Run `App.java` or `SplashScreen.java`

2. **Using Command Line**:
   ```bash
   # Compile
   javac -cp "lib/mysql-connector-j-9.5.0.jar" -d bin src/**/*.java
   
   # Run
   java -cp "bin;lib/mysql-connector-j-9.5.0.jar" App
   ```

3. **Using IDE**:
   - Import project
   - Add `lib/mysql-connector-j-9.5.0.jar` to classpath
   - Run `App.java` as main class

## Usage Guide

### 1. Application Flow

1. **Splash Screen**: Application starts with a loading screen
2. **Login/Signup**: 
   - New users: Click "SIGN UP" to create an account
   - Existing users: Enter username and password, click "LOGIN"
3. **Dashboard**: View overview and navigate to numerology calculator
4. **Generate Report**: 
   - Enter full name and date of birth (DD-MM-YYYY format)
   - Click "Generate Report"
   - PDF report will be generated and can be opened/downloaded

### 2. Numerology Calculations

The application calculates four main numerology numbers:

- **Life Path Number**: Calculated from date of birth (day + month + year reduced to single digit)
- **Destiny Number**: Calculated from full name (sum of all letters reduced to single digit)
- **Soul Number**: Calculated from vowels in name
- **Personality Number**: Calculated from consonants in name

### 3. Report Sections

Each generated report includes:

1. **Career Insights**: Professional path and work style based on Life Path Number
2. **Relationship Insights**: How you connect with others based on Soul Number
3. **Health Insights**: Physical and mental well-being guidance
4. **Money Insights**: Financial patterns and opportunities based on Destiny Number
5. **Characteristics**: Core personality traits

## Database Schema

### users Table
```sql
CREATE TABLE users (
    userid VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### numerology_reports Table (Optional)
```sql
CREATE TABLE numerology_reports (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(50),
    username VARCHAR(100),
    date_of_birth DATE,
    life_path_number INT,
    destiny_number INT,
    soul_number INT,
    personality_number INT,
    report_generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE
);
```

## Technologies Used

- **Frontend**: Java Swing (GUI framework)
- **Backend**: MySQL Database
- **JDBC**: MySQL Connector/J 9.5.0
- **Java**: Core Java (Collections, I/O, Date/Time)
- **Architecture**: Layered Architecture with SOLID principles

## Key Classes and Their Responsibilities

### UI Classes
- **SplashScreen**: Loading screen with progress bar
- **Login**: User authentication interface
- **Signup**: New user registration interface
- **Dashboard**: Main dashboard after login
- **NumerologyMainPage**: Numerology calculation and report generation interface

### Service Classes
- **NumberCalculator**: Numerology number calculations
- **NumerologyInterpretationService**: Provides interpretations for numbers
- **PDFGeneratorService**: Generates PDF reports

### DAO Classes
- **DB**: Database connection management (Singleton pattern)
- **UserDao**: User authentication and registration operations

### DTO Classes
- **UserDTO**: User data transfer object
- **NumerologyDTO**: Numerology data transfer object

## Future Enhancements

1. Add iText or Apache PDFBox library for proper PDF generation
2. Store generated reports in database
3. Add user profile management
4. Implement report history
5. Add more numerology calculations (Expression Number, Maturity Number, etc.)
6. Add chart/graph visualizations
7. Implement password encryption/hashing
8. Add email functionality for report delivery

## Troubleshooting

### Database Connection Issues
- Verify MySQL server is running
- Check database credentials in `DB.java`
- Ensure `numerologydb` database exists
- Verify MySQL connector JAR is in classpath

### Compilation Errors
- Ensure all required JAR files are in `lib/` folder
- Check Java version compatibility (JDK 8+)
- Verify all imports are correct

### Runtime Errors
- Check database connection string
- Verify table structure matches schema
- Check file permissions for PDF generation

## License

This project is created for educational purposes.

## Author

Numerology Application - Java Swing Project

---

**Note**: Make sure to update database credentials in `DB.java` before running the application.
