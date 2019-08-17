/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.List;
import java.util.Map;

/**
 *
 * @author keb03_000
 */
public interface DvdLibraryDao {
    
    // add the given Dvd to the data store
    public Dvd addDvd(Dvd dvd);
    
    // remove the Dvd with the given id from the data store
    public void removeDvd(Integer dvdId);
    
    // update the given Dvd in the data store
    public void updateDvd(Dvd dvd);
    
    // retireve all Dvds from the data store
    public List<Dvd> getAllDvds();
    
    // retrieve the Dvd with the given id from the data store
    public Dvd getDvdById(Integer dvdId);
    
    // search for Dvd by the given search criteria values
    public List<Dvd> searchDvd(Map<SearchTerm, String> criteria);
}
