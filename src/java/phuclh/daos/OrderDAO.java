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
import java.util.Map;
import javax.naming.NamingException;
import phuclh.dtos.OrderDTO;
import phuclh.dtos.RoomCartDTO;
import phuclh.utils.DBUtils;

/**
 *
 * @author Acer
 */
public class OrderDAO implements Serializable {

    private List<OrderDTO> orderList;

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public boolean insertOrder(OrderDTO orderDTO) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert tblOrder(OrderID, emailUser, orderTotal, orderStatus, orderDateCreate) values(?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, orderDTO.getOrderID());
                pst.setString(2, orderDTO.getEmailUser());
                pst.setFloat(3, orderDTO.getOrderTotal());
                pst.setBoolean(4, orderDTO.isOrderStatus());
                pst.setDate(5, orderDTO.getOrderDateCreate());
                int row = pst.executeUpdate();
                if (row > 0) {
                    result = true;
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
        return result;
    }

    public boolean insertOrderDetails(String id, Map<String, RoomCartDTO> cart) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                int row = 0;
                String sql = "insert tblOrderDetail(OrderID, roomID, checkInDate, checkOutDate, total, quantity, [orderDetailStatus]) values(?,?,?,?,?,?,1)";
                for (String items : cart.keySet()) {
                    pst = con.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, items);
                    pst.setDate(3, cart.get(items).getCheckInDate());
                    pst.setDate(4, cart.get(items).getCheckOutDate());
                    pst.setFloat(5, cart.get(items).getTotal());
                    pst.setInt(6, cart.get(items).getQuantity());
                    row = pst.executeUpdate();
                }
                if (row > 0) {
                    result = true;
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
        return result;
    }

    public boolean deleteOrderDetails(String code) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update tblOrderDetail set [orderDetailStatus] = 0 where orderDetailID = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, code);
                int row = pst.executeUpdate();
                if (row > 0) {
                    result = true;
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
        return result;
    }

    public void getOrderDetailByHotelName(String searchValue, String email, String checkInDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        OrderDTO dto = null;
        String sql = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (searchValue.equals("") || (!searchValue.equals("") && !checkInDate.equals(""))) {
                    sql = "select ord.orderDetailID, hotelImg, hotelName, ord.checkInDate, ord.checkOutDate, total, quantity\n"
                            + "from ((tblOrderDetail ord join tblRoom room on ord.roomID = room.roomID) join tblHotel hotel on room.hotelID = hotel.hotelID) \n"
                            + "join tblOrder o on ord.orderID = o.orderID\n"
                            + "where o.emailUser = ? and (ord.checkInDate = ? and hotelName like ?) and ord.[orderDetailStatus] = 1";
                }
                if (!searchValue.equals("") && checkInDate.equals("")) {
                    sql = "select ord.orderDetailID, hotelImg, hotelName, ord.checkInDate, ord.checkOutDate, total, quantity\n"
                            + "from ((tblOrderDetail ord join tblRoom room on ord.roomID = room.roomID) join tblHotel hotel on room.hotelID = hotel.hotelID) \n"
                            + "join tblOrder o on ord.orderID = o.orderID\n"
                            + "where o.emailUser = ? and (ord.checkInDate = ? or hotelName like ?) and ord.[orderDetailStatus] = 1";
                }
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, checkInDate);
                pst.setString(3, "%" + searchValue + "%");
                rs = pst.executeQuery();

                while (rs.next()) {
                    String orderID = rs.getString("orderDetailID");
                    String hotelImg = rs.getString("hotelImg");
                    String hotelName = rs.getString("hotelName");
                    Date checkInDates = rs.getDate("checkInDate");
                    Date checkOutDate = rs.getDate("checkOutDate");
                    float total = rs.getFloat("total");
                    int quantity = rs.getInt("quantity");

                    dto = new OrderDTO(orderID, hotelImg, hotelName, checkInDates, checkOutDate, total, quantity);
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }
                    this.orderList.add(dto);
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
