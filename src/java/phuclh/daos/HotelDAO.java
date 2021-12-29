/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuclh.dtos.HotelDTO;
import phuclh.utils.DBUtils;

/**
 *
 * @author Acer
 */
public class HotelDAO implements Serializable {

    private List<HotelDTO> hotelList;

    public List<HotelDTO> getHotelList() {
        return hotelList;
    }

    public void searchHotelWithDate(String searchValue, String checkInDate, String checkOutDate, int amountRoom) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "select distinct h.hotelID, hotelName, hotelAddress, hotelCity, hotelPhoneNumber, hotelImg\n"
                        + "from tblHotel h join tblRoom r on r.hotelID = h.hotelID \n"
                        + "where (hotelName like ? or hotelCity = ?) \n"
                        + "and (checkInDate <= ? and checkOutDate >= ?) and roomQuantity >= ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setString(2, searchValue);
                pst.setString(3, checkInDate);
                pst.setString(4, checkOutDate);
                pst.setInt(5, amountRoom);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String hotelID = rs.getString("hotelID");
                    String hotelName = rs.getString("hotelName");
                    String hotelAddress = rs.getString("hotelAddress");
                    String hotelCity = rs.getString("hotelCity");
                    String hotelPhone = rs.getString("hotelPhoneNumber");
                    String hotelImg = rs.getString("hotelImg");

                    HotelDTO dto = new HotelDTO(hotelID, hotelName, hotelAddress, hotelCity, hotelPhone, hotelImg);

                    if (this.hotelList == null) {
                        this.hotelList = new ArrayList<>();
                    }
                    this.hotelList.add(dto);
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

    public String getHotelNameByRoomID(String roomID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String hotelName = "";
        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "select hotelName\n"
                        + "from tblHotel h join tblRoom r on h.hotelID = r.hotelID\n"
                        + "where roomID = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, roomID);
                rs = pst.executeQuery();

                if (rs.next()) {
                    hotelName = rs.getString("hotelName");
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
        return hotelName;
    }

    public void getListHotel() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "select distinct h.hotelID, hotelName, hotelAddress, hotelCity, hotelPhoneNumber, hotelImg\n"
                        + "from tblHotel h join tblRoom r on r.hotelID = h.hotelID \n"
                        + "where r.roomStatus = 1";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String hotelID = rs.getString("hotelID");
                    String hotelName = rs.getString("hotelName");
                    String hotelAddress = rs.getString("hotelAddress");
                    String hotelCity = rs.getString("hotelCity");
                    String hotelPhone = rs.getString("hotelPhoneNumber");
                    String hotelImg = rs.getString("hotelImg");

                    HotelDTO dto = new HotelDTO(hotelID, hotelName, hotelAddress, hotelCity, hotelPhone, hotelImg);

                    if (this.hotelList == null) {
                        this.hotelList = new ArrayList<>();
                    }
                    this.hotelList.add(dto);
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
}
