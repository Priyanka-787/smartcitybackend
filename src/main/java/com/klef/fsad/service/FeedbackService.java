package com.klef.fsad.service;

import java.util.List;

import com.klef.fsad.dto.FeedbackDTO;
import com.klef.fsad.dto.FeedbackResponseDTO;
import com.klef.fsad.model.Feedback;


public interface FeedbackService {

    List<Feedback> getByCity(int cityId);
    List<Feedback> getAllFeedbacks();
    List<Feedback> getByUser(int userId);
    public FeedbackResponseDTO addFeedback(FeedbackDTO dto);
    public List<FeedbackResponseDTO> getAllFeedback();
}