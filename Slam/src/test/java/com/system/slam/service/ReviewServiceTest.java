package com.system.slam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.system.slam.entity.BookLiterary;
import com.system.slam.entity.BookResponse;
import com.system.slam.entity.User;
import com.system.slam.repository.BookLiteraryRepository;
import com.system.slam.repository.ReviewRepository;
import com.system.slam.service.ReviewService;
import com.system.slam.service.UserService;
import com.system.slam.web.dto.ReviewDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookLiteraryRepository bookLiteraryRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ReviewService reviewService;

    private BookLiterary bookLiterary;
    private User user;
    private ReviewDto reviewDto;
    private BookResponse bookResponse;

    @BeforeEach
    void setUp() {
        bookLiterary = new BookLiterary();
        bookLiterary.setIdBookLiterary(1L);

        user = new User();
        user.setIdUser(1L);

        reviewDto = new ReviewDto();
        reviewDto.setBookLiteraryId(1L);
        reviewDto.setUserId(1L);
        reviewDto.setResponse("Great book!");

        bookResponse = new BookResponse();
        bookResponse.setIdBookResponse(1L);
        bookResponse.setBookLiterary(bookLiterary);
        bookResponse.setUser(user);
        bookResponse.setCreateAt(LocalDateTime.now());
        bookResponse.setResponse("Great book!");
    }

    @Test
    void createReview_Success() {
        // Arrange
        when(bookLiteraryRepository.findById(1L)).thenReturn(Optional.of(bookLiterary));
        when(userService.getUserById(1L)).thenReturn(user);
        when(reviewRepository.save(any(BookResponse.class))).thenReturn(bookResponse);

        // Act
        ReviewDto result = reviewService.createReview(reviewDto);

        // Assert
        assertNotNull(result);
        assertEquals(bookResponse.getIdBookResponse(), result.getIdBookResponse());
        assertEquals(bookResponse.getResponse(), result.getResponse());

        verify(bookLiteraryRepository, times(1)).findById(1L);
        verify(userService, times(1)).getUserById(1L);
        verify(reviewRepository, times(1)).save(any(BookResponse.class));
    }

    @Test
    void getReviewsByBook_Success() {
        // Arrange
        when(reviewRepository.findAllByBookLiterary_IdBookLiterary(1L)).thenReturn(Collections.singletonList(bookResponse));

        // Act
        List<ReviewDto> result = reviewService.getReviewsByBook(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookResponse.getIdBookResponse(), result.get(0).getIdBookResponse());

        verify(reviewRepository, times(1)).findAllByBookLiterary_IdBookLiterary(1L);
    }

    @Test
    void getReviewsByUser_Success() {
        // Arrange
        when(reviewRepository.findAllByUser_IdUser(1L)).thenReturn(Collections.singletonList(bookResponse));

        // Act
        List<ReviewDto> result = reviewService.getReviewsByUser(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookResponse.getIdBookResponse(), result.get(0).getIdBookResponse());

        verify(reviewRepository, times(1)).findAllByUser_IdUser(1L);
    }
}
