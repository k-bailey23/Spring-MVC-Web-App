/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.model.Address;
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
public class AddressBookSpringMVCDaoTest {
    
    AddressBookSpringMVCDao dao;
    
    public AddressBookSpringMVCDaoTest() {
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
        
        dao = ctx.getBean("addressBookDao", AddressBookSpringMVCDao.class);
        
        // delete all addresses
        List<Address> addresses = dao.getAllAddresses();
        for (Address currentAddress : addresses) {
            dao.deleteAddress(currentAddress.getAddressId());
        }
    }
    
    @Test
    public void addGetAddress() {
        Address address = new Address();
        address.setFirstName("John");
        address.setLastName("Doe");
        address.setStreetAddress("123 Main Street");
        address.setCity("New York City");
        address.setState("New York");
        address.setZipCode(12345);
        
        dao.addAddress(address);
        
        Address fromDao = dao.getAddressById(address.getAddressId());
        assertEquals(fromDao, address);
    }
    
    @Test
    public void deleteAddress() {
        Address address = new Address();
        address.setFirstName("John");
        address.setLastName("Doe");
        address.setStreetAddress("123 Main Street");
        address.setCity("New York City");
        address.setState("New York");
        address.setZipCode(12345);
        
        dao.addAddress(address);
        
        Address fromDao = dao.getAddressById(address.getAddressId());
        assertEquals(fromDao, address);
        
        dao.deleteAddress(address.getAddressId());
        assertNull(dao.getAddressById(address.getAddressId()));
    }
    

    
}
