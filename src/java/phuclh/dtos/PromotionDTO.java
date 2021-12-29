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
public class PromotionDTO implements Serializable{
    private String promotionCode;
    private Date promotionDateExpired;
    private boolean promotionStatus;

    public PromotionDTO() {
    }

    public PromotionDTO(String promotionCode, Date promotionDateExpired, boolean promotionStatus) {
        this.promotionCode = promotionCode;
        this.promotionDateExpired = promotionDateExpired;
        this.promotionStatus = promotionStatus;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public Date getPromotionDateExpired() {
        return promotionDateExpired;
    }

    public void setPromotionDateExpired(Date promotionDateExpired) {
        this.promotionDateExpired = promotionDateExpired;
    }

    public boolean isPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(boolean promotionStatus) {
        this.promotionStatus = promotionStatus;
    }
    
}
