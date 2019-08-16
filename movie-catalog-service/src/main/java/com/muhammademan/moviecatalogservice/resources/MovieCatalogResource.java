/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhammademan.moviecatalogservice.resources;

import com.muhammademan.moviecatalogservice.model.CatalogItem;
import com.muhammademan.moviecatalogservice.model.UserRating;
import com.muhammademan.moviecatalogservice.service.MovieInfo;
import com.muhammademan.moviecatalogservice.service.UserRatingInfo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eman
 */
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //get all rated movie ids
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 5)
//        );
//        UserRating ratings = restemplate.getForObject("http://rating-data-service/ratingsdata/user/" + userId, UserRating.class);
        UserRating ratings = userRatingInfo.getUserRating(userId);

        return ratings.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }

//    public List<CatalogItem> getFallbackCatalog(String userId) {
//        return Arrays.asList(new CatalogItem("No Movie", "", 0));
//    }
//    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
//    public UserRating getUserRating(String userId) {
//        return restemplate.getForObject("http://rating-data-service/ratingsdata/user/" + userId, UserRating.class);
//    }
//
//    public UserRating getFallbackUserRating(String userId) {
//        UserRating userRating = new UserRating();
//        userRating.setUserId(userId);
//        userRating.setUserRating(Arrays.asList(
//                new Rating("0", 0)
//        ));
//        return userRating;
//    }
//    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
//    public CatalogItem getCatalogItem(Rating rating) {
//        Movie movie = restemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
//        return new CatalogItem(movie.getName(), "Desc", rating.getRating());
//    }
//
//    public CatalogItem getFallbackCatalogItem(Rating rating) {
//        return new CatalogItem("Movie Name Not Found", "", rating.getRating());
//    }
}
