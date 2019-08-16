/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhammademan.movieinfoservice.resources;

import com.muhammademan.movieinfoservice.model.Movie;
import com.muhammademan.movieinfoservice.service.MovieService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eman
 */
@RestController
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/api/movies/{movieId}")
    public Optional<Movie> getMovieInfo(@PathVariable("movieId") int movieId) {
        return movieService.getMovieInfo(movieId);
    }

    @RequestMapping("/api/movies")
    public List<Movie> getAllMovieInfo() {
        return movieService.getAllMovieInfo();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/movies")
    public void save(@RequestBody Movie movie) {
        movieService.saveOrUpdate(movie);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/movies")
    public void update(@RequestBody Movie movie) {
        movieService.saveOrUpdate(movie);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/movies/{movieId}")
    public void delete(@PathVariable("movieId") int movieId) {
        movieService.delete(movieId);
    }
}
