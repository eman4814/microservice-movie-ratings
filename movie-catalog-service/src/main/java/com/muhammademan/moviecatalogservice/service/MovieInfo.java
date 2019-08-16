/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhammademan.moviecatalogservice.service;

import com.muhammademan.moviecatalogservice.model.CatalogItem;
import com.muhammademan.moviecatalogservice.model.Movie;
import com.muhammademan.moviecatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eman
 */
@Service
public class MovieInfo {
    
    @Autowired
    private RestTemplate restemplate;
    
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
            },
            threadPoolKey = "MovieInfoPool",
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize",value = "20"),
                @HystrixProperty(name = "maxQueueSize",value = "10"),
            }
    )
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), "Desc", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie Name Not Found", "", rating.getRating());
    }
}
