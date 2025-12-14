package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.ProjectProposalNotFoundException;
import com.examly.springapp.model.ProjectProposal;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.ProjectProposalRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class ProjectProposalServiceImpl implements ProjectProposalService {

    @Autowired
    private ProjectProposalRepo projectProposalRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public ProjectProposal addProjectProposal(ProjectProposal projectProposal) {
        return projectProposalRepo.save(projectProposal);
    }

    @Override
    public List<ProjectProposal> getAllProjectProposals() {
        return projectProposalRepo.findAll();
    }

    @Override
    public ProjectProposal getByProjectProposalId(Long proposalId) {
        return projectProposalRepo.findById(proposalId).get();
    }

    @Override
    public List<ProjectProposal> getAllProposalsByEmpId(Long userId) {
        List<ProjectProposal> proposalList = projectProposalRepo.findAll();
        List<ProjectProposal> proposalListToSend = new ArrayList<>();
        for (ProjectProposal proposal : proposalList) {
            if (proposal.getUser() != null) {
                Long userLocalId = proposal.getUser().getUserId();
                if (userLocalId.equals(userId)) {
                    proposalListToSend.add(proposal);
                }
            }
        }
        return proposalListToSend;
    }

    @Override
    public List<ProjectProposal> getAllProjectProposalsByUserId(Long userId) {
        User user = userRepo.findById(userId).get();
        return projectProposalRepo.findByUser(user);

    }

    @Override
    public ProjectProposal editProjectProposal(Long proposalId, ProjectProposal updatedProposal) {
        updatedProposal.setProposalId(proposalId);
        Optional<ProjectProposal> optoinal = projectProposalRepo.findById(proposalId);
        if (optoinal.isPresent()) {
            updatedProposal = projectProposalRepo.save(updatedProposal);
            return updatedProposal;
        } else {
            throw new ProjectProposalNotFoundException();
        }

    }

    @Override
    public ProjectProposal deleteProjectProposal(Long proposalId) {
        if (projectProposalRepo.existsById(proposalId)) {
            ProjectProposal todelProposal = projectProposalRepo.findById(proposalId).get();
            projectProposalRepo.deleteById(proposalId);
            return todelProposal;
        } else {
            throw new ProjectProposalNotFoundException();
        }

    }

}
