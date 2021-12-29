/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Acer
 */
public class OrderDTO implements Serializable{
    private String orderID;
    private String emailUser;
    private float orderTotal;
    private boolean orderStatus;
    private Date orderDateCreate;
    
    private String hotelImg;
    private String hotelName;
    private Date checkInDate;
    private Date checkOutDate;
    private float total;
    private int quantity;
    
    public OrderDTO() {
    }

    public OrderDTO(String orderID, String hotelImg, String hotelName, Date checkInDate, Date checkOutDate, float total, int quantity) {
        this.orderID = orderID;
        this.hotelImg = hotelImg;
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.total = total;
        this.quantity = quantity;
    }

    public OrderDTO(String orderID, String emailUser, float orderTotal, boolean orderStatus, Date orderDateCreate) {
        this.orderID = orderID;
        this.emailUser = emailUser;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
        this.orderDateCreate = orderDateCreate;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDateCreate() {
        return orderDateCreate;
    }

    public void setOrderDateCreate(Date orderDateCreate) {
        this.orderDateCreate = orderDateCreate;
    }
    
}
