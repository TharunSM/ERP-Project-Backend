package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    @PreAuthorize("hasAuthority('Employee')")
    public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.status(201).body(feedbackService.addFeedback(feedback));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> getAllFeedbacks() {
        return ResponseEntity.status(200).body(feedbackService.getAllFeedback());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('Employee')")
    public ResponseEntity<?> getAllFeedbackByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(feedbackService.getAllFeedbackByUserId(userId));
    }

    @PutMapping("/{feedbackId}")
    @PreAuthorize("hasAuthority('Employee')")
    public ResponseEntity<?> updateFeedback(@PathVariable Long feedbackId, @RequestBody Feedback feedback) {
        return ResponseEntity.status(200).body(feedbackService.editFeedback(feedbackId, feedback));
    }

    @DeleteMapping("/{feedbackId}")
    @PreAuthorize("hasAuthority('Employee')")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long feedbackId) {
        return ResponseEntity.status(200).body(feedbackService.deleteFeedback(feedbackId));
    }
}
