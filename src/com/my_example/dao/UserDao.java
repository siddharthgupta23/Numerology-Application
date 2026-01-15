package com.my_example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my_example.dto.UserDTO;

public class UserDao {
    public String auth(UserDTO userDTO) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select userid from users where userid=? and password=?";
            con = DB.createConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userDTO.getUserid());
            pstmt.setString(2, userDTO.getPassword());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return "Welcome " + userDTO.getUserid();
            } else {
                return "Invalid UserId or password";
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }

    public boolean register(UserDTO userDTO) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into users (userid, password) values (?, ?)";
            con = DB.createConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userDTO.getUserid());
            pstmt.setString(2, userDTO.getPassword());
            int result = pstmt.executeUpdate();
            return result > 0;
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }

    public boolean userExists(String userid) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select userid from users where userid=?";
            con = DB.createConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            return rs.next();
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }
}
