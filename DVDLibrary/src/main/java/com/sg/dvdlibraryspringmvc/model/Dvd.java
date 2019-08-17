/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author keb03_000
 */
public class Dvd {
    
    private Integer dvdId;
    
    @NotEmpty(message = "You must supply a value for Title.")
    @Length(max = 60, message = "Title must be no more than 60 characters in length.")
    private String dvdTitle;
    
    @Range(min = 1900, max = 3000, message = "Year must be accurate year.")
    private Integer releaseYear;
    
    @NotEmpty(message = "You must supply a value for Director.")
    @Length(max = 50, message = "Director must be no more than 50 characters in length.")
    private String director;
    
    @NotEmpty(message = "You must supply a value for Rating.")
    @Length(max = 5, message = "Rating must be no more than 5 characters in length.")
    private String rating;
    
    @NotEmpty(message = "You must supply a value for Notes.")
    @Length(max = 180, message = "Notes must be no more than 180 characters in length.")
    private String notes;

    public String getDvdTitle() {
        return dvdTitle;
    }

    public void setDvdTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getDvdId() {
        return dvdId;
    }

    public void setDvdID(Integer dvdId) {
        this.dvdId = dvdId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.dvdId);
        hash = 73 * hash + Objects.hashCode(this.dvdTitle);
        hash = 73 * hash + Objects.hashCode(this.releaseYear);
        hash = 73 * hash + Objects.hashCode(this.director);
        hash = 73 * hash + Objects.hashCode(this.rating);
        hash = 73 * hash + Objects.hashCode(this.notes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (!Objects.equals(this.dvdTitle, other.dvdTitle)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.dvdId, other.dvdId)) {
            return false;
        }
        if (!Objects.equals(this.releaseYear, other.releaseYear)) {
            return false;
        }
        return true;
    }
    
    
}

