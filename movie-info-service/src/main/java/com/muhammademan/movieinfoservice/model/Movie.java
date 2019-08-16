/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhammademan.movieinfoservice.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author eman
 */
@Entity(name = "movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int movieId;
    private String name;
    

    public Movie() {
    }

    public Movie(int movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
