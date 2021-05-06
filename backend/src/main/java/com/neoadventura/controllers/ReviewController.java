package com.neoadventura.controllers;

import com.neoadventura.dtos.CreateReviewDto;
import com.neoadventura.dtos.CreateUsuarioDto;
import com.neoadventura.dtos.ReviewDto;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.responses.NeoAdventuraResponse;
import com.neoadventura.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/neo-adventura"+"/v1")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reviews")
    public NeoAdventuraResponse<ReviewDto> createReview(@RequestBody CreateReviewDto createReviewDto)
            throws NeoAdventuraException {
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK),
                "OK", reviewService.CreateReview(createReviewDto));
    }

    /*
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/reviews/{reviewId}")
    public NeoAdventuraResponse<UsuarioDto> getUsuarioById(@PathVariable Long reviewId)
            throws NeoAdventuraException {
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                usuarioService.getUsuarioById(reviewId));
    }
    */

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/reviews")
    public NeoAdventuraResponse<List<ReviewDto>> getReviews()
            throws NeoAdventuraException{
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                reviewService.getReviews());
    }
}
