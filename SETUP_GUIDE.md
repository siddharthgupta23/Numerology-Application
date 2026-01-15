# Quick Setup Guide

## Prerequisites
- Java JDK 8 or higher installed
- MySQL Server installed and running
- MySQL Connector JAR file (already included in `lib/` folder)

## Step-by-Step Setup

### 1. Database Setup

Open MySQL command line or MySQL Workbench and run:

```sql
CREATE DATABASE IF NOT EXISTS numerologydb;
USE numerologydb;

CREATE TABLE IF NOT EXISTS users (
    userid VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS numerology_reports (
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

### 2. Update Database Credentials

Edit `src/com/my_example/dao/DB.java`:

Change line 12 to match your MySQL credentials:
```java
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/numerologydb", 
    "root",           // Your MySQL username
    "your_password"   // Your MySQL password
);
```

### 3. Run the Application

**Option A: Using VS Code**
1. Open the `booking_app` folder in VS Code
2. Open `src/App.java`
3. Click "Run" or press F5

**Option B: Using Command Line**
```bash
# Navigate to booking_app directory
cd booking_app

# Compile (Windows)
javac -cp "lib/mysql-connector-j-9.5.0.jar" -d bin src/com/my_example/**/*.java src/App.java

# Run (Windows)
java -cp "bin;lib/mysql-connector-j-9.5.0.jar" App

# For Linux/Mac, use : instead of ; in classpath
java -cp "bin:lib/mysql-connector-j-9.5.0.jar" App
```

### 4. First Time Usage

1. **Splash Screen** appears first
2. Click **"SIGN UP"** to create a new account
3. Enter username and password (minimum 4 characters)
4. After signup, you'll be redirected to login
5. Login with your credentials
6. You'll see the **Dashboard**
7. Click **"Generate Numerology Report"**
8. Enter your full name and date of birth (format: DD-MM-YYYY)
9. Click **"Generate Report"**
10. Report will be saved as a text file (can be converted to PDF)

## Troubleshooting

### "Connection refused" error
- Make sure MySQL server is running
- Check if MySQL is running on port 3306
- Verify database credentials

### "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
- Ensure `mysql-connector-j-9.5.0.jar` is in the `lib/` folder
- Check classpath includes the JAR file

### "Access denied" error
- Verify MySQL username and password
- Check if user has privileges on `numerologydb` database

### Image not showing in splash screen
- Ensure `images/pvr_image.png` exists
- Or the app will show text label instead

## Testing the Application

1. Create a test user: username="test", password="test123"
2. Login with test credentials
3. Generate a report with:
   - Name: "John Doe"
   - Date of Birth: "15-05-1990"
4. Check the generated report file

## Notes

- Reports are generated as text files (`.txt` format)
- To convert to PDF, you can use online converters or add iText/Apache PDFBox library
- All user data is stored securely in MySQL database
- Passwords are stored as plain text (consider hashing for production)



