package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.ProjectProposal;
import com.examly.springapp.model.User;

@Repository
public interface ProjectProposalRepo extends JpaRepository<ProjectProposal, Long> {

    public List<ProjectProposal> findByUser(User user);

}
