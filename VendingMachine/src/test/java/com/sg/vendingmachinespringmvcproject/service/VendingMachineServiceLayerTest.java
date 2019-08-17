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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author keb03_000
 */
public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("VendingMachineServiceLayer", VendingMachineServiceLayer.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItems() {
        assertEquals(service.getItems().size(), 9);
        
    }

    /**
     * Test of selectItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSelectItem() {
        assertEquals(service.selectItem("1"), "1");
        assertNotNull(service.selectItemString());

    }


    /**
     * Test of addMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testAddMoney() {
        service.addMoney("1.00");
    }

    /**
     * Test of getDeposit method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetGetDeposit() {
        service.setDeposit(new BigDecimal(1.00));
        assertNotNull(service.getDeposit());
        assertEquals(service.getDeposit(), new BigDecimal(1.00));
    }

    /**
     * Test of setDeposit method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetDeposit() {
        service.setDeposit(new BigDecimal(1.00));
        assertNotNull(service.getDeposit());
        assertEquals(service.getDeposit(), new BigDecimal(1.00));
    }

    /**
     * Test of makePurchase method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testMakePurchase() {
    }

    /**
     * Test of getMessage method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetGetMessage() {
        service.setMessage("Success");
        service.getMessage();
        assertEquals(service.getMessage(), "Success");
    }


    /**
     * Test of getChangeDue method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetChangeDue() {
        service.getChangeDue();
        assertNotNull(service.getChangeDue());
    }

    /**
     * Test of getChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetChange() {
        service.getChange();
        
    }

    
    
}
