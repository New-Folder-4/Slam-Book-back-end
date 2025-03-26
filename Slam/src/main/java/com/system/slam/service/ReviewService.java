package com.system.slam.service;

import com.system.slam.web.dto.ReviewDto;
import com.system.slam.entity.BookLiterary;
import com.system.slam.entity.BookResponse;
import com.system.slam.entity.User;
import com.system.slam.repository.BookLiteraryRepository;
import com.system.slam.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookLiteraryRepository bookLiteraryRepository;
    private final UserService userService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         BookLiteraryRepository bookLiteraryRepository,
                         UserService userService) {
        this.reviewRepository = reviewRepository;
        this.bookLiteraryRepository = bookLiteraryRepository;
        this.userService = userService;
    }

    public ReviewDto createReview(ReviewDto dto) {
        BookLiterary bookLit = bookLiteraryRepository.findById(dto.getBookLiteraryId())
                .orElseThrow(() -> new RuntimeException("Book not found, id=" + dto.getBookLiteraryId()));

        User user = userService.getUserById(dto.getUserId());

        BookResponse review = new BookResponse();
        review.setBookLiterary(bookLit);
        review.setUser(user);
        review.setCreateAt(LocalDateTime.now());
        review.setResponse(dto.getResponse());

        BookResponse saved = reviewRepository.save(review);

        return convertToDto(saved);
    }

    public List<ReviewDto> getReviewsByBook(Long bookId) {
        List<BookResponse> reviews = reviewRepository.findAllByBookLiterary_IdBookLiterary(bookId);
        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsByUser(Long userId) {
        List<BookResponse> reviews = reviewRepository.findAllByUser_IdUser(userId);
        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReviewDto convertToDto(BookResponse review) {
        ReviewDto dto = new ReviewDto();
        dto.setIdBookResponse(review.getIdBookResponse());
        dto.setBookLiteraryId(review.getBookLiterary().getIdBookLiterary());
        dto.setUserId(review.getUser().getIdUser());
        dto.setCreateAt(review.getCreateAt());
        dto.setResponse(review.getResponse());
        return dto;
    }
}
