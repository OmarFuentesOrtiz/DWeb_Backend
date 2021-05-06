package com.neoadventura.services;

import com.neoadventura.dtos.CreateReviewDto;
import com.neoadventura.dtos.ReviewDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getReviews() throws NeoAdventuraException;
    ReviewDto CreateReview(CreateReviewDto createReviewDto) throws NeoAdventuraException;
}
