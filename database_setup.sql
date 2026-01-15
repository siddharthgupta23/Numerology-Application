-- Numerology Application Database Setup Script
-- Run this script in MySQL to create the required database and tables

CREATE DATABASE IF NOT EXISTS numerologydb;
USE numerologydb;

-- Users table for authentication
CREATE TABLE IF NOT EXISTS users (
    userid VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Numerology reports table (optional - for storing user reports)
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

DELETE FROM users
WHERE userid = "sdfbsjd";
DELETE FROM users
WHERE userid = "Siddharth Gupta";


select * from users;
select * from numerology_reports;

-- Sample data (optional)
-- INSERT INTO users (userid, password) VALUES ('admin', 'admin123');
-- INSERT INTO users (userid, password) VALUES ('testuser', 'test123');



