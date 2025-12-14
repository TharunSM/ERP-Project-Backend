package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    Optional<Project> findByProjectTitle(String title);

}
