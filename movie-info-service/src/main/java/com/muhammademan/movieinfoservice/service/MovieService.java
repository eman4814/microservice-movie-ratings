/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhammademan.movieinfoservice.service;

import com.muhammademan.movieinfoservice.model.Movie;
import com.muhammademan.movieinfoservice.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eman
 */
@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public Optional<Movie> getMovieInfo(int id) {
        return repository.findById(id);
    }

    public List<Movie> getAllMovieInfo() {
        List<Movie> movies = new ArrayList<>();
        repository.findAll().forEach(movies::add);
        return movies;
    }
    
    public void saveOrUpdate(Movie movie){
        repository.save(movie);
    }
    
    public void delete(int id){
        repository.deleteById(id);
    }

}
