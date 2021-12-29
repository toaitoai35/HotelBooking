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
public class UserDTO implements Serializable{
    
    private String email;
    private String password;
    private String username;
    private String phone;
    private String address;
    private String role;
    private boolean status;
    private Date createDate;

    public UserDTO() {
    }
    
    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(String email, String password, String username, String phone, String address, String role, boolean status, Date createDate) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.status = status;
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
