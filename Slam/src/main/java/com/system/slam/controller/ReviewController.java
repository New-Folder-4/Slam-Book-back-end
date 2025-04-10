package com.system.slam.controller;

import com.system.slam.web.dto.ReviewDto;
import com.system.slam.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto dto) {
        return reviewService.createReview(dto);
    }

    @GetMapping("/by-book/{bookId}")
    public List<ReviewDto> getReviewsByBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBook(bookId);
    }

    @GetMapping("/by-user/{userId}")
    public List<ReviewDto> getReviewsByUser(@PathVariable Long userId) {
        return reviewService.getReviewsByUser(userId);
    }
}
