/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.dao.DvdLibraryDao;
import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author keb03_000
 */
public class DvdLibraryDaoTest {
    
    private DvdLibraryDao dao;

    public DvdLibraryDaoTest() {
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
                = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);

        List<Dvd> dvds = dao.getAllDvds();
        for (Dvd currentDvd : dvds) {
            dao.removeDvd(currentDvd.getDvdId());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteDvd() {
        Dvd nd = new Dvd();
        nd.setDvdTitle("Lord of the Rings");
        nd.setReleaseYear(2001);
        nd.setDirector("Peter Jackson");
        nd.setRating("PG-13");
        nd.setNotes("Good");
        dao.addDvd(nd);
        Dvd fromDb = dao.getDvdById(nd.getDvdId());
        assertEquals(fromDb, nd);
        dao.removeDvd(nd.getDvdId());
        assertNull(dao.getDvdById(nd.getDvdId()));
    }

    @Test
    public void addUpdateDvd() {
        Dvd nd = new Dvd();
        nd.setDvdTitle("Lord of the Rings");
        nd.setReleaseYear(2001);
        nd.setDirector("Peter Jackson");
        nd.setRating("PG-13");
        nd.setNotes("Good");
        dao.addDvd(nd);
        nd.setNotes("Excellent");
        dao.updateDvd(nd);
        Dvd fromDb = dao.getDvdById((nd.getDvdId()));
        assertEquals(fromDb, nd);
    }

    @Test
    public void getAllDvds() {
        Dvd nd = new Dvd();
        nd.setDvdTitle("Lord of the Rings");
        nd.setReleaseYear(2001);
        nd.setDirector("Peter Jackson");
        nd.setRating("PG-13");
        nd.setNotes("Good");
        dao.addDvd(nd);

        Dvd nd2 = new Dvd();
        nd2.setDvdTitle("Terminator");
        nd2.setReleaseYear(1984);
        nd2.setDirector("James Cameron");
        nd2.setRating("R");
        nd2.setNotes("Great");
        dao.addDvd(nd2);
        List<Dvd> allDvds = dao.getAllDvds();
        assertEquals(allDvds.size(), 2);
    }

    @Test
    public void searchDvds() {
        Dvd nd = new Dvd();
        nd.setDvdTitle("Lord of the Rings");
        nd.setReleaseYear(2001);
        nd.setDirector("Peter Jackson");
        nd.setRating("PG-13");
        nd.setNotes("Good");
        dao.addDvd(nd);

        Dvd nd2 = new Dvd();
        nd2.setDvdTitle("Terminator");
        nd2.setReleaseYear(1984);
        nd2.setDirector("James Cameron");
        nd2.setRating("R");
        nd2.setNotes("Great");
        dao.addDvd(nd2);

        Dvd nd3 = new Dvd();
        nd3.setDvdTitle("Iron Man");
        nd3.setReleaseYear(2008);
        nd3.setDirector("Jon Favreau");
        nd3.setRating("PG-13");
        nd3.setNotes("Pretty good");
        dao.addDvd(nd3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.DVDTITLE, "Lord of the Rings");
        List<Dvd> dvdList = dao.searchDvd(criteria);
        assertEquals(1, dvdList.size());
        assertEquals(nd, dvdList.get(0));

        criteria.put(SearchTerm.DIRECTOR, "Peter Jackson");
        dvdList = dao.searchDvd(criteria);
        assertEquals(1, dvdList.size());
        assertEquals(nd, dvdList.get(0));

    }

    @Test
    public void searchRating() {
        Dvd nd = new Dvd();
        nd.setDvdTitle("Lord of the Rings");
        nd.setReleaseYear(2001);
        nd.setDirector("Peter Jackson");
        nd.setRating("PG-13");
        nd.setNotes("Good");
        dao.addDvd(nd);

        Dvd nd2 = new Dvd();
        nd2.setDvdTitle("Terminator");
        nd2.setReleaseYear(1984);
        nd2.setDirector("James Cameron");
        nd2.setRating("R");
        nd2.setNotes("Great");
        dao.addDvd(nd2);

        Dvd nd3 = new Dvd();
        nd3.setDvdTitle("Iron Man");
        nd3.setReleaseYear(2008);
        nd3.setDirector("Jon Favreau");
        nd3.setRating("PG-13");
        nd3.setNotes("Pretty good");
        dao.addDvd(nd3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.RATING, "R");
        List<Dvd> dvdList = dao.searchDvd(criteria);
        assertEquals(1, dvdList.size());
        assertEquals(nd2, dvdList.get(0));

    }

    @Test
    public void searchNotes() {
        Dvd nd = new Dvd();
        nd.setDvdTitle("Lord of the Rings");
        nd.setReleaseYear(2001);
        nd.setDirector("Peter Jackson");
        nd.setRating("PG-13");
        nd.setNotes("Good");
        dao.addDvd(nd);

        Dvd nd2 = new Dvd();
        nd2.setDvdTitle("Terminator");
        nd2.setReleaseYear(1984);
        nd2.setDirector("James Cameron");
        nd2.setRating("R");
        nd2.setNotes("Great");
        dao.addDvd(nd2);

        Dvd nd3 = new Dvd();
        nd3.setDvdTitle("Iron Man");
        nd3.setReleaseYear(2008);
        nd3.setDirector("Jon Favreau");
        nd3.setRating("PG-13");
        nd3.setNotes("Pretty good");
        dao.addDvd(nd3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.NOTES, "Pretty good");
        List<Dvd> dvdList = dao.searchDvd(criteria);
        assertEquals(1, dvdList.size());
        assertEquals(nd3, dvdList.get(0));

    }

    @Test
    public void searchReleaseYear() {
        Dvd nd = new Dvd();
        nd.setDvdTitle("Lord of the Rings");
        nd.setReleaseYear(2001);
        nd.setDirector("Peter Jackson");
        nd.setRating("PG-13");
        nd.setNotes("Good");
        dao.addDvd(nd);

        Dvd nd2 = new Dvd();
        nd2.setDvdTitle("Terminator");
        nd2.setReleaseYear(1984);
        nd2.setDirector("James Cameron");
        nd2.setRating("R");
        nd2.setNotes("Great");
        dao.addDvd(nd2);

        Dvd nd3 = new Dvd();
        nd3.setDvdTitle("Iron Man");
        nd3.setReleaseYear(2008);
        nd3.setDirector("Jon Favreau");
        nd3.setRating("PG-13");
        nd3.setNotes("Pretty good");
        dao.addDvd(nd3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.RELEASEYEAR, "2008");
        List<Dvd> dvdList = dao.searchDvd(criteria);
        assertEquals(1, dvdList.size());
        assertEquals(nd3, dvdList.get(0));

    }
}

