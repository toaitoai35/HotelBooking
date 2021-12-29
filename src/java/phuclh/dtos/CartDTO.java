/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Acer
 */
public class CartDTO implements Serializable{
    private Map<String, RoomCartDTO> cart;

    public CartDTO() {
    }

    public Map<String, RoomCartDTO> getCart() {
        return cart;
    }
    
    public void setCart(Map<String, RoomCartDTO> cart) {
        this.cart = cart;
    }
    
    public void add(RoomCartDTO dto) {
        if (this.cart == null){
            cart = new HashMap<>();
        }
        
        if (this.cart.containsKey(dto.getRoomID())){
            int quantity = cart.get(dto.getRoomID()).getQuantity();
            float total = dto.getTotal() / quantity;
            dto.setQuantity(quantity + 1);
            dto.setTotal(total * dto.getQuantity());
        }
        
        cart.put(dto.getRoomID(), dto);
    }
    
    public void delete(String id) {
        if (this.cart == null) {
            return;
        }

        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(String id, RoomCartDTO dto) {
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.replace(id, dto);
            }
        }
    }
}
