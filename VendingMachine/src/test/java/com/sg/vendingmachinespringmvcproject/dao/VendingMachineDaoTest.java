/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvcproject.dao;

import com.sg.vendingmachinespringmvcproject.model.Item;
import java.util.HashMap;
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
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
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
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetItemsAndGetItemNumber() {
        HashMap<Integer, Item> items = new HashMap<>();
    
        items.put(1, new Item("1", "Snickers", "1.85", 9));
        items.put(2, new Item("2", "M & Ms", "1.50", 2));
        items.put(3, new Item("3", "Pringles", "2.10", 5));
        items.put(4, new Item("4", "Reese's", "1.85", 4));
        items.put(5, new Item("5", "Pretzels", "1.25", 9));
        items.put(6, new Item("6", "Twinkies", "1.95", 3));
        items.put(7, new Item("7", "Doritos", "1.75", 11));
        items.put(8, new Item("8", "Almond Joy", "1.85", 0));
        items.put(9, new Item("9", "Trident", "1.95", 6));
        
        List<Item> fromDao = dao.getItems();
        assertNotNull(fromDao);
        
        Item getItemNumberTest = dao.getItemNumber(1);
        assertNotNull(getItemNumberTest);
        
        
        
        
    }

    

    
    
}
