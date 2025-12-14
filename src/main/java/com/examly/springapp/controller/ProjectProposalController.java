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

import com.examly.springapp.exception.ProjectProposalNotFoundException;
import com.examly.springapp.model.ProjectProposal;
import com.examly.springapp.service.ProjectProposalService;

@RestController
@RequestMapping("/api/projectproposals")
@CrossOrigin
public class ProjectProposalController {

    @Autowired
    private ProjectProposalService pService;

    @PostMapping()
    @PreAuthorize("hasAuthority('Employee')")
    public ResponseEntity<?> addProjectProposal(@RequestBody ProjectProposal projectproposal) {
        try {
            return ResponseEntity.status(201).body(pService.addProjectProposal(projectproposal));
        } catch (ProjectProposalNotFoundException e) {
            return ResponseEntity.status(500).body(pService.addProjectProposal(projectproposal));
        }
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> getAllProjectProposals() {
        return ResponseEntity.status(200).body(pService.getAllProjectProposals());
    }

    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasAuthority('Employee')")
    public ResponseEntity<?> getAllProposalsByEmpId(@PathVariable Long empId) {
        return ResponseEntity.status(200).body(pService.getAllProposalsByEmpId(empId));
    }

    @GetMapping("/{proposalId}")
    @PreAuthorize("hasAnyAuthority('Manager','Employee')")
    public ResponseEntity<?> getByProjectProposalId(@PathVariable Long proposalId) {
        return ResponseEntity.status(200).body(pService.getByProjectProposalId(proposalId));
    }

    @DeleteMapping("/{proposalId}")
    @PreAuthorize("hasAuthority('Employee')")
    public ResponseEntity<?> deleteProposalById(@PathVariable Long proposalId) {
        return ResponseEntity.status(200).body(pService.deleteProjectProposal(proposalId));
    }

    @PutMapping("/{proposalId}")
    @PreAuthorize("hasAnyAuthority('Manager','Employee')")
    public ResponseEntity<?> editProposalbyId(@PathVariable Long proposalId, @RequestBody ProjectProposal projectProposal) {
        return ResponseEntity.status(200).body(pService.editProjectProposal(proposalId, projectProposal));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('Employee')")
    public ResponseEntity<?> getAllProjectProposalsByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(pService.getAllProjectProposalsByUserId(userId));
    }

}
