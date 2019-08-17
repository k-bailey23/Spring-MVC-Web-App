/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.model.Address;
import java.util.List;

/**
 *
 * @author keb03_000
 */
public interface AddressBookSpringMVCDao {
    
    public void addAddress(Address address);
    
    public void deleteAddress(int addressId);
    
    public void updateAddress(Address address);
    
    public Address getAddressById(int id);
    
    public List<Address> getAllAddresses();
    
}
