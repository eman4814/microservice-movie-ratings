/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhammademan.moviecatalogservice.service;

import com.muhammademan.moviecatalogservice.model.Rating;
import com.muhammademan.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eman
 */

@Service
public class UserRatingInfo {
    
    @Autowired
    private RestTemplate restemplate;
    
    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
            },
            threadPoolKey = "UserRatingInfoPool",
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize",value = "20"),
                @HystrixProperty(name = "maxQueueSize",value = "10"),
            }
    )
    public UserRating getUserRating(String userId) {
        return restemplate.getForObject("http://rating-data-service/ratingsdata/user/" + userId, UserRating.class);
    }

    public UserRating getFallbackUserRating(String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRating(Arrays.asList(
                new Rating("0", 0)
        ));
        return userRating;
    }
}
