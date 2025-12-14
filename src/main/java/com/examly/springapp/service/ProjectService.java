package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Project;
import com.examly.springapp.model.User;

public interface ProjectService {

    Project addProject(Project project);

    List<Project> getAllProjects();

    Project editProject(Long projectId, Project updatedProject);

    Project deleteProject(Long projectId);

    Project getProjectById(Long projectId);

    User addUserToProject(Long projectId, Long userId);

    User removeUserFromProject(Long projectId, Long userId);

}
