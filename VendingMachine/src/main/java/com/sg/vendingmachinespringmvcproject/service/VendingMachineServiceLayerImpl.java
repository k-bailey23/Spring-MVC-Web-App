/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvcproject.service;

import com.sg.vendingmachinespringmvcproject.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvcproject.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author keb03_000
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    // Initialization
    private VendingMachineDao dao;
    
    private BigDecimal deposit = new BigDecimal("0.00");
    
    private BigDecimal dollars = new BigDecimal("1.00");
    private BigDecimal quarters = new BigDecimal("0.25");
    private BigDecimal dimes = new BigDecimal("0.10");
    private BigDecimal nickels = new BigDecimal("0.05");
    private BigDecimal pennies = new BigDecimal("0.01");
    
    private BigDecimal depositShort = new BigDecimal("0.0");
    private BigDecimal absoluteShort = new BigDecimal("0.0");
    
    private int quarterCount = 0;
    private int dimeCount = 0;
    private int nickelCount = 0;
    private int pennyCount = 0;
    
    private int intItemNumber = 0;
    private String message = "";
    
    String itemNumberString = "";
    
    String changeDue = "";
    
    // Dependency Injection
    @Inject 
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }
    
    // Method: Get Items
    // Returns: List<Item> itemList
    @Override
    public List<Item> getItems() {
        List<Item> itemList = dao.getItems();
        return itemList;
    }

    // Method: Select Item
    // Returns: String itemNumberString
    @Override
    public String selectItem(String itemNumber) {
        intItemNumber = Integer.parseInt(itemNumber);
        itemNumberString = Integer.toString(intItemNumber);
        return itemNumberString;
    }

    // Method: Select Item String
    // Returns: String itemNumberString
    @Override
    public String selectItemString() {
        return itemNumberString;
    }

    // Method: Add Money
    // Returns: void
    @Override
    public void addMoney(String money) {
        switch (money) {
            case "addDollar":
                deposit = deposit.add(dollars);
                break;
            case "addQuarter":
                deposit = deposit.add(quarters);
                break;
            case "addDime":
                deposit = deposit.add(dimes);
                break;
            case "addNickel":
                deposit = deposit.add(nickels);
                break;
            case "addPenny":
                deposit = deposit.add(pennies);
                break;
            default:
                deposit = deposit.add(BigDecimal.ZERO);
                break;
        }
    }

    // Method: Get Deposit
    // Returns: BigDecimal deposit
    @Override
    public BigDecimal getDeposit() {
        return deposit;
    }

    // Method: Set Deposit
    // Returns: void
    @Override
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    // Method: Make Purchase
    // Returns: void
    @Override
    public void makePurchase() {
        Item item = dao.getItemNumber(intItemNumber);
        BigDecimal bigDPrice = new BigDecimal(item.getPrice());
        int quantity = item.getQuantity();

        
        // No Item Inventory Exception
        if (quantity <= 0) {
           message = "SOLD OUT!!!";
        } else {
           depositShort = deposit.subtract(bigDPrice);

           // Insufficient Funds Exception
           if (depositShort.compareTo(BigDecimal.ZERO) < 0) {
               absoluteShort = depositShort.abs();
               message = "Please deposit: $" + absoluteShort;

           // Successful Transaction
           } else {
               deposit = depositShort;
               quantity = quantity - 1;
               item.setQuantity(quantity);
               getChange();
               message = "Thank You!";
           }
        }   
    }

    // Method: Get Message
    // Returns: String message
    @Override
    public String getMessage() {
        return message;
    }

    // Method: Set Message
    // Returns: void
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    // Method: Get Change Due
    // Returns: String changeDue
    @Override
    public String getChangeDue() {
        return changeDue;
    }

    // Method: Get Change
    // Returns: void
    @Override
    public void getChange() {
        if (itemNumberString == null) {
            message = null;
            changeDue = null;
        } else {
            while (deposit.compareTo(quarters) > 0 || (deposit.compareTo(quarters) == 0)) {
                quarterCount++;
                deposit = deposit.subtract(quarters);
            }
            while (deposit.compareTo(dimes) > 0 || (deposit.compareTo(dimes) == 0)) {
                dimeCount++;
                deposit = deposit.subtract(dimes);
            }
            while (deposit.compareTo(nickels) > 0 || (deposit.compareTo(nickels) == 0)) {
                nickelCount++;
                deposit = deposit.subtract(nickels);
            }
            while (deposit.compareTo(pennies) > 0 || (deposit.compareTo(pennies) == 0)) {
                pennyCount++;
                deposit = deposit.subtract(pennies);
            }
            
            itemNumberString = null;
            message = null;
            changeDue = (quarterCount + " Quarters, " + dimeCount + " Dimes, " + nickelCount + " Nickles, " + pennyCount + " Pennies.");
            quarterCount = 0;
            dimeCount = 0;
            nickelCount = 0;
            pennyCount = 0;
            
            
        }
    }
    
}
