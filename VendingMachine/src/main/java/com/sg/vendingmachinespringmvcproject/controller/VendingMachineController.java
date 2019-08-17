/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvcproject.controller;

import com.sg.vendingmachinespringmvcproject.model.Item;
import com.sg.vendingmachinespringmvcproject.service.VendingMachineServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author keb03_000
 */
@Controller
public class VendingMachineController {
    
    private VendingMachineServiceLayer service;
    
    @Inject 
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }
    
    // Spring @Request Mapping Annotation:
    //  - Maps HTTP request to handler methods of MVC/REST controller
    @RequestMapping(value = "/", method = RequestMethod.GET)
    // Display Vending Machine
    public String displayVendingMachine(Model model) {
        // Get all the Items from the DAO
        List<Item> itemList = service.getItems();
        
        // Put the List of Items on the Model
        model.addAttribute("itemList", itemList);
        // Get Deposit from DAO and put deposit value on the Model
        model.addAttribute("deposit", service.getDeposit());
        // Get Message from DAO and put message value on the Model
        model.addAttribute("messages", service.getMessage());
        // Get Item Number from DAO and put item number value on the Model
        model.addAttribute("itemNumber", service.selectItemString());
        // Get Change from DAO and put change value on the Model
        model.addAttribute("change", service.getChangeDue());
        
        // Return the logical name of View component
        return "vendingMachine";
    }
    
    // Spring @Request Mapping Annotation: 
    //   - Maps the /depositMoney/{money} URL to this method
    //   - Request matches the endpoint for the depositMoney form in the jsp
    @RequestMapping(value = "/depositMoney/{money}", method = RequestMethod.GET)
    // Add Money
    public String addMoney(@PathVariable String money) {
        // Data Persistence
        service.addMoney(money);
        // Redirect to external URL
        return "redirect:/";
    }
    
    // Spring @Request Mapping Annotation: 
    //   - Maps the /itemSelection/{itemNumber} URL to this method
    //   - Request matches the endpoint for the itemSelection form in the jsp
    @RequestMapping(value = "/itemSelection/{itemNumber}", method = RequestMethod.GET)
    // Item Selection
    public String itemSelection(@PathVariable String itemNumber) {
        // Data Persistence
        service.selectItem(itemNumber);
        // Redirect to external URL
        return "redirect:/";
    }
    
    // Spring @Request Mapping Annotation: 
    //   - Maps the /makePurchase URL to this method
    //   - Request matches the endpoint for the makePurchase form in the jsp
    @RequestMapping(value = "/makePurchase", method = RequestMethod.GET)
    // Make Purchase
    public String makePurchase() {
        // Data Persistence
        service.makePurchase();
        // Redirect to external URL
        return "redirect:/";
    }
    
    // Spring @Request Mapping Annotation: 
    //   - Maps the /returnChange URL to this method
    //   - Request matches the endpoint for the returnChange form in the jsp
    @RequestMapping(value = "/returnChange", method = RequestMethod.GET)
    // Return Change
    public String returnChange() {
        // Data Persistence
        service.getChange();
        // Redirect to external URL
        return "redirect:/";
    }
    
    
}
