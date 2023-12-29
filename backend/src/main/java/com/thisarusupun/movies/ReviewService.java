package com.thisarusupun.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class) // update the Movie object
                .matching(Criteria.where("imdbId").is(imdbId)) // find the movie using imdb id
                .apply(new Update().push("reviewIds").value(review)) // update the reviweIds array of that movie
                .first(); // confirm getting single movie and updating that

        return review;
    }
}
