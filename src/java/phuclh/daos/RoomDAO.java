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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuclh.dtos.RoomDTO;
import phuclh.utils.DBUtils;

/**
 *
 * @author Acer
 */
public class RoomDAO implements Serializable {

    private List<RoomDTO> roomList;

    public List<RoomDTO> getRoomList() {
        return roomList;
    }

    public void getListRoomByHotelIDWithDate(String hotelID, String checkInDate, String checkOutDate, String amountRoom) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "select roomID, roomDescription, rt.roomType, roomStatus, roomPrice, checkInDate, checkOutDate, roomImg, roomQuantity\n"
                        + "from tblRoom r join tblRoomType rt on r.roomTypeID = rt.roomTypeID\n"
                        + "where hotelID = ? and (checkInDate <= ? and checkOutDate >= ?) and roomQuantity >= ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, hotelID);
                pst.setString(2, checkInDate);
                pst.setString(3, checkOutDate);
                pst.setString(4, amountRoom);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    String roomDescription = rs.getString("roomDescription");
                    String roomType = rs.getString("roomType");
                    boolean roomStatus = rs.getBoolean("roomStatus");
                    float roomPrice = rs.getFloat("roomPrice");
                    Date roonCheckInDate = rs.getDate("checkInDate");
                    Date roonCheckOutDate = rs.getDate("checkOutDate");
                    int roomQuantity = rs.getInt("roomQuantity");
                    String roomImg = rs.getString("roomImg");

                    RoomDTO dto = new RoomDTO(roomID, hotelID, roomDescription, roomType, roomStatus, roomPrice, roonCheckInDate, roonCheckOutDate, roomImg, roomQuantity);

                    if (this.roomList == null) {
                        this.roomList = new ArrayList<>();
                    }
                    this.roomList.add(dto);
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
    }

    public RoomDTO getRoomByID(String roomID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        RoomDTO dto = null;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "select roomID, hotelID, roomDescription, rt.roomType, roomStatus, roomPrice, checkInDate, checkOutDate, roomImg, roomQuantity\n"
                        + "from tblRoom r join tblRoomType rt on r.roomTypeID = rt.roomTypeID\n"
                        + "where roomID = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, roomID);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String hotelID = rs.getString("hotelID");
                    String roomDescription = rs.getString("roomDescription");
                    String roomType = rs.getString("roomType");
                    boolean roomStatus = rs.getBoolean("roomStatus");
                    float roomPrice = rs.getFloat("roomPrice");
                    Date roonCheckInDate = rs.getDate("checkInDate");
                    Date roonCheckOutDate = rs.getDate("checkOutDate");
                    int roomQuantity = rs.getInt("roomQuantity");
                    String roomImg = rs.getString("roomImg");

                    dto = new RoomDTO(roomID, hotelID, roomDescription, roomType, roomStatus, roomPrice, roonCheckInDate, roonCheckOutDate, roomImg, roomQuantity);
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
    
    public boolean updateQuantity(int quantity, String roomID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update tblRoom set roomQuantity = ? where roomID = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, quantity);
                pst.setString(2, roomID);
                int row = pst.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }
}
