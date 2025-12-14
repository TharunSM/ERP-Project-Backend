package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback, Long> {

    public List<Feedback> findByUser(User user);

}
