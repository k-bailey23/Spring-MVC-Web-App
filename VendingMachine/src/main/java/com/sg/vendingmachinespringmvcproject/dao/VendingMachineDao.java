/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvcproject.dao;

import com.sg.vendingmachinespringmvcproject.model.Item;
import java.util.List;

/**
 *
 * @author keb03_000
 */
public interface VendingMachineDao {
    
    public List<Item> getItems();
    
    public Item getItemNumber(int itemNumber);
}
