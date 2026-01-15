-- SQL Script to verify numerology reports are being saved
-- Run this in MySQL after generating a report

USE numerologydb;

-- View all reports
SELECT * FROM numerology_reports;

-- View reports for a specific user
SELECT * FROM numerology_reports WHERE userid = 'your_username_here';

-- Count total reports
SELECT COUNT(*) AS total_reports FROM numerology_reports;

-- View reports with user details (if needed)
SELECT 
    r.report_id,
    r.userid,
    r.username,
    r.date_of_birth,
    r.life_path_number,
    r.destiny_number,
    r.soul_number,
    r.personality_number,
    r.report_generated_at,
    u.created_at AS user_created_at
FROM numerology_reports r
LEFT JOIN users u ON r.userid = u.userid
ORDER BY r.report_generated_at DESC;

-- Check if table exists and structure
DESCRIBE numerology_reports;

