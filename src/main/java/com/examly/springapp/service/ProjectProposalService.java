package com.examly.springapp.service;

import com.examly.springapp.model.ProjectProposal;
import java.util.List;

public interface ProjectProposalService {
    
    ProjectProposal addProjectProposal(ProjectProposal projectProposal);

    List<ProjectProposal> getAllProjectProposals();

    List<ProjectProposal> getAllProjectProposalsByUserId(Long userId);

    ProjectProposal editProjectProposal(Long proposalId, ProjectProposal updatedProposal);

    ProjectProposal deleteProjectProposal(Long proposalId);

    ProjectProposal getByProjectProposalId(Long proposalId);

    List<ProjectProposal> getAllProposalsByEmpId(Long userId);

}
