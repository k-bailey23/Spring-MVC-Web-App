/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author keb03_000
 */
public class DvdLibraryDaoDbImpl implements DvdLibraryDao {

    private static final String SQL_INSERT_DVD
            = "insert into dvd "
            + "(dvd_title, release_year, director, rating, notes) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_DVD
            = "delete from dvd where dvd_id = ?";
    private static final String SQL_SELECT_DVD
            = "select * from dvd where dvd_id = ?";
    private static final String SQL_UPDATE_DVD
            = "update dvd set "
            + "dvd_title = ?, release_year = ?, director = ?, "
            + "rating = ?, notes = ? "
            + "where dvd_id = ?";
    
    private static final String SQL_SELECT_ALL_DVD
            = "select * from dvd";
    
    private static final String SQL_SELECT_DVD_BY_TITLE
            = "select * from dvd where dvd_title = ?";
    private static final String SQL_SELECT_DVD_BY_DIRECTOR
            = "select * from dvd where director = ?";
    private static final String SQL_SELECT_DVD_BY_RELEASEYEAR
            = "select * from dvd where release_year = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getDvdTitle(),
                dvd.getReleaseYear(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        
        dvd.setDvdID(newId);
        return dvd;
    }

    @Override
    public void removeDvd(Integer dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getDvdTitle(),
                dvd.getReleaseYear(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes(),
                dvd.getDvdId());
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVD, 
                new DvdMapper());
    }

    @Override
    public Dvd getDvdById(Integer dvdId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD,
                    new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Dvd> searchDvd(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
            return getAllDvds();
        } else {
            StringBuilder sQuery
                    = new StringBuilder("select * from dvd where ");
            int numParams = criteria.size();
            int paramPosition = 0;
            
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            return jdbcTemplate.query(sQuery.toString(),
                    new DvdMapper(),
                    paramVals);
        }
    }
    
    private static final class DvdMapper implements RowMapper<Dvd> {
        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdID(rs.getInt("dvd_id"));
            dvd.setDvdTitle(rs.getString("dvd_title"));
            dvd.setReleaseYear(rs.getInt("release_year"));
            dvd.setDirector(rs.getString("director"));
            dvd.setRating(rs.getString("rating"));
            dvd.setNotes(rs.getString("notes"));
            return dvd;
        }
    }
    
}
