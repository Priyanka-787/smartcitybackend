package com.klef.fsad.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.dto.FeedbackDTO;
import com.klef.fsad.dto.FeedbackResponseDTO;
import com.klef.fsad.model.City;
import com.klef.fsad.model.Feedback;
import com.klef.fsad.model.User;
import com.klef.fsad.repository.CityRepository;
import com.klef.fsad.repository.FeedbackRepository;
import com.klef.fsad.repository.UserRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public FeedbackResponseDTO addFeedback(FeedbackDTO dto) {

	    Feedback feedback = new Feedback();

	    feedback.setMessage(dto.getMessage());
	    feedback.setRating(dto.getRating());

	    User user = userRepository.findById(dto.getUserId()).orElse(null);
	    feedback.setUser(user);

	    City city = cityRepository.findById(dto.getCityId()).orElse(null);
	    feedback.setCity(city);

	    feedback.setCreatedAt(LocalDateTime.now());

	    // 💾 SAVE
	    Feedback saved = feedbackRepository.save(feedback);

	    // 🔥 RETURN DTO
	    FeedbackResponseDTO res = new FeedbackResponseDTO();

	    res.setId(saved.getId());
	    res.setMessage(saved.getMessage());
	    res.setRating(saved.getRating());
	    res.setCreatedAt(saved.getCreatedAt());

	    // 🔥 IMPORTANT LINE
	    res.setUsername(user != null ? user.getName() : "Anonymous");

	    return res;
	}

	@Override
	public List<Feedback> getByCity(int cityId) {
		return feedbackRepository.findByCityId(cityId);
	}

	@Override
	public List<Feedback> getByUser(int userId) {
		return feedbackRepository.findByUserId(userId);
	}
	
	@Override
	public List<Feedback> getAllFeedbacks() {
	    return feedbackRepository.findAll();
	}
	
	@Override
	public List<FeedbackResponseDTO> getAllFeedback() {

	    List<Feedback> list = feedbackRepository.findAll();

	    return list.stream().map(fb -> {
	        FeedbackResponseDTO dto = new FeedbackResponseDTO();

	        dto.setId(fb.getId());
	        dto.setMessage(fb.getMessage());
	        dto.setRating(fb.getRating());
	        dto.setCreatedAt(fb.getCreatedAt());

	        // 🔥 THIS IS THE KEY LINE
	        dto.setUsername(fb.getUser() != null ? fb.getUser().getName() : "Anonymous");

	        return dto;
	    }).toList();
	}
}