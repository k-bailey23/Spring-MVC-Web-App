/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvcproject.service;

import com.sg.vendingmachinespringmvcproject.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author keb03_000
 */
public interface VendingMachineServiceLayer {
    
    public List<Item> getItems();
    
    public String selectItem(String itemNumber);
    
    public String selectItemString();
    
    public void addMoney(String money);
    
    public BigDecimal getDeposit();
    
    public void setDeposit(BigDecimal deposit);
    
    public void makePurchase();
    
    public String getMessage();
    
    public void setMessage(String message);
    
    public String getChangeDue();
    
    public void getChange();
}
