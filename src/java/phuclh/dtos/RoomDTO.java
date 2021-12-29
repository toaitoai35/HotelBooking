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
public class RoomDTO implements Serializable{
    private String roomID;
    private String roomDescription;
    private String roomType;
    private boolean roomStatus;
    private float roomPrice;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomImg;
    private int roomQuantity;
    private String hotelID;

    public RoomDTO() {
    }

    public RoomDTO(String roomID, String hotelID, String roomDescription, String roomType, boolean roomStatus, float roomPrice, Date checkInDate, Date checkOutDate, String roomImg, int roomQuantity) {
        this.roomID = roomID;
        this.roomDescription = roomDescription;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.roomPrice = roomPrice;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomImg = roomImg;
        this.roomQuantity = roomQuantity;
        this.hotelID = hotelID;
    }

    public RoomDTO(String roomID, String roomDescription, String roomType, float roomPrice, String roomImg, int roomQuantity) {
        this.roomID = roomID;
        this.roomDescription = roomDescription;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomImg = roomImg;
        this.roomQuantity = roomQuantity;
    }

    public RoomDTO(String roomID, String roomDescription, String roomType, float roomPrice, Date checkInDate, Date checkOutDate, String roomImg, int roomQuantity) {
        this.roomID = roomID;
        this.roomDescription = roomDescription;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomImg = roomImg;
        this.roomQuantity = roomQuantity;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
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

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
    
}
