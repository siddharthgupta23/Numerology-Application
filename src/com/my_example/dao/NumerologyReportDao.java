package com.my_example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.my_example.dto.NumerologyDTO;

/**
 * Single Responsibility: Handles database operations for numerology reports
 */
public class NumerologyReportDao {
    
    public boolean saveReport(String userid, NumerologyDTO numerologyDTO) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO numerology_reports (userid, username, date_of_birth, life_path_number, " +
                        "destiny_number, soul_number, personality_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
            con = DB.createConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, numerologyDTO.getUsername());
            
            // Convert Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(numerologyDTO.getDateOfBirth().getTime());
            pstmt.setDate(3, sqlDate);
            
            pstmt.setInt(4, numerologyDTO.getLifePathNumber());
            pstmt.setInt(5, numerologyDTO.getDestinyNumber());
            pstmt.setInt(6, numerologyDTO.getSoulNumber());
            pstmt.setInt(7, numerologyDTO.getPersonalityNumber());
            
            int result = pstmt.executeUpdate();
            return result > 0;
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }
}

