/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuclh.dtos.UserDTO;
import phuclh.utils.DBUtils;

/**
 *
 * @author Acer
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String email, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO userDTO = null;
        
        try {
            con = DBUtils.getConnection();
            
            if (con != null) {
                String sql = "select username, phone, address, role, status, createDate\n" +
                            "from tblUser\n" +
                            "where email = ? and password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    String username = rs.getString("username");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String role = rs.getString("role");
                    boolean status = rs.getBoolean("status");
                    Date creaeteDate = rs.getDate("createDate");
                    
                    userDTO = new UserDTO(email, password, username, phone, address, role, status, creaeteDate);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return userDTO;
    }
    
    public boolean createUser (UserDTO userDTO) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            
            if (con != null) {
                String sql = "insert tblUser(email, password, username, phone, address, role, status, createDate) values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, userDTO.getEmail());
                pst.setString(2, userDTO.getPassword());
                pst.setString(3, userDTO.getUsername());
                pst.setString(4, userDTO.getPhone());
                pst.setString(5, userDTO.getAddress());
                pst.setString(6, userDTO.getRole());
                pst.setBoolean(7, userDTO.isStatus());
                pst.setDate(8, userDTO.getCreateDate());
                int row = pst.executeUpdate();
                
                if (row > 0) {
                    check = true;
                }
                
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
    
    public boolean checkUserExist (String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            
            if (con != null) {
                String sql = "select Email from tblUser where Email = ?";
                
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    check = true;
                }
                
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
    
      public UserDTO getEmailSender() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO dto = null;
        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "select top 1 senderEmail, senderPassword from tblSenderEmail where senderStatus = 1 order by NEWID()";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String email = rs.getString("senderEmail");
                    String password = rs.getString("senderPassword");
                    dto = new UserDTO(email, password);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
}
