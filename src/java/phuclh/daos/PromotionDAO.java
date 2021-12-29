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
import phuclh.utils.DBUtils;

/**
 *
 * @author Acer
 */
public class PromotionDAO implements Serializable{
    
    public float isCodeExpired (String code, Date currentDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        float discount = 0;
        
        try {
            con = DBUtils.getConnection();
            
            if (con != null) {
                String sql = "select promotionValue from tblPromotion where promotionCode = ? and promotionDateExpired >= ? and promotionStatus = 1";
                pst = con.prepareStatement(sql);
                pst.setString(1, code);
                pst.setDate(2, currentDate);
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    discount = rs.getFloat("promotionValue");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return discount;
    }
    
    public String isCodeUsed (String email, String code) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String discount = "";
        
        try {
            con = DBUtils.getConnection();
            
            if (con != null) {
                String sql = "select email from tblPromotionDetail where email = ? and promotionCode = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, code);
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    discount = rs.getString("email");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return discount;
    }
    
    public boolean insertPromotionDetail (String email, String code) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            
            if (con != null) {
                String sql = "insert tblPromotionDetail(email, promotionCode) values(?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, code);
                
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
}
