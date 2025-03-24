package com.system.slam.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void sendEmail_ValidParameters_EmailSent() {
        // Arrange
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String text = "Test Email Body";

        // Act
        emailService.sendEmail(to, subject, text);

        // Assert
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendEmail_ValidParameters_EmailContentCorrect() {
        // Arrange
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String text = "Test Email Body";

        // Act
        emailService.sendEmail(to, subject, text);

        // Assert
        verify(mailSender).send(argThat(new ArgumentMatcher<SimpleMailMessage>() {
            @Override
            public boolean matches(SimpleMailMessage message) {
                assertNotNull(message);
                assertEquals(to, message.getTo()[0]);
                assertEquals(subject, message.getSubject());
                assertEquals(text, message.getText());
                return true;
            }
        }));
    }
}