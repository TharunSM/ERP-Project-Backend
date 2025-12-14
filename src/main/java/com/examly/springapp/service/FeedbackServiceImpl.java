package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.FeedbackNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedBackRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedBackRepo feedbackRepo;

    @Autowired
    private UserRepo userRepo;

    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }

    @Override
    public List<Feedback> getAllFeedbackByUserId(Long userId) {
        User toFindAllFedbacksForUser = userRepo.findById(userId).get();
        return feedbackRepo.findByUser(toFindAllFedbacksForUser);

    }

    @Override
    public Feedback editFeedback(Long feedbackId, Feedback updateFeedback) {

        Feedback feedback = feedbackRepo.findById(feedbackId).get();
        feedback.setFeedbackText(updateFeedback.getFeedbackText());
        return feedbackRepo.save(feedback);
    }

    @Override
    public Feedback deleteFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if (feedback == null) {
            throw new FeedbackNotFoundException();
        } else {
            feedbackRepo.deleteById(feedbackId);
            return feedback;
        }
    }
}
