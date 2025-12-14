package com.examly.springapp.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.ProjectAlreadyExistsException;
import com.examly.springapp.exception.ProjectNotFoundException;
import com.examly.springapp.model.Project;
import com.examly.springapp.model.User;
import com.examly.springapp.service.ProjectService;
import com.examly.springapp.service.UserService;

@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/api/projects")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @PostMapping("/api/projects")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> addProject(@RequestBody Project project) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(projectService.addProject(project));
        } catch (ProjectAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Project already exists");
        }
    }

    @GetMapping("/api/projects/{projectId}")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> getProjectById(@PathVariable Long projectId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.getProjectById(projectId));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @PutMapping("/api/projects/{projectId}")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.editProject(projectId, project));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @DeleteMapping("/api/projects/{projectId}")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.deleteProject(projectId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Project is linked with a user");
        }
    }

    @GetMapping("/api/users")
    @PreAuthorize("hasAnyAuthority('Manager','Employee')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @GetMapping("/api/user/{userId}")
    @PreAuthorize("hasAnyAuthority('Manager','Employee')")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserObjectById(userId));
    }

    @GetMapping("/api/project/{projectId}/user/{userId}")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> addUserToProject(@PathVariable Long projectId, @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.addUserToProject(projectId, userId));
    }

    @DeleteMapping("/api/project/remove/{projectId}/user/{userId}")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<?> removeUserFromProject(@PathVariable Long projectId, @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.removeUserFromProject(projectId, userId));
    }
}
