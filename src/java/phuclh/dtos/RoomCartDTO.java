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
public class RoomCartDTO implements Serializable{
    private String roomID;
    private String  roomImg;
    private String roomDescripton;
    private String hotelName;
    private Date checkInDate;
    private Date checkOutDate;
    private float roomPrice;
    private int quantity;
    private float total;

    public RoomCartDTO() {
    }

    public RoomCartDTO(String roomID, String roomImg, String roomDescripton, String hotelName, Date checkInDate, Date checkOutDate, float roomPrice, int quantity, float total) {
        this.roomID = roomID;
        this.roomImg = roomImg;
        this.roomDescripton = roomDescripton;
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomPrice = roomPrice;
        this.quantity = quantity;
        this.total = total;
    }

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public String getRoomDescripton() {
        return roomDescripton;
    }

    public void setRoomDescripton(String roomDescripton) {
        this.roomDescripton = roomDescripton;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
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

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
