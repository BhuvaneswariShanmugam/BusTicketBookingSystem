package com.app.booking_system.service;

import com.app.booking_system.dto.ResponseDTO;
import com.app.booking_system.entity.Feedback;
import com.app.booking_system.repository.FeedbackRepository;
import com.app.booking_system.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    public FeedbackService(FeedbackRepository feedbackRepository){
        this.feedbackRepository=feedbackRepository;
    }

    public ResponseDTO createFeedback(Feedback feedback){
        return ResponseDTO.builder()
                .message(Constants.CREATED)
                .data(this.feedbackRepository.save(feedback))
                .statusCode(200)
                .build();
    }

    public ResponseDTO getAllFeedback(){
        return ResponseDTO.builder()
                .message(Constants.RETRIEVED)
                .data(this.feedbackRepository.findAll())
                .statusCode(200)
                .build();
    }
}
